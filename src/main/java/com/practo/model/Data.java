package com.practo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    private List<JournalRecord> data;
    
	public List<JournalRecord> getData() {
		return data;
	}
	
 // Getter and Setter for JournalRecord Data from JSON
	public void setData(List<JournalRecord> data) {
		this.data = data;
	}
	
    public List<JournalRecord> getJournalRecord() {
        return data;
    }
}
