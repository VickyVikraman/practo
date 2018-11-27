package com.practo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.practo.model.JournalLog;

public interface JournalLogDAO{
	
	public List<JournalLog> getAllJournalLogs();
	
	public Long getMaxId();
	
	public JournalLog findById(Long id);
	
	public void addJournalLog(JournalLog journalLog);
	
	public Long count();
	
	public JournalLog findByJournalId(Long jid, Long gid);
	
	public List<Object[]> searchAjax(String fd, String td);
}
