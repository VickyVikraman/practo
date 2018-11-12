package com.practo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practo.model.ZohoChartOfAccount;

@Repository
public class ZohoChartOfAccountDAOImpl implements ZohoChartOfAccountDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
 // Returns Zoho account record by Account Name
	public ZohoChartOfAccount findByAccountName(String accountName) {
		return (ZohoChartOfAccount) sessionFactory.getCurrentSession().createQuery("from ZohoChartOfAccount where accountName = '" + accountName+"'")
				.uniqueResult();
	}

 // Save Zoho Account Record
	public void save(ZohoChartOfAccount zohoChartOfAccount) {
		sessionFactory.getCurrentSession().saveOrUpdate(zohoChartOfAccount);
	}
	
 // Returns Zoho Account Record by Account ID
	@SuppressWarnings("unchecked")
	public List<ZohoChartOfAccount> findByAccountId(String accountId){
		return sessionFactory.getCurrentSession().createQuery("from ZohoChartOfAccount where accountId = " + accountId).list();
	}
	
 // Returns the count of Zoho Account Records
	public Long count() {
		return (Long) sessionFactory.getCurrentSession().createQuery("Select count(*) from ZohoChartOfAccount").uniqueResult();
	}
}
