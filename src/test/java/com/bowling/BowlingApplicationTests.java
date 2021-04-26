package com.bowling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.test.context.ActiveProfiles;

import com.bowling.model.Game;


@SpringBootTest
@ActiveProfiles(profiles = "test")
class BowlingApplicationTests {

	@Autowired
	ApplicationContext applicationcontext;
	
	@Autowired
	private Converter<Stream<String>, Game> streamToGame;

	@Test
	void testLoadShot() {
		// test invalid lines
		String[] invalidLinesTest = { "", "", null, "Fido" };
		Game game = streamToGame.convert(Arrays.stream(invalidLinesTest));
		assertEquals(game.getPlayers().size(), 0);

		//test null and return empty game
		Game gameNull = streamToGame.convert(null);
		assertTrue(gameNull.getPlayers().isEmpty());

		// test invalid shot
		String[] shotInvalidTest = { "Adrian	K", "Jhon	-2", "Fido	15" };
		Game gameInvalidShot = streamToGame.convert(Arrays.stream(shotInvalidTest));
		assertEquals(3, gameInvalidShot.getPlayers().size());
		assertTrue(gameInvalidShot.getPlayers().entrySet().stream().allMatch(entry ->
			entry.getValue().isEmpty()
		));
		
		
	}

}
