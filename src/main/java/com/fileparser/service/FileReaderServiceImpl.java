package com.fileparser.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fileparser.common.Constants;
import com.fileparser.common.InvalidInputFileException;
/**
 * 
 * 
 * @author Dong Phan
 * 
 * Read file line by line, parasing and write out to file.
 * 
 * If one line has any issue, then ignore it and go to next.
 *
 */
@Service
public class FileReaderServiceImpl implements FileReaderService {

	private Logger logger = LoggerFactory.getLogger(FileReaderServiceImpl.class);

	@Autowired
	ParserService parserService;

	@Autowired
	OutputService outputService;

	public void read(String path) {
		BufferedReader br = null;
		try {
			File file = new File(path);
			if (!file.exists()) {
				throw new FileNotFoundException(path);
			}
			br = new BufferedReader(new FileReader(file));
			String header = br.readLine();
			if (header == null || header.length() == 0) {
				return;
			}
			String[] fields = header.trim().split(Constants.HEADER_SEPARATOR);
			String value;
			int lineCount = 2;
			String outputFile = buildOutputFileName(file);
			while ((value = br.readLine()) != null) {
				Map<String, Object> data = parserService.parser(fields, value);
				outputService.output(data, outputFile + lineCount+ ".txt");
				lineCount++;
			}

		} catch (IOException e) {
			throw new InvalidInputFileException("Invalid input data", e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error("Can not close file", e);
				}
			}
		}
	}

	private String buildOutputFileName(File file) {
		String fileName = file.getName();
		int pos = fileName.lastIndexOf(".");

		if (pos != -1) {
			fileName = fileName.substring(0, pos);
		}

		StringBuilder builder = new StringBuilder();
		builder.append(file.getParent());
		builder.append(File.separator);
		builder.append(fileName);
		builder.append(Constants.OUTPUT);
		return builder.toString();
	}

}
