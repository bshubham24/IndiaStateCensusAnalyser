package com.capgi;

import java.io.Reader;
import java.util.Iterator;

import com.capgi.CustomCensusAnalyserException.ExceptionType;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCsvBuilder<E> {
	public Iterator<E> getCsvBeanIterator(Reader reader, Class csvClass) throws CustomCensusAnalyserException {
		try {
			CsvToBeanBuilder<E> csvToBean = new CsvToBeanBuilder<E>(reader);
			csvToBean.withType(csvClass);
			csvToBean.withIgnoreLeadingWhiteSpace(true);
			return csvToBean.build().iterator();
		} catch (IllegalStateException e) {
			throw new CustomCensusAnalyserException("Invalid class", ExceptionType.InvalidClass);
		}

	}

	public <E> int getNoOfEntries(Iterator<E> iterator) {
		int noOfEntries = 0;
		while (iterator.hasNext()) {
			noOfEntries++;
			iterator.next();
		}
		return noOfEntries;
	}
}
