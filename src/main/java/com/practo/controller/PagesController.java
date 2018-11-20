package com.practo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.insta.integration.accounting.zoho.books.api.ChartOfAccountsApi;
import com.insta.integration.accounting.zoho.books.api.JournalsApi;
import com.insta.integration.accounting.zoho.books.exception.BooksException;
import com.insta.integration.accounting.zoho.books.model.ChartOfAccount;
import com.insta.integration.accounting.zoho.books.model.ChartOfAccountList;
import com.insta.integration.accounting.zoho.books.model.Journal;
import com.insta.integration.accounting.zoho.books.model.LineItem;
import com.insta.integration.accounting.zoho.books.service.ZohoBooks;
import com.practo.model.JournalLog;
import com.practo.model.JournalRecord;
import com.practo.model.Journals;
import com.practo.model.Log;
import com.practo.model.Search;
import com.practo.model.ZohoBooksConfig;
import com.practo.model.ZohoChartOfAccount;
import com.practo.service.JournalLogService;
import com.practo.service.JournalRecordService;
import com.practo.service.JournalsService;
import com.practo.service.ZohoBooksConfigService;
import com.practo.service.ZohoChartOfAccountService;


@Controller
public class PagesController {
	
	String authtoken= "95e55d6089a02f516661687e665ab829";
	String organizationid= "667382590";
	Boolean zohosynced = false;
	String syncMsg = null;
	String voucherId = null;
	String guid = null;
	
	//Following Variable read error/success messages from application.properties file.
	@Value( "${syn.error}" )
	private String synError;
	
	@Value( "${syn.success}" )
	private String synSuccess;
	
	@Value( "${syn.already}" )
	private String alreadySyn;
	
	@Value( "${audit.log}" )
	private String auditLog;

//	Wiring services to controller	
	@Autowired
	private JournalsService journalsService;
	
	@Autowired
	private ZohoBooksConfigService zohobooksConfigService;
	
	@Autowired
	private JournalLogService journalLogService;
	
	@Autowired
	private ZohoChartOfAccountService accountService;
	
