package com.test.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SearchOutput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1234234324555577687L;
	
	private Map<String, Integer> counts;

	public Map<String, Integer> getCounts() {
		return counts;
	}

	public void setCounts(Map<String, Integer> counts) {
		this.counts = counts;
	}
}
