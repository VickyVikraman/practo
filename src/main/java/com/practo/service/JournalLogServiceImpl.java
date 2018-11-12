package com.practo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practo.dao.JournalLogDAO;
import com.practo.model.JournalLog;


@Service
@Transactional
public class JournalLogServiceImpl implements JournalLogService{

	@Autowired
	private JournalLogDAO journalLogDAO;

	@Override
	@Transactional
	public List<JournalLog> getAllJournalLogs() {
		return journalLogDAO.getAllJournalLogs();
	}
	
	public Long getMaxId() {
		return journalLogDAO.getMaxId();
	}
	
	public JournalLog findById(Long id) {
		return journalLogDAO.findById(id);
	}
	
	@Override
	@Transactional
	public void addJournalLog(JournalLog journalLog) {
		journalLogDAO.addJournalLog(journalLog);
	}
	
	public Long count() {
		return journalLogDAO.count();
	}
	
	public JournalLog findByJournalId(Long jid, Long gid) {
		return journalLogDAO.findByJournalId(jid, gid);
	}
	
	public List<Object[]> searchAjax(String fd, String td) {
		return journalLogDAO.searchAjax(fd,td);
	}
}
