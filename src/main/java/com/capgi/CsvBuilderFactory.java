package com.capgi;

public class CsvBuilderFactory<E> {
	public static ICSVBuilder createCSVBuilder() {
		return new OpenCsvBuilder();
	}
}
