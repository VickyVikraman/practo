package com.insta.integration.accounting.quick.books.qbo;

import javax.servlet.http.HttpSession;

import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;

/**
 * 
 * @author dderose
 *
 */

public class DataServiceFactory {

	
	/**
	 * Initializes DataService for a given app/company profile
	 * 
	 * @return
	 * @throws FMSException
	 */
	public static DataService getDataService(HttpSession session) throws FMSException {
		//create dataservice
		return new DataService(ContextFactory.getContext(session));
	}
}