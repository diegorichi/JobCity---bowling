package com.bowling.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bowling.io.InputLoader;
import com.bowling.model.Game;
import com.bowling.output.GameOutput;

/**
 * Concrete implementation of the ApplicationService This class execute two
 * things: 1. Load data into a model object 2. Write the model object to output
 * 
 * @author Diego Richi
 *
 */
@Component
public class GameApplicationService implements ApplicationService {

	@Autowired
	private InputLoader inputLoader;

	@Autowired
	private GameOutput gameOutput;

	@Override
	public void processGame() throws IOException {

		Game data = inputLoader.getData();

		gameOutput.output(data, System.out);

	}

}
