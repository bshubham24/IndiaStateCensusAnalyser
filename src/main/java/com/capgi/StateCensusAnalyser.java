package com.capgi;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import com.capgi.CustomCensusAnalyserException.ExceptionType;
import com.capgi.csvbuilder.CsvBuilderFactory;
import com.capgi.csvbuilder.CsvException;
import com.capgi.csvbuilder.ICSVBuilder;
import com.google.gson.Gson;

public class StateCensusAnalyser {
	List<CSVStateCensus> censusList = null;

	public int loadCsvData(String csvFile) throws CustomCensusAnalyserException, IOException, CsvException {
		if (!csvFile.contains(".csv")) {
			throw new CustomCensusAnalyserException("Incorrect csv file", ExceptionType.IncorrectCsvFile);
		}
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFile));) {
			ICSVBuilder<CSVStateCensus> csvBuilder = CsvBuilderFactory.createCSVBuilder();
			censusList = csvBuilder.getCsvBeanList(reader, CSVStateCensus.class);
			int noOfEntries = censusList.size();
			return noOfEntries;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return 0;
		} catch (RuntimeException e) {
			throw new CustomCensusAnalyserException("File data not correct", ExceptionType.IncorrectData);
		} catch (CsvException e) {
			throw new CsvException("Unable to parse", CsvException.ExceptionType.UNABLE_TO_PARSE);
		}
	}

	public int loadStateCode(String csvFile) throws CustomCensusAnalyserException, IOException, CsvException {
		if (!csvFile.contains(".csv")) {
			throw new CustomCensusAnalyserException("Incorrect csv file", ExceptionType.IncorrectCsvFile);
		}
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFile));) {
			ICSVBuilder<CSVStates> csvBuilder = CsvBuilderFactory.createCSVBuilder();
			List<CSVStates> censusList = csvBuilder.getCsvBeanList(reader, CSVStates.class);
			int noOfEntries = censusList.size();
			return noOfEntries;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return 0;
		} catch (NullPointerException e) {
			throw new CustomCensusAnalyserException("File is empty", ExceptionType.NO_DATA);
		} catch (RuntimeException e) {
			throw new CustomCensusAnalyserException("File data not correct", ExceptionType.IncorrectData);
		} catch (CsvException e) {
			throw new CsvException("Unable to parse", CsvException.ExceptionType.UNABLE_TO_PARSE);
		}
	}

	public String getStateWiseSortedCensusData() throws CustomCensusAnalyserException {
		if (censusList == null || censusList.size() == 0) {
			throw new CustomCensusAnalyserException("File is empty",
					CustomCensusAnalyserException.ExceptionType.NO_DATA);
		}
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.state);
		this.sort(censusComparator);
		String sortedStateCensus = new Gson().toJson(censusList);
		return sortedStateCensus;
	}

	private void sort(Comparator<CSVStateCensus> censusComparator) {
		for (int i = 0; i < censusList.size() - 1; i++) {
			for (int j = 0; j < censusList.size() - 1 - i; j++) {
				CSVStateCensus census1 = censusList.get(j);
				CSVStateCensus census2 = censusList.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					censusList.set(j, census2);
					censusList.set(j + 1, census1);
				}
			}
		}

	}
}
