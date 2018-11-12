package com.practo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practo.dao.ZohoBooksConfigDAO;
import com.practo.model.ZohoBooksConfig;

@Service
@Transactional
public class ZohoBooksConfigServiceImpl implements ZohoBooksConfigService{
	
	@Autowired
	private ZohoBooksConfigDAO zohoBooksConfigDAO;
	
	@Override
	@Transactional
	public List<ZohoBooksConfig> getAllZohoBooksConfig() {
		return zohoBooksConfigDAO.getAllZohoBooksConfig();
	}
	
	@Override
	@Transactional
	public void addZohoBooks(ZohoBooksConfig zohoBooksConfig) {
		zohoBooksConfigDAO.addZohoBooks(zohoBooksConfig);
	}

	public ZohoBooksConfig findById(Long id) {
		return zohoBooksConfigDAO.findById(id);
	}
}
