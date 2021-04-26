package com.bowling.output;

import java.io.OutputStream;
import java.io.PrintStream;

import com.bowling.model.Game;

public interface GameOutput {

	default void output(Game data, PrintStream out) {
		// nothing
	}

	default void output(Game data, OutputStream out) {
		// nothing
	}

	
	
	
}
