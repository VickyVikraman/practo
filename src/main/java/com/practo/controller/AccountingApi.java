package com.practo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.insta.integration.accounting.zoho.books.api.ChartOfAccountsApi;
import com.insta.integration.accounting.zoho.books.model.ChartOfAccount;
import com.insta.integration.accounting.zoho.books.service.ZohoBooks;
import com.practo.model.Data;
import com.practo.model.JournalRecord;
import com.practo.model.Journals;
import com.practo.model.ZohoChartOfAccount;
import com.practo.service.JournalRecordService;
import com.practo.service.JournalsService;
import com.practo.service.ZohoBooksConfigService;
import com.practo.service.ZohoChartOfAccountService;

@RestController
public class AccountingApi {
	
 //	Wiring services to controller
	@Autowired
	private JournalsService journalsService;
	
	@Autowired
	private JournalRecordService journalRecordService;
	
	@Autowired
	private ZohoBooksConfigService zohobooksConfigService;
	
	@Autowired
	private ZohoChartOfAccountService zohoChartOfAccountService;
	
	String authtoken = "95e55d6089a02f516661687e665ab829";
	String organizationid = "667382590";
	
 //	Initializing connection with ZohoBooks	 
	public ZohoBooks service() {
		ZohoBooks service = new ZohoBooks();
		if(zohobooksConfigService.getAllZohoBooksConfig().iterator().hasNext()) {
			authtoken = zohobooksConfigService.getAllZohoBooksConfig().iterator().next().getAuthToken();
			organizationid = zohobooksConfigService.getAllZohoBooksConfig().iterator().next().getOrganizationId();
		}
		service.initialize(authtoken, organizationid);
		return service;
	}
	
 // Creation of zoho account if not present 
	public void createAccount(String accountName, String accountType) {
		/*if (zohoChartOfAccountService.findByAccountName(accountName) == null) {*/
			ZohoBooks service = service();
			ChartOfAccountsApi chartOfAccountsApi = service.getChartOfAccountsApi();
			ChartOfAccount chartOfAccount = new ChartOfAccount();
			chartOfAccount.setAccountName(accountName);
			chartOfAccount.setAccountType(accountType);			
			/*if (zohoChartOfAccountService.findByAccountName(accountName) == null) {
				ZohoChartOfAccount chart_account = new ZohoChartOfAccount("",accountName, accountType, "");
				zohoChartOfAccountService.save(chart_account);
				//System.out.println(create.getAccountId());
			}*/
			try {
				ChartOfAccount create = chartOfAccountsApi.create(chartOfAccount);
				if (zohoChartOfAccountService.findByAccountId(create.getAccountId()) != null) {
					ZohoChartOfAccount chart_account = new ZohoChartOfAccount(create.getAccountId(),accountName, accountType, "");
					zohoChartOfAccountService.save(chart_account);
					System.out.println(create.getAccountId());
				}				
				
			} catch (Exception e) {
				e.printStackTrace();
			}			
		//}
	}
	
	@RequestMapping(value="/sync_credit", method = RequestMethod.GET)
	public void SyncCredit() {
		syncAccount("https://api.myjson.com/bins/uh0yf", "credit");
	}
	
	@RequestMapping(value="/sync_debit", method = RequestMethod.GET)
	public void Syncdebit() {
		 syncAccount("https://api.myjson.com/bins/e3b3f", "debit");
	}
	
 // updating tables from JSON data 
	public void syncAccount(String url, String accType) {
		String journalAccountType, accountName = "";
        RestTemplate restTemplate = new RestTemplate();
        Data data = restTemplate.getForObject(url, Data.class);
        System.out.println("dataaa" + data.getJournalRecord());
        for(JournalRecord journalRecord: data.getJournalRecord()) {
        	System.out.println("check for null" + journalRecord.getGuid().getClass());
        	if(journalRecordService.findByRecordGuid(journalRecord.getGuid()) == null) {
        		System.out.println("record service data" + journalRecordService.findByRecordGuid(journalRecord.getGuid()));
        		if(journalsService.findByBillNoIgnoreCase(journalRecord.getBillNo()).isEmpty()) {
        			Journals journal = new Journals(journalRecord.getBillNo(),journalRecord.getVoucherDate(),journalRecord.getVoucherType(), journalRecord.getBillOpenDate());
        			journal.setRecordCount(1);
        			journalsService.addJournals(journal);
        			journalRecord.setJournalId(journal.getId());
        		} else {
        			Journals journal = journalsService.findByBillNoIgnoreCase(journalRecord.getBillNo()).get(0);
        			journal.setRecordCount(journal.getRecordCount()+1);
        			if(journal.getStatus() != null && journal.getStatus() == true) {
        				journal.setStatus(false);
        			}
        			journalsService.addJournals(journal);
        			journalRecord.setJournalId(journal.getId());
        		}
        		if(zohoChartOfAccountService.findByAccountName(journalRecord.getCreditAccount()) == null) {
        			accountName = journalRecord.getCreditAccount();
        			String accountType = (accountName.equals("Counter Receipts"))? "expense" : "income";
        			createAccount(accountName, accountType);
        		}else {
        			if (accType.equalsIgnoreCase("credit")) {
        				accountName = journalRecord.getCreditAccount();
        			}
        			
        			else if (accType.equalsIgnoreCase("debit")) {
        				accountName = journalRecord.getDebitAccount();
        			}
        		
        			String accountType = (accountName.equals("Counter Receipts"))? "expense" : "income";
        			createAccount(accountName, accountType);
        			accountName = journalRecord.getCreditAccount();
        		}
    			journalAccountType = (accountName.equals("Counter Receipts"))? "credit" : "debit";
    			journalRecord.setAccountType(journalAccountType);        		
    			journalRecordService.addJournalRecord(journalRecord);
        	}
        }
	}
}
