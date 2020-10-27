package com.capgi.csvbuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCsvBuilder<E> implements ICSVBuilder<E> {

	public Iterator<E> getCsvBeanIterator(Reader reader, Class csvClass) throws CsvException {
		return getCsvBean(reader, csvClass).iterator();
	}

	public List<E> getCsvBeanList(Reader reader, Class csvClass) throws CsvException {
		return getCsvBean(reader, csvClass).parse();
	}

	private CsvToBean<E> getCsvBean(Reader reader, Class csvClass) throws CsvException {
		try {
			CsvToBeanBuilder<E> csvToBean = new CsvToBeanBuilder<E>(reader);
			csvToBean.withType(csvClass);
			csvToBean.withIgnoreLeadingWhiteSpace(true);
			return csvToBean.build();
		} catch (IllegalStateException e) {
			throw new CsvException(e.getMessage(), CsvException.ExceptionType.UNABLE_TO_PARSE);
		}
	}

}
