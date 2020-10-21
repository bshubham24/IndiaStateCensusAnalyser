package com.capgi.csvbuilder;

public class CsvBuilderFactory<E> {
	public static ICSVBuilder createCSVBuilder() {
		return new OpenCsvBuilder();
	}
}
