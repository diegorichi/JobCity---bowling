package com.bowling.model.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.bowling.model.Bowling;
import com.bowling.model.Game;
import com.bowling.model.Shot;

/**
 * This class is responsible of convert a game from the input data to our model.
 * 
 * @author Diego Richi
 *
 */
@Component
@ConfigurationProperties(prefix = "converter")
@PropertySource("classpath:bowling.properties")
public class StringToBowlingConverter implements Converter<Stream<String>, Game> {

	private Logger LOG = LoggerFactory.getLogger(StringToBowlingConverter.class);

	private String separator;
	

	@Autowired
	MessageSource messageSource;

	@Autowired
	ApplicationContext applicationContext;

	/**
	 * This convert method takes all lines of the input data and ensure the process
	 * of each line.
	 * @param source contains the each shot of the player and the player name
	 */
	@Override
	public Game convert(Stream<String> source) {

		Bowling bowling = (Bowling) applicationContext.getBean("bowling");

		if (source == null)
			return bowling;

		source.forEach((String line) -> {

			Optional<String> lineOp = Optional.ofNullable(line);

			lineOp.ifPresent((String lineNotNull) -> {
				Optional<Shot> shot = this.processLine(lineNotNull, bowling);
				bowling.addValidShot(shot);
			});

		});

		//also we validate here a few things.
		if (!bowling.validate()){
			LOG.error(messageSource.getMessage("game.invalid", null, Locale.getDefault()));
		}
		
		return bowling;
	}

	/**
	 * Process the shot of player. Split line by tab Char (this could be
	 * parameterized in the future) Take the name and the value of the shot. Also
	 * add the player if is not present in the game and the shot to the player.
	 * 
	 * @param lineNotNull
	 * @param bowling
	 * @return
	 */
	private Optional<Shot> processLine(String lineNotNull, Bowling bowling) {
		String[] split = lineNotNull.split(getSeparator());

		//we have a name and points
		if (split.length == 2) {
			Shot shot = new Shot(bowling, lineNotNull);
			Map<String, List<Shot>> players = bowling.getPlayers();
			String playerName = split[0];
			shot.setPlayerName(playerName);

			shot.setShot(split[1]);

			if (!players.containsKey(playerName)) {
				players.put(playerName, new ArrayList<>());
			}
			// we return the shot as an optional
			return Optional.of(shot);

		} else {
			//the shot is discarded and return an Empty optional
			if (LOG.isWarnEnabled()) {
				String[] arg = { lineNotNull };
				LOG.warn(messageSource.getMessage("converter.invalid.line", arg, Locale.getDefault()));
			}
		}
		return Optional.empty();
	}

	
	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

}
