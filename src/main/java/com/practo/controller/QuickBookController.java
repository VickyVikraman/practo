package com.practo.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;


import com.intuit.developer.sampleapp.crud.helper.*;
//import com.insta.integration.accounting.quick.books.model.JournalEntry;
import com.insta.integration.accounting.quick.books.qbo.DataServiceFactory;
import com.insta.integration.accounting.zoho.books.model.Journal;
//import com.insta.integration.accounting.quick.books.helper.JournalEntryHelper;
import com.intuit.ipp.data.Account;
import com.intuit.ipp.data.EntityTypeEnum;
import com.intuit.ipp.data.EntityTypeRef;
import com.intuit.ipp.data.JournalEntry;
import com.intuit.ipp.data.JournalEntryLineDetail;
import com.intuit.ipp.data.Line;
import com.intuit.ipp.data.LineDetailTypeEnum;
import com.intuit.ipp.data.PostingTypeEnum;
import com.intuit.ipp.data.ReferenceType;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.services.DataService;
import com.intuit.oauth2.config.OAuth2Config;
import com.intuit.oauth2.config.Scope;
import com.intuit.oauth2.exception.InvalidRequestException;
import com.practo.client.OAuth2PlatformClientFactory;
import com.practo.model.JournalLog;
import com.practo.model.JournalRecord;
import com.practo.model.Journals;
import com.practo.quick.books.service.JournalEntryService;
import com.practo.service.JournalLogService;
import com.practo.service.JournalRecordService;
import com.practo.service.JournalsService;
import com.practo.service.ZohoChartOfAccountService;

@Controller
public class QuickBookController {
	
	String voucherId,guid;
	
	@Autowired
	OAuth2PlatformClientFactory factory;
	
	@Autowired
	private JournalEntryService journalEntryService;
	
	@Autowired
	private JournalsService journalsService;
	
	@Autowired
	private JournalLogService journalLogService;
	
	@Autowired
	private JournalRecordService journalRecordService;
	
	@Autowired
	private ZohoChartOfAccountService accountService;
	/**
	 * Controller mapping for connectToQuickbooks button
	 * @return
	 */
	@RequestMapping("/connectToQuickbooks")
	public View connectToQuickbooks(HttpSession session) {
		//logger.info("inside connectToQuickbooks ");
		OAuth2Config oauth2Config = factory.getOAuth2Config();
		
		String redirectUri = factory.getPropertyValue("OAuth2AppRedirectUri"); 
		String csrf = oauth2Config.generateCSRFToken();
		session.setAttribute("csrfToken", csrf);
		try {
			System.out.println(redirectUri);
			System.out.println(csrf);
			List<Scope> scopes = new ArrayList<Scope>();
			scopes.add(Scope.Accounting);
//			scopes.add(Scope.All);
			return new RedirectView(oauth2Config.prepareUrl(scopes, redirectUri, csrf), true, true, false);
		} catch (InvalidRequestException e) {
			System.out.println(e);
		}
		return null;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/quickbooks",method=RequestMethod.POST)
	public String sendToQBO(@RequestParam(value="ids[]") String[] ids,ModelAndView model,HttpSession session)throws FMSException, ParseException{	 
		Long groupId ;
		System.out.println("Token : "+session.getAttribute("access_token"));
		try 
		{
			DataService service = DataServiceFactory.getDataService(session);
 			String creditAccountId,debitAccountId;
 			groupId= (long) 1;
 			if(journalLogService.count() > 0) {
 				Long identity = journalLogService.getMaxId();
 				groupId = journalLogService.findById(identity).getGroupId();
 				groupId ++;
			}			
			List<String> accountIds = new ArrayList<>();
			Map<String, String> map = new HashMap<String, String>();
			List<Line> lineItems = new ArrayList<Line>();
			JournalEntry entry=new JournalEntry();
			double netAmount;
			for(String id:ids)
			{
				Integer journalId =Integer.parseInt(id);
				Journals journal = journalEntryService.getJournalsEntry(new Long(journalId));	
				if(journal.getStatus() == null || journal.getStatus() == false)
				{
					
					Long jid = journal.getId();
					List<JournalRecord> journalRecord = journalRecordService.findByJournalId(new Long(jid));
					netAmount=0.00;
					for(JournalRecord record : journalRecord)
					{
						netAmount += record.getNetAmount(); 
						voucherId = record.getVoucherNo();
						 guid = record.getGuid();
						 if(voucherId.equals("RA038033")) {
							voucherId = "AB16000504";
						 }
					}
					Line line1=getDebitLineItem(netAmount,journalRecord.get(0),journal);
					lineItems.add(line1);
					
					Line line=getCreditLineItem(netAmount,journalRecord.get(0),journal);
					lineItems.add(line);
					
					entry.setLine(lineItems);
					System.out.println("Before Add");
					
					// add journalentry
//					JournalEntry journalentry = JournalEntryHelper.getJournalEntryFields(service);
//					JournalEntry savedJournalEntry = service.add(journalentry);
					
					
					try
					{
						JournalEntry saveEntry = service.add(entry);
						JournalLog journalLogs = new JournalLog(new Date(), groupId, "In queue", "Success", journal.getId(), voucherId, guid);
						journalLogService.addJournalLog(journalLogs);
						journal.setStatus(true);
						journalsService.addJournals(journal);
					}
					catch (FMSException e) {
						
						JournalLog journalLogs = new JournalLog(new Date(), groupId, "Failed", e.getMessage(), journal.getId(), voucherId, guid);
						journalLogService.addJournalLog(journalLogs);
						journal.setStatus(false);
						journalsService.addJournals(journal);
						System.out.println(e.getMessage());
						
					}
				}
			}
		}
		catch (FMSException e) {
			System.out.println(e.getMessage());
			
		}
		catch (ParseException e) {
			
			System.out.println(e.getMessage());
			
		}
		catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		}
		
		return "redirect:/";
	}
	
