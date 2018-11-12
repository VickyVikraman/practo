package com.practo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practo.model.ZohoBooksConfig;

@Repository
public class ZohoBooksConfigDAOImpl implements ZohoBooksConfigDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
 // Returns the List of Zoho Books
	@SuppressWarnings("unchecked")
	public List<ZohoBooksConfig> getAllZohoBooksConfig() {
		return sessionFactory.getCurrentSession().createQuery("from ZohoBooksConfig").list();
	}
	
 // Save Zoho Books
	public void addZohoBooks(ZohoBooksConfig zohoBooksConfig) {
		sessionFactory.getCurrentSession().saveOrUpdate(zohoBooksConfig);
	}
	
 // Returns Zoho Book by ID
	public ZohoBooksConfig findById(Long id) {
		return (ZohoBooksConfig) sessionFactory.getCurrentSession().get(
				ZohoBooksConfig.class, id);
	}

}
