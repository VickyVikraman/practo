package com.practo.dao;

import java.util.List;

import com.intuit.ipp.data.JournalEntry;
import com.practo.model.Journals;
import com.practo.model.Search;

public interface JournalsDAO {

	public void addJournals(Journals journals);

	public Journals getJournals(Long journalsid);
				
	public List<Journals> findByFilter(Search search);
	
	public List<Journals> findByBillNoIgnoreCase(String billNo);
	
	public Journals getJournalEntry(long journalEntryId);
}
