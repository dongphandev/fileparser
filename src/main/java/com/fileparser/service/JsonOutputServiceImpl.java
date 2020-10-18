package com.fileparser.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 * @author Dong Phan
 *
 *  Write the Json to text file.
 *  
 */
@Service
public class JsonOutputServiceImpl implements OutputService {

	private Logger logger = LoggerFactory.getLogger(JsonOutputServiceImpl.class);
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public void output(Map<String, Object> data, String fileName) {
		FileWriter fileWriter = null;
		try {
			File file = new File(fileName);
			if (file.exists()) {
				file.delete();
			}
			fileWriter = new FileWriter(file);
			fileWriter.write(mapper.writeValueAsString(data));
			fileWriter.flush();

		} catch (IOException e) {
			logger.error("Can not write file", e);
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					logger.error("Can not close file", e);
				}
			}

		}
	}
}
