package com.bowling.output;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.bowling.model.BowlingResult;
import com.bowling.model.Game;
import com.bowling.model.Shot;

@Component
public class SystemOutGameOutput implements GameOutput {

	/**
	 * Print the game data to PrintStream (in this case is System.out
	 */
	@Override
	public void output(Game data, PrintStream out) {

		printFrame(out);

		StringBuilder players = new StringBuilder();

		data.getPlayers().entrySet().stream().forEach(playerEntry -> 
			printPlayerData(players, playerEntry, data)
		);

		out.println(players.toString());

	}

	/**
	 * Print all information about the player
	 * 
	 * @param lineBuilder
	 * @param playerEntry
	 * @param game
	 */
	private void printPlayerData(StringBuilder lineBuilder, Entry<String, List<Shot>> playerEntry, Game game) {

		printPlayerName(lineBuilder, playerEntry);

		Map<String, List<String>> scoreGame = game.getScoreGame(playerEntry.getValue());

		printPinfall(lineBuilder, scoreGame);

		printScore(lineBuilder, scoreGame);

	}
	/**
	 * Print the score line to the out param
	 * @param out
	 */
	private void printScore(StringBuilder scoreBuilder, Map<String, List<String>> scoreGame) {
		List<String> score = scoreGame.get(BowlingResult.SCORE);
		scoreBuilder.append("Score\t\t");
		String spaces = "\t\t";
		
		// i use the for with an index to avoid print spaces after last item
		
		
		for (int i = 0; i < score.size(); i++) {
			String element = score.get(i);
			if (i == (score.size() - 1)) {
				spaces = "";
			}
			scoreBuilder.append(String.format("%s%s", element, spaces));
		}
		scoreBuilder.append(System.lineSeparator());
	}

	/**
	 * Print the pinfall line to the out param
	 * @param out
	 */
	private void printPinfall(StringBuilder pinfallBuilder, Map<String, List<String>> scoreGame) {
		List<String> pinfall = scoreGame.get(BowlingResult.PINFALL);
		pinfallBuilder.append("Pinfalls\t");
		String spaces = "\t";
		
		// i use the for with an index to avoid print spaces after last item
		
		for (int i = 0; i < pinfall.size(); i++) {
			String item = pinfall.get(i);
			if ("X".equalsIgnoreCase(item)) {
				pinfallBuilder.append(String.format("%sX%s",spaces,spaces));
			} else {
				if (i == (pinfall.size() - 1)) {
					spaces = "";
				}
				pinfallBuilder.append(String.format("%s%s", item, spaces));
			}
		}
		pinfallBuilder.append(System.lineSeparator());
	}

	/**
	 * Print the player name to the out poaram
	 * @param out
	 */

	private void printPlayerName(StringBuilder players, Entry<String, List<Shot>> playerEntry) {
		String name = playerEntry.getKey().trim();
		players.append(name);
		players.append(System.lineSeparator());
	}

	/**
	 * Print the frame to the out poaram
	 * @param out
	 */
	private void printFrame(PrintStream out) {
		StringBuilder frame = new StringBuilder("Frame\t\t");
		// just print the frame line
		
		// i use the for with an index to avoid print spaces after last item
		
		for (int j = 1; j < 11; j++) {
			frame.append(j);
			//only print the tab in non-last item
			if (j < 10)
				frame.append("\t\t");

		}
		frame = frame.append(System.lineSeparator());
		out.print(frame.toString());
	}

}
