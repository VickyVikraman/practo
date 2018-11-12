package com.practo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practo.dao.ZohoChartOfAccountDAO;
import com.practo.model.ZohoChartOfAccount;

@Service
@Transactional
public class ZohoChartOfAccountServiceImpl implements ZohoChartOfAccountService{

	@Autowired
	private ZohoChartOfAccountDAO zohoChartOfAccountDAO;
	
	public ZohoChartOfAccount findByAccountName(String accountName) {
		return zohoChartOfAccountDAO.findByAccountName(accountName);
	}
	
	@Override
	@Transactional
	public void save(ZohoChartOfAccount zohoChartOfAccount) {
		zohoChartOfAccountDAO.save(zohoChartOfAccount);
	}
	
	@Override
	@Transactional
	public List<ZohoChartOfAccount> findByAccountId(String accountId) {
		return zohoChartOfAccountDAO.findByAccountId(accountId);
	}
	
	public Long count() {
		return zohoChartOfAccountDAO.count();
	}
}
