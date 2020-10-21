package com.capgi.csvbuilder;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder<E> {
	public Iterator<E> getCsvBeanIterator(Reader reader, Class csvClass) throws CsvException;

	public <E> int getNoOfEntries(Iterator<E> iterator);
}
