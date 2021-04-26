package com.bowling.io.strategy;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * The classes should implement the file strategy loader. They can be used
 * whatever method you want, for example input stream buffered reader or another
 * implementation.
 * 
 * @author Diego Richi
 * @return Stream with each line of the file should be returned
 */
public interface FileStrategyLoader {

	Stream<String> loadInputfile(String dataFile) throws IOException;

}
