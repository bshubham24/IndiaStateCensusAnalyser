package com.capgi;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.capgi.csvbuilder.CsvException;
import com.google.gson.Gson;

public class StateCodeTest {
	public static final String STATE_CODE_FILE = "F:\\Capgemini_training1\\java_eclipse\\IndiaStateCensusAnalyser\\src\\main\\java\\com\\capgi\\IndiaStateCode.csv";
	public static final String WRONG_STATE_CODE_FILE = "F:\\Capgemini_training1\\java_eclipse\\IndiaStateCensusAnalyser"
			+ "\\IndiaStateCensusData.cv";
	public static final String WRONG_DATA_IN_STATE_CODE_FILE = "F:\\Capgemini_training1\\java_eclipse\\IndiaStateCensusAnalyser\\IndiaStateCodeDelimiter.csv";
	public static final String WRONG_HEADER_IN_STATE_CODE_FILE = "F:\\Capgemini_training1\\java_eclipse\\IndiaStateCensusAnalyser"
			+ "\\IndiaStateCensusData.csv";
	public static final String WRONG_STATE_CODE_FILE_TYPE = "F:\\Capgemini_training1\\java_eclipse\\IndiaStateCensusAnalyser"
			+ "\\IndiaStateCensusData.txt";

	@Test
	public void whenNumberOfRecordMatchesForStateCodeFileShouldPassTest()
			throws CustomCensusAnalyserException, IOException, CsvException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		int totalEntriesInCsvFile = stateCensusAnalyser.loadStateCode(STATE_CODE_FILE);
		Assert.assertEquals(37, totalEntriesInCsvFile);
	}

	@Test
	public void whenFileIsNotInCsvFormatShouldThrowCustomException() throws IOException, CsvException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			int totalEntriesInCsvFile = stateCensusAnalyser.loadStateCode(WRONG_STATE_CODE_FILE);
		} catch (CustomCensusAnalyserException e) {
			Assert.assertEquals(CustomCensusAnalyserException.ExceptionType.IncorrectCsvFile, e.type);
		}
	}

	@Test
	public void whenFileDataIsImproperShouldThrowCustomException() throws IOException, CsvException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			int totalEntriesInCsvFile = stateCensusAnalyser.loadStateCode(WRONG_DATA_IN_STATE_CODE_FILE);
		} catch (CustomCensusAnalyserException e) {
			Assert.assertEquals(CustomCensusAnalyserException.ExceptionType.IncorrectData, e.type);
		}
	}

	@Test
	public void whenFileHeaderIsImproperShouldThrowCustomException() throws IOException, CsvException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			int totalEntriesInCsvFile = stateCensusAnalyser.loadStateCode(WRONG_HEADER_IN_STATE_CODE_FILE);
		} catch (CustomCensusAnalyserException e) {
			Assert.assertEquals(CustomCensusAnalyserException.ExceptionType.IncorrectData, e.type);
		}
	}

	@Test
	public void whenFileTypeIsImproperShouldThrowCustomException() throws IOException, CsvException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			int totalEntriesInCsvFile = stateCensusAnalyser.loadStateCode(WRONG_STATE_CODE_FILE_TYPE);
		} catch (CustomCensusAnalyserException e) {
			Assert.assertEquals(CustomCensusAnalyserException.ExceptionType.IncorrectCsvFile, e.type);
		}

	}

	@Test
	public void whenSortedByStateCodeShouldReturnSortedData()
			throws CustomCensusAnalyserException, IOException, CsvException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		stateCensusAnalyser.loadStateCode(STATE_CODE_FILE);
		String sortedCensusData = stateCensusAnalyser.getStateCodeWiseSortedCodeData();
		CSVStates[] censusCsv = new Gson().fromJson(sortedCensusData, CSVStates[].class);
		Assert.assertEquals("AD", censusCsv[0].stateCode);
		Assert.assertEquals("WB", censusCsv[36].stateCode);
	}
}
