package com.test.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.SearchInput;
import com.test.model.SearchOutput;
import com.test.service.ISearchService;
import com.test.util.Util;


@RestController
public class SearchController {
	
	@Autowired
	ISearchService searchService;
	
	@RequestMapping(value ="/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE	)
	public ResponseEntity<SearchOutput> search(@RequestBody SearchInput searchInput){
		 return new ResponseEntity<SearchOutput>(searchService.search(searchInput), HttpStatus.OK);
	}
	
	@RequestMapping(value ="/top/{count}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE )
	public ResponseEntity<InputStreamResource> getCountCSV(@PathVariable("count") Integer count, HttpServletRequest request) throws IOException {
		String outputFilePath = request.getServletContext().getRealPath("/WEB-INF/classes") + File.separator + "output.csv";
		
		Util.deleteExistingOutputFile(outputFilePath);
		
		ClassPathResource outputFile = searchService.getCountCSV(count, outputFilePath);
		
		if(outputFile != null ){
			 HttpHeaders headers = new HttpHeaders();
		    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		    headers.add("Pragma", "no-cache");
		    headers.add("Expires", "0");
		    headers.add("Content-Disposition", "attachment; filename=\"output.csv\"");
			return ResponseEntity
			            .ok()
			            .headers(headers)
			            .contentLength(outputFile.contentLength())
			            .contentType(MediaType.APPLICATION_OCTET_STREAM)
			            .body(new InputStreamResource(outputFile.getInputStream()));
			
		}else {
			ClassPathResource errorOutputFile = new ClassPathResource("ErrorOutput.txt");
			return ResponseEntity
		            .ok()
		            .contentLength(errorOutputFile.contentLength())
		            .contentType(MediaType.APPLICATION_OCTET_STREAM)
		            .body(new InputStreamResource(errorOutputFile.getInputStream()));
		}
		
	}
}
