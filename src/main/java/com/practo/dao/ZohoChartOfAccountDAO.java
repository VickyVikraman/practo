package com.practo.dao;

import java.util.List;

import com.practo.model.ZohoChartOfAccount;

public interface ZohoChartOfAccountDAO {

	public ZohoChartOfAccount findByAccountName(String accountName);
	
	public void save(ZohoChartOfAccount zohoChartOfAccount);
	
	public List<ZohoChartOfAccount> findByAccountId(String accountId);
	
	public Long count();
}
