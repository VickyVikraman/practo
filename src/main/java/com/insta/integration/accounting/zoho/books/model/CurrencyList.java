/* $Id$ */

package com.insta.integration.accounting.zoho.books.model;

import java.util.ArrayList;

/**

* This class is used to create an object for Currency list.

*/


public class CurrencyList extends ArrayList<Currency>
{
	
	
	private PageContext pageContext = new PageContext();
	
	
	/**
	
	* set the page context.
	
	* @param pageContext PageContext object.
	
	*/
	
	public void setPageContext(PageContext pageContext)throws Exception
	{
		this.pageContext = pageContext;
	}
	
	/**
	
	* get the page context.
	
	* @return Returns the PageContext object.
	
	*/
	
	public PageContext getPageContext()
	{
		return pageContext;
	}
	
}
