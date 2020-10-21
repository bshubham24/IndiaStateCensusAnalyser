package com.capgi;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.capgi.CustomCensusAnalyserException.ExceptionType;

public class StateCensusAnalyser {

	public int loadCsvData(String csvFile) throws CustomCensusAnalyserException, IOException {
		if (!csvFile.contains(".csv")) {
			throw new CustomCensusAnalyserException("Incorrect csv file", ExceptionType.IncorrectCsvFile);
		}
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFile));) {
			ICSVBuilder<CSVStateCensus> csvBuilder = CsvBuilderFactory.createCSVBuilder();
			Iterator<CSVStateCensus> iterator = csvBuilder.getCsvBeanIterator(reader, CSVStateCensus.class);
			int noOfEntries = csvBuilder.getNoOfEntries(iterator);
			return noOfEntries;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return 0;
		} catch (RuntimeException e) {
			throw new CustomCensusAnalyserException("File data not correct", ExceptionType.IncorrectData);
		}
	}

	public int loadStateCode(String csvFile) throws CustomCensusAnalyserException, IOException {
		if (!csvFile.contains(".csv")) {
			throw new CustomCensusAnalyserException("Incorrect csv file", ExceptionType.IncorrectCsvFile);
		}
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFile));) {
			ICSVBuilder<CSVStates> csvBuilder = CsvBuilderFactory.createCSVBuilder();
			Iterator<CSVStates> iterator = csvBuilder.getCsvBeanIterator(reader, CSVStates.class);
			int noOfEntries = csvBuilder.getNoOfEntries(iterator);
			return noOfEntries;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return 0;
		} catch (NullPointerException e) {
			throw new CustomCensusAnalyserException("File is empty", ExceptionType.NO_DATA);
		} catch (RuntimeException e) {
			throw new CustomCensusAnalyserException("File data not correct", ExceptionType.IncorrectData);
		}
	}

}
