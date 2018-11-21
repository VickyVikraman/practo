package com.practo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practo.dao.JournalRecordDAO;
import com.practo.model.JournalRecord;

@Service
@Transactional
public class JournalRecordServiceImpl implements JournalRecordService{

	@Autowired
	private JournalRecordDAO journalRecordDAO;
	
	public JournalRecord findByRecordGuid(String record_guid) {
		return journalRecordDAO.findByRecordGuid(record_guid);
	}

	@Override
	@Transactional
	public void addJournalRecord(JournalRecord journalRecord) {
		journalRecordDAO.addJournalRecord(journalRecord);
	}
	
	public List<JournalRecord> findByJournalId(Long journal_id) {
		return journalRecordDAO.findByJournalId(journal_id);
	}
	
}
