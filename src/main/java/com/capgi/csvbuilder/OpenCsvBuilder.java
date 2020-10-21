package com.capgi.csvbuilder;

import java.io.Reader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCsvBuilder<E> implements ICSVBuilder<E> {
	public Iterator<E> getCsvBeanIterator(Reader reader, Class csvClass) throws CsvException {
		try {
			CsvToBeanBuilder<E> csvToBean = new CsvToBeanBuilder<E>(reader);
			csvToBean.withType(csvClass);
			csvToBean.withIgnoreLeadingWhiteSpace(true);
			return csvToBean.build().iterator();
		} catch (IllegalStateException e) {
			throw new CsvException(e.getMessage(), CsvException.ExceptionType.UNABLE_TO_PARSE);
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
