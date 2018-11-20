package com.practo.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.util.ArrayList;
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

import com.insta.integration.accounting.quick.books.api.AccountCreate;
import com.insta.integration.accounting.quick.books.helper.AccountHelper;
import com.insta.integration.accounting.quick.books.model.JournalEntry;
import com.insta.integration.accounting.quick.books.qbo.DataServiceFactory;
import com.intuit.ipp.data.Account;
import com.intuit.ipp.data.EntityTypeEnum;
import com.intuit.ipp.data.EntityTypeRef;
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
			return new RedirectView(oauth2Config.prepareUrl(scopes, redirectUri, csrf), true, true, false);
		} catch (InvalidRequestException e) {
			System.out.println(e);
		}
		return null;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/quickbooks",method=RequestMethod.POST)
	public String sendToQBO(@RequestParam(value="ids[]") String[] ids,ModelAndView model,HttpSession session)throws FMSException, ParseException{	 
		
		System.out.println("Token : "+session.getAttribute("access_token"));
		try 
		{
			DataService service = DataServiceFactory.getDataService(session);
				List<String> accountIds = new ArrayList<>();
				Map<String, String> map = new HashMap<String, String>();
				Integer id =Integer.parseInt("1");
				Journals journal = journalEntryService.getJournalsEntry(new Long(id));	
				if(journal.getStatus() == null || journal.getStatus() == false) {
					
					JournalEntry entry=new JournalEntry();
					
					List<Line> lineItems = new ArrayList<Line>();
					
					Line line=getCreditLineItem(service);
					lineItems.add(line);
					
					Line line1=getDebitLineItem(service);
					lineItems.add(line1);
					
					entry.setLine(lineItems);
					
					System.out.println("Before Add");
					JournalEntry saveEntry = service.add(entry);
					System.out.println(session.getAttribute("access_token"));
					System.out.println("After Add");
					System.out.println("GUID "+guid);
					System.out.println(journal.getId());
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
	public Line getCreditLineItem(DataService service) throws FMSException{
		
		
		Line line=new Line();

		
		line.setDescription("Description "+ RandomStringUtils.randomAlphanumeric(15));
		
		line.setAmount(new BigDecimal("100.00"));
		
		line.setDetailType(LineDetailTypeEnum.JOURNAL_ENTRY_LINE_DETAIL);
		
		JournalEntryLineDetail journalEntryLineDetail=new JournalEntryLineDetail();
		journalEntryLineDetail.setPostingType(PostingTypeEnum.CREDIT);
		
		ReferenceType ref=new ReferenceType();
		ref.setValue("44");
		ref.setName("Notes Payable");
		journalEntryLineDetail.setAccountRef(ref);
		  
        line.setJournalEntryLineDetail(journalEntryLineDetail);
		
		System.out.println(line.getAmount());

		return line;
	}
	public Line getDebitLineItem(DataService service) throws FMSException, ParseException{
		
		
		Line line1=new Line();
		System.out.println("debit not null");
		
		
		line1.setId("1");
		
		line1.setDescription("Description "+ RandomStringUtils.randomAlphanumeric(15));
		
		
		line1.setAmount(new BigDecimal("100"));
		
		line1.setDetailType(LineDetailTypeEnum.JOURNAL_ENTRY_LINE_DETAIL);
		
		JournalEntryLineDetail journalEntryLineDetail1=new JournalEntryLineDetail();
		journalEntryLineDetail1.setPostingType(PostingTypeEnum.DEBIT);
		
		ReferenceType ref=new ReferenceType();
		ref.setValue("39");
		ref.setName("Notes Payable");
		journalEntryLineDetail1.setAccountRef(ref);
		
		line1.setJournalEntryLineDetail(journalEntryLineDetail1);
		
		return line1;
	}

}
