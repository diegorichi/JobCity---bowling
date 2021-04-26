package com.bowling.model;

import java.util.List;
import java.util.Map;

public interface Game extends GameResult {
	
	public boolean validate();
	
	public Map<String, List<Shot>> getPlayers();

	
}