	/* Getting line items by below two methods getCreditLineItem and getDebitLineItem */
	public Line getCreditLineItem(double netAmount,JournalRecord record,Journals journal) throws FMSException{
		
		
		Line line=new Line();
		
		line.setDescription(journal.getVoucherType()+" - "+record.getVoucherNo());
		
		
		line.setAmount(new BigDecimal(netAmount));
		
		line.setDetailType(LineDetailTypeEnum.JOURNAL_ENTRY_LINE_DETAIL);
		
		JournalEntryLineDetail journalEntryLineDetail=new JournalEntryLineDetail();
		journalEntryLineDetail.setPostingType(PostingTypeEnum.CREDIT);
		
		ReferenceType ref=new ReferenceType();
		ref.setValue("2");
		ref.setName(record.getVoucherRef());
		journalEntryLineDetail.setAccountRef(ref);
		  
        line.setJournalEntryLineDetail(journalEntryLineDetail);
		
		System.out.println(line.getAmount());

		return line;
	}
	
	public Line getDebitLineItem(double netAmount,JournalRecord record,Journals journal) throws FMSException, ParseException{
		
		
		Line line1=new Line();
		System.out.println("debit not null");
		
		line1.setId(journal.getId().toString());
		
		line1.setDescription(journal.getVoucherType()+" - " +record.getVoucherNo());
		
		
		line1.setAmount(new BigDecimal(netAmount));
		
		line1.setDetailType(LineDetailTypeEnum.JOURNAL_ENTRY_LINE_DETAIL);
		
		JournalEntryLineDetail journalEntryLineDetail1=new JournalEntryLineDetail();
		journalEntryLineDetail1.setPostingType(PostingTypeEnum.DEBIT);
		
		ReferenceType ref=new ReferenceType();
		ref.setValue("1");
		ref.setName(record.getVoucherRef());
		journalEntryLineDetail1.setAccountRef(ref);
		
		line1.setJournalEntryLineDetail(journalEntryLineDetail1);
		
		return line1;
	}

}
