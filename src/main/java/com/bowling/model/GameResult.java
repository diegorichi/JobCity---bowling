package com.bowling.model;

import java.util.List;
import java.util.Map;

public interface GameResult {

	public Map<String, List<String>> getScoreGame(List<Shot> value);

	
}
