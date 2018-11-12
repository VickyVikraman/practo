package com.practo.dao;

import java.util.List;

import com.practo.model.ZohoBooksConfig;

public interface ZohoBooksConfigDAO {
	
	public void addZohoBooks(ZohoBooksConfig zohoBooksConfig);

	public List<ZohoBooksConfig> getAllZohoBooksConfig();
	
	public ZohoBooksConfig findById(Long id);
	
}
