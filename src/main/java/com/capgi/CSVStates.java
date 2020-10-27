package com.capgi;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {

	@CsvBindByName(column = "SrNo", required = true)
	private int SrNo;
	@CsvBindByName(column = "State Name", required = true)
	private String stateName;
	@CsvBindByName(column = "TIN", required = true)
	private int tin;
	@CsvBindByName(column = "StateCode", required = true)
	public String stateCode;

	@Override
	public String toString() {
		return "SrNo: " + SrNo + " " + "State Name: " + stateName + " " + "TIN: " + tin + " " + "State Code: "
				+ stateCode;
	}
}
