package com.bowling.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class BowlingResult implements GameResult {

	public static final String SCORE = "score";
	public static final String PINFALL = "pinfall";
	private static final String ERRORS = "errors";

	@Autowired
	MessageSource messageSource;
	
	@Override
	public Map<String, List<String>> getScoreGame(List<Shot> value) {

		Map<String, List<String>> scoreResult = new HashMap<>();
		List<String> pinfall = new ArrayList<>();
		List<String> score = new ArrayList<>();

		int shotBol = 1;
		int acumScore = 0;
		int turnScore = 0;

		for (int i = 0; i < value.size(); i++) {
			Shot shotItem = value.get(i);
			Integer shotScore = shotItem.getShotScore();
			Boolean foul = shotItem.getFoul();
			if (shotScore == 10) {
				acumScore += getScore(value, i, shotBol);
				score.add(String.valueOf(acumScore));
				pinfall.add("X");
				shotBol = 1;
				continue;
			}
			if (shotBol == 1) {
				if (foul) {
					pinfall.add("F");
				} else {
					pinfall.add(String.valueOf(shotScore));
				}
				turnScore = shotScore;
				shotBol = 2;
			} else {
				acumScore += getScore(value, i, shotBol);
				score.add(String.valueOf(acumScore));
				if (shotScore + turnScore == 10) {
					pinfall.add("/");
				} else {
					if (foul) {
						pinfall.add("F");
					} else {
						pinfall.add(String.valueOf(shotScore));
					}
				}
				shotBol = 1;
			}
		}

		scoreResult.put(PINFALL, pinfall);
		scoreResult.put(SCORE, score.subList(0,10));
		

		
		
		if (score.size()!=10) {
			scoreResult.put(ERRORS,new ArrayList<>());
			scoreResult.get(ERRORS).add(
					messageSource.getMessage("score.size.invalid", null, Locale.getDefault())
				);
		}

		return scoreResult;
	}

	private int getScore(List<Shot> value, int i, int shotBol) {
		Shot shot = value.get(i);
		Integer shotScore = shot.getShotScore();

		if (shotScore == 10) {
			int size = value.size();
			int nextShotOne = i+1 < size ? value.get(i + 1).getShotScore() : 0;
			int nextshotTwo = i+2 < size ? value.get(i + 2).getShotScore() : 0;
			return 10 + nextShotOne + nextshotTwo;
		}
		if (shotBol == 2) {
			Shot previousShot = value.get(i - 1);
			Integer prevousShotScore = previousShot.getShotScore();
			if (prevousShotScore + shotScore == 10) {
				Shot nextShot = value.get(i + 1);
				return prevousShotScore + shotScore + nextShot.getShotScore();
			} else {
				return prevousShotScore + shotScore;
			}
		}

		return 0;

	}
}
