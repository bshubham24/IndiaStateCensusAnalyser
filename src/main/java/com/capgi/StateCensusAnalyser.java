package com.capgi;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.capgi.CustomCensusAnalyserException.ExceptionType;
import com.capgi.csvbuilder.CsvBuilderFactory;
import com.capgi.csvbuilder.CsvException;
import com.capgi.csvbuilder.ICSVBuilder;
import com.google.gson.Gson;

public class StateCensusAnalyser {
	List<CSVStateCensus> stateCensusList = null;
	List<CSVStates> stateCodeList = null;

	public int loadCsvData(String csvFile) throws CustomCensusAnalyserException, IOException, CsvException {
		if (!csvFile.contains(".csv")) {
			throw new CustomCensusAnalyserException("Incorrect csv file", ExceptionType.IncorrectCsvFile);
		}
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFile));) {
			ICSVBuilder<CSVStateCensus> csvBuilder = CsvBuilderFactory.createCSVBuilder();
			stateCensusList = csvBuilder.getCsvBeanList(reader, CSVStateCensus.class);
			int noOfEntries = stateCensusList.size();
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
			stateCodeList = csvBuilder.getCsvBeanList(reader, CSVStates.class);
			int noOfEntries = stateCodeList.size();
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
		if (stateCensusList == null || stateCensusList.size() == 0) {
			throw new CustomCensusAnalyserException("File is empty",
					CustomCensusAnalyserException.ExceptionType.NO_DATA);
		}
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.state);
		this.sort(censusComparator);
		String sortedStateCensus = new Gson().toJson(stateCensusList);
		return sortedStateCensus;
	}

	private void sort(Comparator<CSVStateCensus> censusComparator) {
		for (int i = 0; i < stateCensusList.size() - 1; i++) {
			for (int j = 0; j < stateCensusList.size() - 1 - i; j++) {
				CSVStateCensus census1 = stateCensusList.get(j);
				CSVStateCensus census2 = stateCensusList.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					stateCensusList.set(j, census2);
					stateCensusList.set(j + 1, census1);
				}
			}
		}

	}

	public String getStateCodeWiseSortedCodeData() throws CustomCensusAnalyserException {
		if (stateCodeList == null || stateCodeList.size() == 0)
			throw new CustomCensusAnalyserException("No Code Data", ExceptionType.NO_DATA);
		List<CSVStates> sortedList = stateCodeList.stream().sorted(Comparator.comparing(code -> code.stateCode))
				.collect(Collectors.toList());
		String sortedCodeDataJson = new Gson().toJson(sortedList);
		return sortedCodeDataJson;
	}

	public String getPopulationWiseSortedCensusData() throws CustomCensusAnalyserException {
		if (stateCensusList == null || stateCensusList.size() == 0)
			throw new CustomCensusAnalyserException("No Code Data", ExceptionType.NO_DATA);
		List<CSVStateCensus> sortedList = stateCensusList.stream().sorted(Comparator.comparing(code -> code.population))
				.collect(Collectors.toList());
		Collections.reverse(sortedList);
		String sortedCodeDataJson = new Gson().toJson(sortedList);
		return sortedCodeDataJson;
	}

	public String getPopulationDensityWiseSortedCensusData() throws CustomCensusAnalyserException {
		if (stateCensusList == null || stateCensusList.size() == 0)
			throw new CustomCensusAnalyserException("No Code Data", ExceptionType.NO_DATA);
		List<CSVStateCensus> sortedList = stateCensusList.stream()
				.sorted(Comparator.comparing(code -> code.DensityPerSqKm)).collect(Collectors.toList());
		Collections.reverse(sortedList);
		String sortedCodeDataJson = new Gson().toJson(sortedList);
		System.out.println(sortedCodeDataJson);
		return sortedCodeDataJson;
	}
}
