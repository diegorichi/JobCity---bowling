package com.bowling;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;

import com.bowling.io.FileLoader;
import com.bowling.model.BowlingResult;
import com.bowling.model.Game;
import com.bowling.model.Shot;

@SpringBootTest
@ActiveProfiles(profiles = "test")
class BowlingExtremeCasesTests {

	@Autowired
	private FileLoader fileLoader;

	@Value("classpath:gamedata-allfoul.txt")
	private Resource resourceAllFoul;
	
	@Value("classpath:gamedata-allstrike.txt")
	private Resource resourceAllStrike;
	
	@Value("classpath:gamedata-allzero.txt")
	private Resource resourceAllZero;
	
	@Test
	void testAllFoul() throws IOException {
		// test invalid lines
		fileLoader.setDataFile(resourceAllFoul.getFile().getAbsolutePath());
		Game game = fileLoader.getData(); 
		
		Set<Entry<String, List<Shot>>> entrySet = game.getPlayers().entrySet();

		for (Entry<String, List<Shot>> entry : entrySet) {
			Map<String, List<String>> scoreGame = game.getScoreGame(entry.getValue());
			List<String> pinfall = scoreGame.get(BowlingResult.PINFALL);
			for (String item : pinfall) {
				assertEquals("F", item);
			}
			List<String> score = scoreGame.get(BowlingResult.SCORE);
			for (int i = 0; i < score.size(); i++) {
				assertEquals("0", score.get(i));
			}
			assertEquals(10, score.size());
		}
		
		assertEquals(game.getPlayers().size(), 2);
		
	}


	@Test
	void testAllStrike() throws IOException {
		// test invalid lines
		fileLoader.setDataFile(resourceAllStrike.getFile().getAbsolutePath());
		Game game = fileLoader.getData(); 
		
		Set<Entry<String, List<Shot>>> entrySet = game.getPlayers().entrySet();

		for (Entry<String, List<Shot>> entry : entrySet) {
			Map<String, List<String>> scoreGame = game.getScoreGame(entry.getValue());
			List<String> pinfall = scoreGame.get(BowlingResult.PINFALL);
			for (String item : pinfall) {
				assertEquals("X", item);
			}
			List<String> score = scoreGame.get(BowlingResult.SCORE);
			for (int i = 0; i < score.size(); i++) {
				assertEquals(String.format("%d", 30+i*30), score.get(i));
			}
			assertEquals(10, score.size());
		}
		
		assertEquals(game.getPlayers().size(), 2);
		
	}

	@Test
	void testAllZero() throws IOException {
		// test invalid lines
		fileLoader.setDataFile(resourceAllZero.getFile().getAbsolutePath());
		Game game = fileLoader.getData(); 
		
		Set<Entry<String, List<Shot>>> entrySet = game.getPlayers().entrySet();

		for (Entry<String, List<Shot>> entry : entrySet) {
			Map<String, List<String>> scoreGame = game.getScoreGame(entry.getValue());
			List<String> pinfall = scoreGame.get(BowlingResult.PINFALL);
			for (String item : pinfall) {
				assertEquals("0", item);
			}
			List<String> score = scoreGame.get(BowlingResult.SCORE);
			for (String item : score) {
				assertEquals("0", item);
			}
			assertEquals(10, score.size());			
			
		}
		
		assertEquals(2, game.getPlayers().size());
		
	}
	
	
}
