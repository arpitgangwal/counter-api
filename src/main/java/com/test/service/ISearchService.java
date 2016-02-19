package com.test.service;



import org.springframework.core.io.ClassPathResource;

import com.test.model.SearchInput;
import com.test.model.SearchOutput;

public interface ISearchService {
	public SearchOutput search(SearchInput searchInput);
	public ClassPathResource getCountCSV(Integer number, String outputFilePath);
}
