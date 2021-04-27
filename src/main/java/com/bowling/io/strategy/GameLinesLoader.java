package com.bowling.io.strategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

/**
 * This class is responsible to load the data file with {@link} {@link BufferedReader} implementation
 * @author Diego Richi
 * @return {@link Stream} of lines
 */
@Component
public class GameLinesLoader implements FileStrategyLoader {
	
	@Override
	public Stream<String> loadInputfile(String dataFile) throws IOException {

	    Path path = Paths.get(dataFile);

	    //load as bufferedReader
	    BufferedReader reader = Files.newBufferedReader(path);
	    
	    return reader.lines();
	}

}
