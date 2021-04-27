package com.bowling.io.strategy;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * The classes should implement the {@link FileStrategyLoader}. They can choose
 * another implementation, for example input stream, buffered reader or another
 * implementation.
 * 
 * @author Diego Richi
 * @return {@link Stream} with each line of the file should be returned
 */
public interface FileStrategyLoader {

	Stream<String> loadInputfile(String dataFile) throws IOException;

}
