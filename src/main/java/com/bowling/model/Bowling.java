package com.bowling.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bowling.model.validator.BowlingValidator;
import com.bowling.model.validator.ShotValidator;

@Component
// this say to the container that is not a singleton bean
@Scope("prototype")
public class Bowling implements Game {

	@Autowired
	private GameResult gameResult;

	@Autowired
	private ShotValidator shotValidator;

	@Autowired
	private BowlingValidator bowlingValidator;

	private Map<String, List<Shot>> players = new HashMap<>();

	public Map<String, List<Shot>> getPlayers() {
		return players;
	}
	
	
	//always in the model objects we need to implement
	// the equals and the hascode.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((players == null) ? 0 : players.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bowling other = (Bowling) obj;
		if (players == null) {
			if (other.players != null)
				return false;
		} else {
			if (!players.keySet().stream().allMatch((String playerName) -> other.players.containsKey(playerName))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Validate and add a shot of player
	 * 
	 * @param shot
	 */
	public void addValidShot(Optional<Shot> shot) {

		shot.ifPresent((Shot s) -> {
			if (shotValidator.validate(s)) {
				players.get(s.getPlayerName()).add(s);
			}
		});

	}

	public boolean validate() {
		return bowlingValidator.validate(this);
	}

	
	@Override
	public Map<String,List<String>> getScoreGame(List<Shot> value) {
		return gameResult.getScoreGame(value);
		
	}



}
