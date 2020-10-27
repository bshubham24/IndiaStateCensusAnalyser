package com.capgi.csvbuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<E> {
	public Iterator<E> getCsvBeanIterator(Reader reader, Class csvClass) throws CsvException;

	public List<E> getCsvBeanList(Reader reader, Class csvClass) throws CsvException;

}
