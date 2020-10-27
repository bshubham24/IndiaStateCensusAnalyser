/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.capgi;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.capgi.csvbuilder.CsvException;
import com.google.gson.Gson;

public class StateAnalyserCensusTest {
	public static final String STATE_CENSUS_DATA_FILE = "F:\\Capgemini_training1\\java_eclipse\\IndiaStateCensusAnalyser"
			+ "\\IndiaStateCensusData.csv";
	public static final String WRONG_STATE_CENSUS_DATA_FILE = "F:\\Capgemini_training1\\java_eclipse\\IndiaStateCensusAnalyser"
			+ "\\IndiaStateCensusData.cv";
	public static final String WRONG_DATA_IN_CENSUS_FILE = "F:\\Capgemini_training1\\java_eclipse\\IndiaStateCensusAnalyser\\IndiaStateCensusDataDelimiter.csv";

	public static final String WRONG_HEADER_IN_CENSUS_FILE = "F:\\Capgemini_training1\\java_eclipse\\IndiaStateCensusAnalyser\\IndiaStateCode.csv";
	public static final String WRONG_STATE_CENSUS_DATA_FILE_TYPE = "F:\\Capgemini_training1\\java_eclipse\\IndiaStateCensusAnalyser"
			+ "\\IndiaStateCensusData.txt";


	@Test
	public void whenNumberOfRecordMatchesShouldPassTest()
			throws CustomCensusAnalyserException, IOException, CsvException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		int totalEntriesInCsvFile = stateCensusAnalyser.loadCsvData(STATE_CENSUS_DATA_FILE);
		Assert.assertEquals(29, totalEntriesInCsvFile);
	}

	@Test
	public void whenFileIsNotInCsvFormatShouldThrowCustomException() throws IOException, CsvException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			int totalEntriesInCsvFile = stateCensusAnalyser.loadCsvData(WRONG_STATE_CENSUS_DATA_FILE);
		} catch (CustomCensusAnalyserException e) {
			Assert.assertEquals(CustomCensusAnalyserException.ExceptionType.IncorrectCsvFile, e.type);
		}
	}

	@Test
	public void whenFileDataIsImproperShouldThrowCustomException() throws IOException, CsvException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			int totalEntriesInCsvFile = stateCensusAnalyser.loadCsvData(WRONG_DATA_IN_CENSUS_FILE);
		} catch (CustomCensusAnalyserException e) {
			Assert.assertEquals(CustomCensusAnalyserException.ExceptionType.IncorrectData, e.type);
		}
	}

	@Test
	public void whenFileHeaderIsImproperShouldThrowCustomException() throws IOException, CsvException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			int totalEntriesInCsvFile = stateCensusAnalyser.loadCsvData(WRONG_HEADER_IN_CENSUS_FILE);
		} catch (CustomCensusAnalyserException e) {
			Assert.assertEquals(CustomCensusAnalyserException.ExceptionType.IncorrectData, e.type);
		}
	}


	@Test
	public void whenFileTypeIsImproperShouldThrowCustomException() throws IOException, CsvException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			int totalEntriesInCsvFile = stateCensusAnalyser.loadCsvData(WRONG_STATE_CENSUS_DATA_FILE);
		} catch (CustomCensusAnalyserException e) {
			Assert.assertEquals(CustomCensusAnalyserException.ExceptionType.IncorrectCsvFile, e.type);
		}

	}

	@Test
	public void whenSortedShouldReturnStatesInAlphabeticalOrder()
			throws IOException, CsvException, CustomCensusAnalyserException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		stateCensusAnalyser.loadCsvData(STATE_CENSUS_DATA_FILE);
		String sortedCensusData = stateCensusAnalyser.getStateWiseSortedCensusData();
		CSVStateCensus[] censusCsv = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		Assert.assertEquals("Andhra Pradesh", censusCsv[0].state);
		Assert.assertEquals("West Bengal", censusCsv[28].state);
	}

	@Test
	public void whenSortedByPopulatonShouldReturnSortedJsonData()
			throws IOException, CsvException, CustomCensusAnalyserException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		int totalEntriesInCsvFile = stateCensusAnalyser.loadCsvData(STATE_CENSUS_DATA_FILE);
		String sortedCensusData = stateCensusAnalyser.getPopulationWiseSortedCensusData();
		CSVStateCensus[] censusCsv = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		Assert.assertEquals("Sikkim", censusCsv[28].state);
		Assert.assertEquals("Uttar Pradesh", censusCsv[0].state);
		Assert.assertEquals(29, totalEntriesInCsvFile);
	}

	@Test
	public void whenSortedByPopulatonDensityShouldReturnSortedJsonData()
			throws IOException, CsvException, CustomCensusAnalyserException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		int totalEntriesInCsvFile = stateCensusAnalyser.loadCsvData(STATE_CENSUS_DATA_FILE);
		String sortedCensusData = stateCensusAnalyser.getPopulationDensityWiseSortedCensusData();
		CSVStateCensus[] censusCsv = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		Assert.assertEquals("Arunachal Pradesh", censusCsv[28].state);
		Assert.assertEquals("Bihar", censusCsv[0].state);
		Assert.assertEquals(29, totalEntriesInCsvFile);
	}
}
