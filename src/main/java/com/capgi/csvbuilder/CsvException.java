package com.capgi.csvbuilder;

public class CsvException extends Exception {

	public enum ExceptionType {
		UNABLE_TO_PARSE
	}

	ExceptionType type;

	public CsvException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
}
