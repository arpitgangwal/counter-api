package com.test.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.test.model.SearchInput;
import com.test.model.SearchOutput;
import com.test.service.ISearchService;
import com.test.util.Util;

@Service("searchService")
public class SearchServiceImpl implements ISearchService {

	public SearchOutput search(SearchInput searchInput) {
		SearchOutput searchOutput = new SearchOutput();
		List<String> wordsInput = searchInput.getSearchText();
		Map<String, Integer> countSearch = Util.getWordCountMap();
		Map<String, Integer> searchCount = new HashMap<String, Integer>();
		for (String word : wordsInput) {
			if (countSearch.containsKey(word.toLowerCase()))
				searchCount.put(word, countSearch.get(word.toLowerCase()));
			else
				searchCount.put(word, 0);
		}

		searchOutput.setCounts(searchCount);
		return searchOutput;
	}

	public ClassPathResource getCountCSV(Integer number, String outputFilePath) {
		Map<String, Integer> countSearch;

		if (number == null)
			return null;
		else
			countSearch = Util.getWordCountMap();
		Set<Map.Entry<String, Integer>> set = countSearch.entrySet();
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
				set);
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		File outputFile = new File(outputFilePath);
		FileWriter fw=null;
		ClassPathResource classPathResource = null;
		
		try {
			fw = new FileWriter(outputFile);
			
			int index = 0;
			while (number > 0) {
				Map.Entry<String, Integer> entry = list.get(index);
				try {
					fw.append(entry.getKey() + Util.COMMA_DELIMITER + entry.getValue());
					fw.append(Util.NEW_LINE_SEPARATOR);
				
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			
				index++;
				number--;
			}
			classPathResource = new ClassPathResource("output.csv");
			
		} catch (IOException e) {

			e.printStackTrace();
		}finally{
			try{
				fw.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		
		
		//return fw.;
		return classPathResource;
	}

	
}
