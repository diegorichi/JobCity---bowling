package com.bowling.io;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.bowling.model.Game;

/**
 * This interface define the method that is responsible to
 * load the information from an input,
 * and build the model objects to represents the game
 * @author Diego Richi
 *
 */
public interface InputLoader {

	Game getData() throws IOException;
	
}
