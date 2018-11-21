package com.insta.integration.accounting.quick.books.api;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.intuit.developer.sampleapp.crud.helper.AccountHelper;
import com.insta.integration.accounting.quick.books.qbo.DataServiceFactory;
import com.intuit.ipp.data.Account;
import com.intuit.ipp.data.Error;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.util.Logger;

/**
 * Demonstrates methods to create account
 * 
 * @author dderose
 *
 */
public class AccountCreate {
	
	private static final org.slf4j.Logger LOG = Logger.getLogger();
		
	public static void main(String[] args) {
		try {
//			createAccount();
		} catch (Exception e) {
			LOG.error("Error during CRUD", e.getCause());
		}
	}
	
	public static void createAccount(HttpSession session) throws Exception {
		
		try {
			DataService service = DataServiceFactory.getDataService(session);
			// add bank account
			System.out.println("Service Initiate");
			Account account = AccountHelper.getBankAccountFields();
			System.out.println("accountDetails");
			Account savedAccount = service.add(account);
			LOG.info("Account created: " + savedAccount.getId());		
			
		} catch (FMSException e) {
			System.out.println("Account Create "+e.getMessage());
			List<Error> list = e.getErrorList();
			list.forEach(error -> LOG.error("Error while calling entity add:: " + error.getMessage()));			
		}
		
	}
	

	

}
