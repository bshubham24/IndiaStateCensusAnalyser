package com.capgi;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.capgi.CustomCensusAnalyserException.ExceptionType;
import com.capgi.csvbuilder.CsvBuilderFactory;
import com.capgi.csvbuilder.CsvException;
import com.capgi.csvbuilder.ICSVBuilder;

public class StateCensusAnalyser {

	public int loadCsvData(String csvFile) throws CustomCensusAnalyserException, IOException, CsvException {
		if (!csvFile.contains(".csv")) {
			throw new CustomCensusAnalyserException("Incorrect csv file", ExceptionType.IncorrectCsvFile);
		}
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFile));) {
			ICSVBuilder<CSVStateCensus> csvBuilder = CsvBuilderFactory.createCSVBuilder();
			List<CSVStateCensus> list = csvBuilder.getCsvBeanList(reader, CSVStateCensus.class);
			int noOfEntries = list.size();
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
			List<CSVStates> list = csvBuilder.getCsvBeanList(reader, CSVStates.class);
			int noOfEntries = list.size();
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

}
