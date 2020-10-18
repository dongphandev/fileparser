package com.fileparser.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fileparser.common.Constants;

@Service
public class SimpleParseServiceImpl implements ParserService{

	@Override
	public Map<String, Object> parser(String[] fields,String valueList) {
		Map<String, Object> result = new HashMap<>();
		if (fields == null || fields.length ==0 ) {
			return result;
		}
		String[] values = valueList.split(Constants.VALUE_SEPARATOR);
		for(int i = 0; i < fields.length; i++) {
			if(i < values.length) {
				result.put(fields[i], values[i]);
			}else {
				result.put(fields[i], null);
			}
			
		}
		return result;
	}

}
