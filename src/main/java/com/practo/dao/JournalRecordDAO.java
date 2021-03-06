package com.practo.dao;

import java.util.List;

import com.practo.model.JournalRecord;

public interface JournalRecordDAO {
	
	public void addJournalRecord(JournalRecord journalRecord);

	public JournalRecord findByRecordGuid(String record_guid);
	
	public List<JournalRecord> findByJournalId(Long journal_id);
	
	public Double getNetAmount(Long journal_id);

}