	@Autowired
	private JournalRecordService journalRecordService;

//	Initializing connection with ZohoBooks	
	public ZohoBooks service() {
		ZohoBooks service = new ZohoBooks();
		if(zohobooksConfigService.getAllZohoBooksConfig().iterator().hasNext()) {
			authtoken = zohobooksConfigService.getAllZohoBooksConfig().iterator().next().getAuthToken();
			organizationid = zohobooksConfigService.getAllZohoBooksConfig().iterator().next().getOrganizationId();
			System.out.println(authtoken+" "+organizationid);
		}
		service.initialize(authtoken, organizationid);
		return service;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listJournals(Model model) throws IOException {
		Map<String, Map> summary = new HashMap<String, Map>();
		List<Journals> results = new ArrayList<>();
//		List<Log> data = new ArrayList<>();
		if(model.asMap().get("results") == null) {
			model.addAttribute("results", new ArrayList<JournalRecord>());
		}
		if(model.asMap().get("search") == null) {
	        model.addAttribute("search", new Search());
		}

		if(zohobooksConfigService != null && zohobooksConfigService.getAllZohoBooksConfig().size() > 0) {
			model.addAttribute("zohoBooks", zohobooksConfigService.findById(zohobooksConfigService.getAllZohoBooksConfig().iterator().next().getId()));
		}else {
			model.addAttribute("zohoBooks", new ZohoBooksConfig());
		}
		
		if(zohosynced != null && zohosynced) {
			model.addAttribute("zohosyncmessage", synSuccess);
			zohosynced = false;
		}else{
			model.addAttribute("zohosyncmessage", "");
		}
		model.addAttribute("summary", summary);
		model.addAttribute("results", results);
//		model.addAttribute("logdata",data);
		return "home";
	}
	
	//RC Codingmart : What is this end point for ? What does the view name "task" translate to. There is no task.jsp. 
	@RequestMapping(value="/tasks", method = RequestMethod.GET)
	public ModelAndView tasks(ModelAndView model) {
		model.setViewName("tasks");
		return model;
	}
	
	//
	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping(value="/", method = RequestMethod.POST)
	public ModelAndView filterdata(@ModelAttribute("search") Search search,ModelAndView model) {
	   List<Journals> resultList = new ArrayList<>();
	   List<Journals> result = new ArrayList<>();
	   Iterable<JournalLog> log = journalLogService.getAllJournalLogs();
	   List<String> errors = new ArrayList<>();
	   List<Journals> filter = new ArrayList<>();
	   Map<String, Map> map = new HashMap<String, Map>();
	   if(zohobooksConfigService != null && zohobooksConfigService.getAllZohoBooksConfig().size() > 0) {
		   model.addObject("zohoBooks", zohobooksConfigService.findById(zohobooksConfigService.getAllZohoBooksConfig().iterator().next().getId()));
	   }else {
		   model.addObject("zohoBooks", new ZohoBooksConfig());
	   }
	   if (!errors.isEmpty()) {
		   model.addObject("results",errors);
	   }
	   else {
		// Checking for empty filter values
		   if(search.getVoucherFrom().isEmpty() && search.getVoucherTo().isEmpty() && search.getVoucherTypeList() == null) {
			   model.addObject("zohosyncmessage", synError);
		   }else {
		// Filter result by voucher Date and Voucher Type			
			   resultList = journalsService.findByFilter(search); 
			   model.addObject("zohosyncmessage","");
		   }
	   //  Adding same account name net amounts
		   for(Journals res : resultList) {
			   Long jid = res.getId();
			   List<JournalRecord> jr = journalRecordService.findByJournalId(new Long(jid));
			   res.setJournalRecords(jr);
			   for(JournalRecord part : jr) {
				   if(map.containsKey(part.getCreditAccount())) {				   		
				   		@SuppressWarnings("unchecked")
						Map<String, Double> temp = map.get(part.getCreditAccount());
				   		Double added_result = temp.get(part.getAccountType()).doubleValue() + part.getNetAmount().doubleValue();
				   		temp.put(part.getAccountType(), added_result);
				   		map.put(part.getCreditAccount(), temp);
				   }
				   else {
				   		Map<String, Double> temp = new HashMap<String, Double>();
				   		temp.put(part.getAccountType(), part.getNetAmount().doubleValue());
					    map.put(part.getCreditAccount(), temp);
				   }
			   }
		   }
	   }
	   model.addObject("results", resultList);
	   model.addObject("summary", map);
	   model.addObject("logs",log);
	   model.addObject("search", search);
	   model.setViewName("home");
	   return model;
	}
 // creating Zoho chart of accounts if empty
	public void getAccountsList() {
		ZohoBooks service = service();
		ChartOfAccountsApi accountsApi = service.getChartOfAccountsApi();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("filter_by", "AccountType.All"); 
        hashMap.put("sort_column", "account_type");		
		try {
			ChartOfAccountList accountList = accountsApi.getChartOfAccounts(hashMap);
			for(ChartOfAccount account : accountList) {
				if (accountService.findByAccountId(account.getAccountId()).isEmpty()) {
					ZohoChartOfAccount acc = new ZohoChartOfAccount(account.getAccountId(),account.getAccountName(), account.getAccountType(), account.getDescription());
					accountService.save(acc);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/zoho", method = RequestMethod.POST)
	public String sendToZoho(@RequestParam(value="ids[]") String[] ids, ModelAndView model) { 
		ZohoBooks service = service();
		JournalsApi journalsApi = service.getJournalsApi();
		String creditAccountId = null;
		String debitAcountId = null;
		Long groupId = (long) 1;
	 // Getting the highest group id value
		if(journalLogService.count() > 0) {
			Long identity = journalLogService.getMaxId();
			groupId = journalLogService.findById(identity).getGroupId();
			groupId ++;
		}
	 // Loop through journal ids
		for(String idd: ids) {
			
			List<String> accountIds = new ArrayList<String>();
			Map<String, String> map = new HashMap<String, String>();
			Integer id =Integer.parseInt(idd);
			Journals journal = journalsService.getJournals(new Long(id));
			if(accountService.count() < 0) {
				getAccountsList();
			}
		 // checks for creation of zoho journal
			if(journal.getStatus() == null || journal.getStatus() == false) {
				Journal zoho_journal = new Journal();
				zoho_journal.setJournalDate(new SimpleDateFormat("yyyy-MM-dd").format(journal.getBillOpenDate()).toString());
				zoho_journal.setReferenceNumber(journal.getBillNo());
				zoho_journal.setNotes("Bill no: "+ journal.getBillNo());
				List<LineItem> lineItems = new ArrayList<LineItem>();
				//List<LineItem> debitLineItems = new ArrayList<LineItem>();
				Long jid = journal.getId();
				List<JournalRecord> jr = journalRecordService.findByJournalId(new Long(jid));
				for(JournalRecord record : jr) {
					debitAcountId = null;
					creditAccountId = null;
					if (accountService.findByAccountName(record.getCreditAccount()) != null) {
						creditAccountId = accountService.findByAccountName(record.getCreditAccount()).getAccountId();
					}
					if (accountService.findByAccountName(record.getDebitAccount()) != null) {
						debitAcountId = accountService.findByAccountName(record.getDebitAccount()).getAccountId();
					}
					
				  // Duplicate line item's net amount are added  	
					/* if(map.containsKey(accountId) && record.getAccountType().equals(map.get(accountId))){
						Double bd = new Double(lineItems.get(accountIds.indexOf(accountId)).getAmount()) +  record.getNetAmount();
						lineItems.get(accountIds.indexOf(accountId)).
							setAmount(bd);
					 }*/
					/* else{*/
						
						
						if (creditAccountId != null) 
						{
							LineItem lineItem = new LineItem();							
							lineItem.setAmount(record.getNetAmount().doubleValue());
							lineItem.setAccountId(creditAccountId);
							lineItem.setDebitOrCredit("credit");
							lineItems.add(lineItem);
						}
						if (debitAcountId != null) {
							LineItem lineItem = new LineItem();							
							lineItem.setAmount(record.getNetAmount().doubleValue());
							lineItem.setAccountId(debitAcountId);
							lineItem.setDebitOrCredit("debit");
							lineItems.add(lineItem);
						}
						
						accountIds.add(creditAccountId);
						map.put(creditAccountId, record.getAccountType());
					// }
					 voucherId = record.getVoucherNo();
					 guid = record.getGuid();
					 if(voucherId.equals("RA038033")) {
						voucherId = "AB16000504";
					 }
				}
			 // Updating Journal Log table
				try {
					zoho_journal.setLineItems(lineItems);
					Journal create = journalsApi.create(zoho_journal);
					JournalLog journalLogs = new JournalLog(new Date(), groupId, "In queue", "Success", journal.getId(), voucherId, guid);
					journalLogService.addJournalLog(journalLogs);
					journal.setStatus(true);
					journalsService.addJournals(journal);
				} catch (BooksException booksException) {
					JournalLog journalLogs = new JournalLog(new Date(), groupId, "Failed", booksException.getMessage(), journal.getId(), voucherId, guid);
					journalLogService.addJournalLog(journalLogs);
					journal.setStatus(false);
					journalsService.addJournals(journal);
					System.out.println("Account Type:" + journal);
				} catch (Exception e1) {
					System.out.println(e1);
				}
		        zohosynced = true;
			}
			else {
				zohosynced = true;
				syncMsg = alreadySyn;
			}
		}
	return "redirect:/";
	}
	
 // Clears page
	@RequestMapping(value="/clear", method = RequestMethod.POST)
	public ModelAndView clear() {
		return new ModelAndView("redirect:/");
	}
	
 // Display Log modal
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="/logModal", method = RequestMethod.POST)
	public ModelAndView logModalViaAjax(@ModelAttribute("logg") Log logg, ModelAndView model) {
		List<Object[]> data = new ArrayList<>();
		List<Map> subResult = new ArrayList<>();
		Map<String, List<Map>> mainResult = new HashMap<String, List<Map>>();
		data = journalLogService.searchAjax(logg.getFromDate(),logg.getEndDate());
	 // Loop through data to get like key value pairs
		for(Object[] records : data) {
			Map<String,String> dataResult = new HashMap<String,String>();
			dataResult.put("group_id",records[0].toString());
			dataResult.put("voucher_id",records[1].toString());
			dataResult.put("sync_status", records[2].toString());
			dataResult.put("sync_date", records[3].toString());
			dataResult.put("voucher_type", records[4].toString());
			dataResult.put("credit_account", records[5].toString());
			dataResult.put("account_type", records[6].toString());
			dataResult.put("net_amount", records[7].toString());
			subResult.add(dataResult);
		}
	 // Loop through key value pairs to group by group id
		for(Map sub : subResult) {
			if(mainResult.containsKey(sub.get("group_id").toString())) {
				List<Map> temp = mainResult.get(sub.get("group_id").toString());
				temp.add(sub);
				mainResult.put(sub.get("group_id").toString(), temp);
			}
			else {
				List<Map> preResult = new ArrayList<>();
				preResult.add(sub);
				mainResult.put(sub.get("group_id").toString(), preResult);
			}
		}
		model.addObject("isError", false);
		if (data.size() == 0) {
			model.addObject("auditLogError", auditLog);
			model.addObject("isError", true);
		} else {
			model.addObject("auditLogError", "");
		}
		model.addObject("logdata",mainResult);
		model.addObject("logg", logg);
		// RC Codinngmart: Setting a view name with @ResponseBody ?
		model.setViewName("logModal");
		return model;
	}
	
 // GET method for the page 'logModal'
	@RequestMapping(value="/logModal", method = RequestMethod.GET)
	public String logModal(Model model) throws IOException {
		List<String> data = new ArrayList<>();
		model.addAttribute("logdata",data);
		if(model.asMap().get("logg") == null) {
			model.addAttribute("logg", new Log());
		}
		// RC Codingmart : why logdata is added twice ?
		model.addAttribute("logdata",data);
		return("logModal");
	}
}