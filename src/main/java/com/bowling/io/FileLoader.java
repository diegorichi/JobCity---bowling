package com.bowling.io;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.bowling.io.strategy.FileStrategyLoader;
import com.bowling.model.Game;

/**
 * This is the concrete implementation of {@link InputLoader} Interface
 * 
 * Here we choose as the exercise purpose, load the input data from file
 * and transform the input data to the game definition.
 * 
 * @author Sistemas
 *
 */
@Component
public class FileLoader implements InputLoader {

	@Autowired
	private FileStrategyLoader fileStrategyLoader;

	@Autowired
	private Converter<Stream<String>, Game> streamToGame;

    @Value("${data}")
    private String dataFile;

	@Override
	public Game getData() throws IOException {

		Stream<String> data = fileStrategyLoader.loadInputfile(dataFile);

		return streamToGame.convert(data);
	}
	
	
	public String getDataFile() {
		return dataFile;
	}

	public void setDataFile(String dataFile) {
		this.dataFile = dataFile;
	}


}
