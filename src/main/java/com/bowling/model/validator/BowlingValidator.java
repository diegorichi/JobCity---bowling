package com.bowling.model.validator;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.bowling.model.Bowling;
import com.bowling.model.Shot;

@Component
@ConfigurationProperties(prefix = "game")
@PropertySource("classpath:bowling.properties")
public class BowlingValidator implements Validator<Bowling> {

	// this properties are injected from the property file.
	private Integer players;
	private Integer maxShots;
	private Integer minShots;
	private Integer playersMinLen;
	
	@Override
	public boolean validate(Bowling b) {

		if (b == null)
			return false;

		Map<String, List<Shot>> playersMap = b.getPlayers();

		//validate the size of players
		if (playersMap.size() != this.players)
			return false;

		//validate the shot quantity
		if (!(playersMap.entrySet().stream().allMatch(entry -> 
					(entry.getValue().size() <= maxShots && entry.getValue().size() >= minShots) 
				)
			))
			return false;

		//validate the min len for name of players
		if (!(playersMap.entrySet().stream().allMatch(entry -> entry.getKey().length() >= playersMinLen)))
			return false;

		return true;
	}

	public Integer getPlayers() {
		return players;
	}

	public void setPlayers(Integer players) {
		this.players = players;
	}

	public Integer getMaxShots() {
		return maxShots;
	}

	public void setMaxShots(Integer maxShots) {
		this.maxShots = maxShots;
	}

	public Integer getMinShots() {
		return minShots;
	}

	public void setMinShots(Integer minShots) {
		this.minShots = minShots;
	}

	public Integer getPlayersMinLen() {
		return playersMinLen;
	}

	public void setPlayersMinLen(Integer playersMinLen) {
		this.playersMinLen = playersMinLen;
	}

}
