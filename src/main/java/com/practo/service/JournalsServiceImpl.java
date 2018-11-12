package com.practo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practo.dao.JournalsDAO;
import com.practo.model.Journals;
import com.practo.model.Search;

@Service
@Transactional
public class JournalsServiceImpl implements JournalsService {

	@Autowired
	private JournalsDAO journalsDAO;

	@Override
	@Transactional
	public void addJournals(Journals journals) {
		journalsDAO.addJournals(journals);
	}

	public Journals getJournals(Long journalsid) {
		return journalsDAO.getJournals(journalsid);
	}

	public void setJournalsDAO(JournalsDAO journalsDAO) {
		this.journalsDAO = journalsDAO;
	}
	
	public List<Journals> findByFilter(Search search) {
		 return journalsDAO.findByFilter(search);
	}
	
	@Override
	@Transactional
	public List<Journals> findByBillNoIgnoreCase(String billNo) {
		return journalsDAO.findByBillNoIgnoreCase(billNo);
}

}
