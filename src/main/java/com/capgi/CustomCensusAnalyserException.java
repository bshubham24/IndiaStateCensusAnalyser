package com.capgi;

public class CustomCensusAnalyserException extends Exception {
	enum ExceptionType {
		IncorrectCsvFile, IncorrectData, NO_DATA, InvalidClass;



	}

	ExceptionType type;

	public CustomCensusAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
}
