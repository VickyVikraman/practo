package com.practo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practo.model.JournalRecord;

@Repository
public class JournalRecordDAOImpl implements JournalRecordDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
 // Returns Journal Record by GUID
	public JournalRecord findByRecordGuid(String record_guid) {
		System.out.println("session " + sessionFactory.getCurrentSession());
		return (JournalRecord) sessionFactory.getCurrentSession().createQuery("from JournalRecord where guid = '" + record_guid+"'")
				.uniqueResult();
	}

 // Save Journal Record
	public void addJournalRecord(JournalRecord journalRecord) {
		sessionFactory.getCurrentSession().saveOrUpdate(journalRecord);
	}
	
 // Returns List of Journal Records by JournalId
	@SuppressWarnings("unchecked")
	public List<JournalRecord> findByJournalId(Long journal_id) {
		return sessionFactory.getCurrentSession().createQuery("from JournalRecord where journal_id="+journal_id).list();
	}
}
