package com.practo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="zoho_chart_of_account")
public class ZohoChartOfAccount implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7792782663716101434L;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long id;	
	
	@Column(name="account_id")
	private String accountId = "";
	
	@Column(name="account_name")
    private String accountName = "";
	
	@Column(name="account_type")
    private String accountType = "";
	
	@Column(name="description")
    private String description = "";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	protected ZohoChartOfAccount() {
	}

	public ZohoChartOfAccount( String accountId, String accountName, String accountType, String description) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountType = accountType;
		this.description = description;
	}
}
