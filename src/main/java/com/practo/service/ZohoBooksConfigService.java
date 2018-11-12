package com.practo.service;

import java.util.List;

import com.practo.model.ZohoBooksConfig;

public interface ZohoBooksConfigService {
	
	public void addZohoBooks(ZohoBooksConfig zohoBooksConfig);

	public List<ZohoBooksConfig> getAllZohoBooksConfig();
	
	public ZohoBooksConfig findById(Long id);

}
