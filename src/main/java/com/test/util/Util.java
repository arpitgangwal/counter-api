package com.test.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;

public class Util {
	public static final String COMMA_DELIMITER = ",";
	public static final String NEW_LINE_SEPARATOR = "\n";
	private Util(){
		
	}

	public static void deleteExistingOutputFile(String filePath){
		File file = new File(filePath);
		if(file.exists()){
			file.delete();
		}
	}
	@SuppressWarnings({ "finally", "resource" })
	public static Map<String, Integer> getWordCountMap() {
		Map<String, Integer> countSearch = new HashMap<String, Integer>();
		String thisLine;
		BufferedReader br = null;


			try {
				ClassPathResource classPathResource = new ClassPathResource("Content.txt");
				File file = classPathResource.getFile();;
				br = new BufferedReader(new InputStreamReader(
						new FileInputStream(file)));
				String words[] = null;
				try {
					while ((thisLine = br.readLine()) != null) {
						words = thisLine.split(" ");
					
			
				for (int i = 0; i < words.length; i++) {
					String word = words[i].toLowerCase();
					if(word.endsWith(".") ||word.endsWith(",")  )
						word=word.substring(0, word.length()-1);
					if (!countSearch.containsKey(word))
						countSearch.put(word, 1);
					else
						countSearch
								.put(word, countSearch.get(word) + 1);
				
				}
				}
				return countSearch;
			} catch (FileNotFoundException e) {
				e.printStackTrace();

			}

		} finally {
			try {
				br.close();
				return countSearch;
			} catch (IOException e) {
				
				e.printStackTrace();
				return countSearch;
			}
		}
	}

}
