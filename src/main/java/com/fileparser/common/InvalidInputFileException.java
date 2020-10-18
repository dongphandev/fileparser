package com.fileparser.common;

public class InvalidInputFileException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	

	public InvalidInputFileException(String message, Throwable e) {
		super(message, e);
		this.message = message;
	}

}
