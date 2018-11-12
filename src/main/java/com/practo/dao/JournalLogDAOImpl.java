package com.practo.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.practo.model.JournalLog;

@Repository
public class JournalLogDAOImpl implements JournalLogDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

 // Returns the List of All Journal Logs
	@SuppressWarnings("unchecked")
	public List<JournalLog> getAllJournalLogs() {

		return sessionFactory.getCurrentSession().createQuery("from JournalLog")
				.list();
	}
	
 // Returns the Id of the highest GroupId value 
	public Long getMaxId() {
		return (Long) sessionFactory.getCurrentSession().createQuery("SELECT max(jls.id) FROM JournalLog jls").uniqueResult();
	}
	
 // Returns the Journal Log by ID
	public JournalLog findById(Long id) {
		return (JournalLog) sessionFactory.getCurrentSession().createQuery("from JournalLog where id="+id).uniqueResult();
	}
	
 //	Save Journal Log record
	public void addJournalLog(JournalLog journalLog) {
		sessionFactory.getCurrentSession().saveOrUpdate(journalLog);
	}
	
 // Returns the count of Journal Logs
	public Long count() {
		return (Long) sessionFactory.getCurrentSession().createQuery("Select count(*) from JournalLog").uniqueResult();
	}
	
 // Returns Journal Log by JournalId
	public JournalLog findByJournalId(Long jid, Long gid) {
		return (JournalLog) sessionFactory.getCurrentSession().createQuery("from JournalLog where journal_id="+jid+"and groupId ="+gid).uniqueResult();
	}
	
 // Returns the list of records between the from date and to date in Log modal page
	@SuppressWarnings("unchecked")
	public List<Object[]> searchAjax(String fd, String td) {
		List<Long> gids = new ArrayList<>();
		List<Object[]> data = new ArrayList<>();
		List<String> finaldata = new ArrayList<>();
		Date fdd = null;
		Date tdd = null;
		String newfd = null;
		String newtd = null;
		String joined = "";
		SimpleDateFormat voucherDate=new SimpleDateFormat("MM/dd/yyyy");
		try {
			fdd = voucherDate.parse(fd);
			tdd = voucherDate.parse(td);
			newfd = new SimpleDateFormat("yyyy-MM-dd").format(fdd);
			newtd = new SimpleDateFormat("yyyy-MM-dd").format(tdd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String query = (" Date(syncDate) between '" + newfd + "' and '" + newtd + "' ");
		gids = (sessionFactory.getCurrentSession().createQuery("select distinct groupId from JournalLog where"+query)).list();
		for (Long iterable_element : gids) {
			joined +="'"+iterable_element.toString()+"',";
		}
		
		if (joined.length() > 0) {
			joined = joined.substring(0, joined.length()-1);
			data = sessionFactory.getCurrentSession().createSQLQuery("SELECT b.group_id, b.voucher_id, b.sync_status, b.sync_date, a.voucher_type, a.credit_account, a.account_type, a.net_amount FROM  journal_log b left join  journal_record a ON b.guid = a.guid where group_id in (" + joined + ") order by group_id desc").list();
		}
		return data;		
	}
}
