package com.fileparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fileparser.service.FileReaderServiceImpl;


@SpringBootApplication
public class Application {
	private static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);

	}

	@Bean
	public CommandLineRunner demo() {

		return (args) -> {
			if (args.length != 1) {
				logger.info("*************Please input one and only file*******************");
				return;
			}
			logger.info(String.format("*************Start parsing file %s*******************",args[0]));
			fileReaderService.read(args[0]);
			logger.info("*************End parsing*******************");
		};
	}

	@Autowired
	private FileReaderServiceImpl fileReaderService;

}
