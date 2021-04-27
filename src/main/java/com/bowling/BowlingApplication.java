package com.bowling;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.bowling.services.ApplicationService;


/**
 * This is the main class that implements {@link CommandLineRunner}
 * @author Diego Richi
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
public class BowlingApplication implements CommandLineRunner  {

	private static Logger LOG = LoggerFactory.getLogger(BowlingApplication.class);


	public static void main(String[] args) {
		if (Arrays.stream(args).noneMatch((String arg) -> arg.contains("data"))) {
			printHelp();
			return;
		}
		SpringApplication.run(BowlingApplication.class, args);
	}
	
	private static void printHelp() {

		LOG.error("The input file should be pass as a parameter --data=<path to the input file>");
		LOG.error("The input file should be exist in the file system");

	}

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private Environment environment;
	
	@Override
	public void run(String... args) throws Exception {
	
		if (!(Arrays.stream(environment.getActiveProfiles()).anyMatch((String profile) ->
			"test".equalsIgnoreCase(profile)
		)))

		applicationService.processGame();
	}
}
