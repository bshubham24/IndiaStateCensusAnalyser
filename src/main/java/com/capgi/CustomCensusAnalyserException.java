package com.capgi;

public class CustomCensusAnalyserException extends Exception {
	enum ExceptionType {
		IncorrectCsvFile;
	}

	ExceptionType type;

	public CustomCensusAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
}
