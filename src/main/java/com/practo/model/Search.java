package com.practo.model;

import java.util.Collection;

public class Search {
	private String voucherType;
	private Collection<? extends String> voucherTypeList;
	private String voucherFrom;
	private String voucherTo;
	private String creditAccount;
	private String accountGroup;
	
	public String getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}
	
	public String getCreditAccount() {
		return creditAccount;
	}
	public void setCreditAccount(String creditAccount) {
		this.creditAccount = creditAccount;
	}
	
	public String getAccountGroup() {
		return accountGroup;
	}
	public void setAccountGroup(String accountGroup) {
		this.accountGroup = accountGroup;
	}
	
	public String getVoucherFrom() {
		return voucherFrom;
	}
	public void setVoucherFrom(String voucherFrom) {
		this.voucherFrom = voucherFrom;
	}
	public String getVoucherTo() {
		return voucherTo;
	}
	public void setVoucherTo(String voucherTo) {
		this.voucherTo = voucherTo;
	}
	public Collection<? extends String> getVoucherTypeList(){
		return voucherTypeList;
	}
	
	public String getVoucherTypeString() {
		String joined = "";
		if(voucherTypeList == null)
			return null;
		for (String iterable_element : voucherTypeList) {
			joined +="'"+iterable_element+"',";
		}
		joined = joined.substring(0, joined.length()-1);
		return joined;
	}
	public void setVoucherTypeList(Collection<? extends String> voucherTypeList) {
		this.voucherTypeList = voucherTypeList;
	}
}
