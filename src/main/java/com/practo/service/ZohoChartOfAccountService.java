package com.practo.service;

import java.util.List;

import com.practo.model.ZohoChartOfAccount;

public interface ZohoChartOfAccountService {
	
	public ZohoChartOfAccount findByAccountName(String accountName);
	
	public void save(ZohoChartOfAccount zohoChartOfAccount);

	public List<ZohoChartOfAccount> findByAccountId(String accountId);
	
	public Long count();

}
