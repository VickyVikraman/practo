package com.practo.quick.books.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practo.dao.JournalsDAO;
import com.practo.model.Journals;

@Service
@Transactional
public class JournalEntryServiceImpl implements JournalEntryService{
	
	@Autowired
	private JournalsDAO journalsDAO;
	
	@Override
	public Journals getJournalsEntry(Long journalEntryId) {
		return journalsDAO.getJournalEntry(journalEntryId);
	}

}
