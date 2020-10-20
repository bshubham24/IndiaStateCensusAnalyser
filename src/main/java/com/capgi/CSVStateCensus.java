package com.capgi;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
	@CsvBindByName
	private String state;
	@CsvBindByName
	private int population;
	@CsvBindByName
	private int AreaInSqKm;
	@CsvBindByName
	private int DensityPerSqKm;

	@Override
	public String toString() {
		return "State: " + state + " " + "Population: " + population + " " + "Area: " + AreaInSqKm + " " + "Density: "
				+ DensityPerSqKm;
	}

}
