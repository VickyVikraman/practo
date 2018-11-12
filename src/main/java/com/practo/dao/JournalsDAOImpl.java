package com.practo.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practo.model.Journals;
import com.practo.model.Search;

@Repository
public class JournalsDAOImpl implements JournalsDAO {

	@Autowired
	private SessionFactory sessionFactory;

 // Save Journals
	public void addJournals(Journals journals) {
		sessionFactory.getCurrentSession().saveOrUpdate(journals);
	}

 // Returns List of all Journals
	public Journals getJournals(Long journalsid) {
		return (Journals) sessionFactory.getCurrentSession().get(
				Journals.class, journalsid);
	}
	
 // Returns List of Journals by Bill No
	@SuppressWarnings("unchecked")
 	public List<Journals> findByBillNoIgnoreCase(String billNo){
		return sessionFactory.getCurrentSession().createQuery("from Journals where billNo = '" + billNo+"'").list();
 	}
	
 // Returns Journal Record by voucher date and voucher type
	@SuppressWarnings({ "unchecked", "unused" })
	public List<Journals> findByFilter(Search search){
		List<String> query = new ArrayList<>();
		if(!search.getVoucherFrom().isEmpty() && !search.getVoucherTo().isEmpty()) {
			SimpleDateFormat voucherDate=new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd");
			try {
				query.add(" voucherDate between '"+formattedDate.format(voucherDate.parse(search.getVoucherFrom()))+"' and '"+formattedDate.format(voucherDate.parse(search.getVoucherTo()))+"' ");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(search.getVoucherTypeString() != null) {
			query.add(" voucherType in("+search.getVoucherTypeString()+")");
		}
		if(query.isEmpty())
			return null;
		String framedQuery=" ";
		for (String iterable_element : query) {
			framedQuery +=iterable_element+" and ";
		}
		framedQuery =  framedQuery.substring(0,framedQuery.length()-4);
		return sessionFactory.getCurrentSession().createQuery("from Journals where " + framedQuery).list();
	}

}