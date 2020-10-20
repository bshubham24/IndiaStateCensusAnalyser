package com.capgi;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class StateCodeTest {
	public static final String STATE_CODE_FILE = "F:\\Capgemini_training1\\java_eclipse\\IndiaStateCensusAnalyser\\IndiaStateCode.csv";
	public static final String WRONG_STATE_CODE_FILE = "F:\\Capgemini_training1\\java_eclipse\\IndiaStateCensusAnalyser"
			+ "\\IndiaStateCensusData.cv";
	public static final String WRONG_DATA_IN_STATE_CODE_FILE = "F:\\Capgemini_training1\\java_eclipse\\IndiaStateCensusAnalyser\\IndiaStateCodeDelimiter.csv";
	public static final String WRONG_HEADER_IN_STATE_CODE_FILE = "F:\\Capgemini_training1\\java_eclipse\\IndiaStateCensusAnalyser"
			+ "\\IndiaStateCensusData.csv";
	public static final String WRONG_STATE_CODE_FILE_TYPE = "F:\\Capgemini_training1\\java_eclipse\\IndiaStateCensusAnalyser"
			+ "\\IndiaStateCensusData.txt";

	@Test
	public void whenNumberOfRecordMatchesForStateCodeFileShouldPassTest()
			throws CustomCensusAnalyserException, IOException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		int totalEntriesInCsvFile = stateCensusAnalyser.loadStateCode(STATE_CODE_FILE);
		Assert.assertEquals(37, totalEntriesInCsvFile);
	}

	@Test
	public void whenFileIsNotInCsvFormatShouldThrowCustomException() throws IOException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			int totalEntriesInCsvFile = stateCensusAnalyser.loadStateCode(WRONG_STATE_CODE_FILE);
		} catch (CustomCensusAnalyserException e) {
			Assert.assertEquals(CustomCensusAnalyserException.ExceptionType.IncorrectCsvFile, e.type);
		}
	}

	@Test
	public void whenFileDataIsImproperShouldThrowCustomException() throws IOException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			int totalEntriesInCsvFile = stateCensusAnalyser.loadStateCode(WRONG_DATA_IN_STATE_CODE_FILE);
		} catch (CustomCensusAnalyserException e) {
			Assert.assertEquals(CustomCensusAnalyserException.ExceptionType.IncorrectData, e.type);
		}
	}

	@Test
	public void whenFileHeaderIsImproperShouldThrowCustomException() throws IOException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			int totalEntriesInCsvFile = stateCensusAnalyser.loadStateCode(WRONG_HEADER_IN_STATE_CODE_FILE);
		} catch (CustomCensusAnalyserException e) {
			Assert.assertEquals(CustomCensusAnalyserException.ExceptionType.IncorrectData, e.type);
		}
	}

	@Test
	public void whenFileTypeIsImproperShouldThrowCustomException() throws IOException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			int totalEntriesInCsvFile = stateCensusAnalyser.loadStateCode(WRONG_STATE_CODE_FILE_TYPE);
		} catch (CustomCensusAnalyserException e) {
			Assert.assertEquals(CustomCensusAnalyserException.ExceptionType.IncorrectCsvFile, e.type);
		}

	}
}
