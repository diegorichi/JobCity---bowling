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
	private Integer max_shots;
	private Integer min_shots;
	private Integer players_min_len;
	
	@Override
	public boolean validate(Bowling b) {

		if (b == null)
			return false;

		Map<String, List<Shot>> players = b.getPlayers();

		//validate the size of players
		if (players.size() != this.players)
			return false;

		//validate the shot quantity
		if (!(players.entrySet().stream().allMatch(entry -> 
					(entry.getValue().size() <= max_shots && entry.getValue().size() >= min_shots) 
				)
			))
			return false;

		//validate the min len for name of players
		if (!(players.entrySet().stream().allMatch(entry -> entry.getKey().length() >= players_min_len)))
			return false;

		return true;
	}

	public Integer getPlayers_min_len() {
		return players_min_len;
	}

	public void setPlayers_min_len(Integer players_min_len) {
		this.players_min_len = players_min_len;
	}

	public Integer getPlayers() {
		return players;
	}

	public void setPlayers(Integer players) {
		this.players = players;
	}

	public Integer getMax_shots() {
		return max_shots;
	}

	public void setMax_shots(Integer max_shots) {
		this.max_shots = max_shots;
	}

	public Integer getMin_shots() {
		return min_shots;
	}

	public void setMin_shots(Integer min_shots) {
		this.min_shots = min_shots;
	}

}
