package com.test.model;

import java.io.Serializable;
import java.util.List;

public class SearchInput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 123432423432423L;
	
	private List<String> searchText;

	public List<String> getSearchText() {
		return searchText;
	}

	public void setSearchText(List<String> searchText) {
		this.searchText = searchText;
	}
}
