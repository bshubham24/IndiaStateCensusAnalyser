package com.capgi;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
	@CsvBindByName
	public String state;
	@CsvBindByName
	public int population;
	@CsvBindByName
	public int AreaInSqKm;
	@CsvBindByName
	public int DensityPerSqKm;

	@Override
	public String toString() {
		return "State: " + state + " " + "Population: " + population + " " + "Area: " + AreaInSqKm + " " + "Density: "
				+ DensityPerSqKm;
	}

}
