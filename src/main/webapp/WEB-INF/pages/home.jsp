<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<spring:url value="/assets/css/styles.css" var="mainCss" />
<spring:url value="/assets/js/jquery-3.1.1.js" var="jqueryJs" />
<spring:url value="/assets/fonts/CamphorPro-Regular.ttf" var="regularFont" />
<spring:url value="/assets/fonts/CamphorPro-Bold.ttf" var="boldFont" />
<spring:url value="/assets/js/semantic-2.2.13.min.js" var="semanticJs" />
<spring:url value="/assets/js/script.js" var="scriptJs" />
<spring:url value="/assets/css/semantic-2.2.13.min.css" var="semanticCss" />
<spring:url value="/assets/css/datepicker.css" var="datepickerCss" />
<spring:url value="/assets/js/datepicker.js" var="datepickerJs" />
	

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Practo</title>
     <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta charset="utf-8"/> 
	
	<%-- <link th:href="<c:url value="/assets/fonts/CamphorPro-Regular.ttf" />" rel="stylesheet">
	<link th:href="<c:url value="/assets/fonts/CamphorPro-Regular.ttf" />"  rel="stylesheet">
	<script type="text/javascript" th:src="<c:url value="/assets/js/jquery-3.1.1.js" />"></script>
	<link rel="stylesheet" type="text/css" th:href="<c:url value="/assets/css/styles.css" />"> --%>
	
	<link href="${regularFont}" rel="stylesheet" />
	<link href="${boldFont}" rel="stylesheet" />
	<link href="${mainCss}" rel="stylesheet" />
    <script src="${jqueryJs}"></script>
    <script src="${semanticJs}"></script>
    <script src="${scriptJs}"></script>
    <link href="${semanticCss}" rel="stylesheet" />
    <link href="${datepickerCss}" rel="stylesheet" />
    <script src="${datepickerJs}"></script>
</head>
<body>
<div class="main-wrapper">
		<!-- Header section starts -->
		<div class="main-header ui items">
			<div class="add-link">
			<i class="sidebar icon hamburger-menu"></i>
				<!-- <a id='credential-modal'> Add Zoho Account </a>
				<span class="header-span"> | </span>
				<a  href="tasks.html"> All Sync Requests </a> -->
				<ul class="ui right header-ul">
					<button class="home-btn">
						<i class="home icon header-icons"></i> Home
					</button>
					<li>
						<a class="home-btn" href="/practo/connectToQuickbooks"><i class="info icon header-icons custom-head-icon"></i> Connect to QB</a>
					</li>
					<li>
						<a class="home-btn"><i class="info icon header-icons custom-head-icon"></i> Export Logs</a>
					</li>
					<li>
						<a class="home-btn" id="toast-danger"><i class="window close icon header-icons custom-head-icon"></i> Error sample </a>
					</li>
					<li>
						<a class="home-btn" href="#"><i class="help icon header-icons"></i> Help</a>
					</li>
					<li>
						<a class="home-btn" ><i class="user icon header-icons"></i> User</a>
					</li>
				</ul>
			</div>
		</div>
		<!-- Header section ends -->
		<div class="ui grid">
			<div id="snackbar" class="pad0">
				<div class="ui row">
				<i class="ui one wide column warning sign icon toast-danger-icon"></i>
				<h6 class="d-iblock ui fourteen wide column mar0 toast-head">Accounting Export Failed.</h6>
				<i class="close icon ui one wide column f-right" id="close-toast"></i>
				</div>
				<div class="toast-para">
					<p>Body text: The quick brown fox jumps over the hungry lazy dog. The quick brown fox jumps over the hungry lazy dog. </p>
				</div>
			</div>
		</div>
		<!-- Main wrapper starts -->
		<div class="main-wrapper">
			<div class="ui top attached tabular menu">
			  <div class="active item custom-tab">Accounting Export</div>
			</div>
			<div class="search-filter">
				<p class="sub-title">Financial Accounting</p>
			</div>
			
			<!-- Search filter section starts -->
			<div class="filter-section" id="stickyfilter">
				<div class="search-header">
					<label class="thick-label-medium">SEARCH RECORDS</label>
					<button class="toggle-search button"><span id="toggle-label">Hide</span></button>
				</div>
				
				<div class="search-body">
					<c:url var="post_url"  value="/" />
					<form:form action="${post_url}" object="${search}" modelAttribute="search" method="post">
						<div class="ui six column wide doubling stackable grid filter-bar" style="margin: 0 15px; margin-bottom: 0 !important;">
							<div class="two wide column ui input pad-l0">
								<label class="filter-label">Voucher Date (From)</label>
								<form:input type="text" placeholder="dd/mm/yyyy" class="header-inputs-mobile  datepicker" path="voucherFrom" />
							</div>
							<div class="two wide column ui input">
								<label class="filter-label">Voucher Date (To)</label>
								<form:input type="text" placeholder="dd/mm/yyyy" class="header-inputs-mobile  datepicker" path="voucherTo" />
							</div>
							<div class="two wide column ui input">
								<label class="filter-label">Export for / Center</label>
								<select class="ui compact selection dropdown filter-section-select header-inputs-mobile" name="select">
									<option value="Hospital">Hospital / 001</option>
								</select>
							</div>
							<div class="four wide column">
								<label class="filter-label">Voucher Type</label>
								<form:select class="ui fluid search select dropdown" id="voucher-types" multiple="multiple" path="voucherTypeList">
									<form:option value="Consignment Issue">Consignment Issue</form:option>
									<form:option value="Hospbills">Hospbills</form:option>
									<form:option value="Pharmacy Discount">Pharmacy Discount</form:option>
									<form:option value="Inventory Transaction">Inventory Transaction</form:option>
									<form:option value="Payment">Payment</form:option>
									<form:option value="Payment Due">Payment Due</form:option>
									<form:option value="Payment Due">Pharma Bills</form:option>
									<form:option value="Payment Due">Purchase</form:option>
									<form:option value="Receipt">Receipt</form:option>
									<form:option value="Stock Consumption">Stock Consumption</form:option>
									<form:option value="Stock Transfer">Stock Transfer</form:option>
									<form:option value="Stock Returns">Stock Returns</form:option>
									<form:option value="User / Department Issue">User / Department Issue</form:option>
								</form:select>
							</div>
							<div class="wide column ui filter-buttons">
								<button type="submit" class="ui button basic">Search</button>
							</div>
						</div>
					</form:form>
					<c:if test="${zohosyncmessage != ''}"> 
						<div class="ui message message-container">
						  <i class="close icon"></i>
						  <span >${zohosyncmessage}</span>
						</div>
					</c:if>
				</div>			
			</div>
		<!-- Search filter section ends -->
		<c:url var="pst_url"  value="${url}" />
		<form:form method="post" action="${pst_url}" >
			<div class="ui grid padded stackable">			 
				<div class="eleven wide column details-division">
				<!-- Details section starts -->
				<c:if test="${results.size() > 0}"> 
					<div class="details-section">
					  <label class="thick-label-medium title-padding">VOUCHER DETAILS</label>
					  	<div class="details-block" >						
								<c:forEach items="${results}" var="result">
								<div class="details-heading">
										<input type="checkbox" class="hide" value="${result.id}" name="ids[]" checked> 
										<span class="details-title"></span>
										<p class="table-head-label" >
										<fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${result.journalRecords[0].billOpenDate}" />
										</p>
										
										<span class="details-title">Voucher Type: </span>									
										<p class="table-head-label" >
											<c:out value="${result.journalRecords[0].voucherType}" />
										</p>
										
										<span class="details-title">Voucher No: </span>
										<p class="table-head-label" >
											<c:out value="${result.journalRecords[0].voucherNo}" />
										</p>
										
										<span class="details-title ">Other Details: </span>
										<p class="details-truncate table-head-label" >
										Bill No.<c:out value="${result.journalRecords[0].voucherNo }" /> (MR No. MR000021: Child of Tejaswini Singh)
										</p>
										
										<c:if test="${result.status == true}"> 
											<span class="exported green-color" data-parameter="${result.journalRecords[0].voucherNo}" text="Synced">Exported, 22-01-2018</span>
										</c:if>
										
										<div class="ui fluid popup">
											<p> <fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${result.journalRecords[0].billOpenDate}" /> </p>
											<table class="hover-table">
												<thead>
													<tr class="hover-table">
														<th></th>
														<th></th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${log.voucherId == result.journalRecords[0].voucherNo}">												
														<c:forEach items="${logs}" var="log">
															<tr class="hover-table" >
																<td> <fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${log.syncDate}" /></td>
																<td> <c:out value="${log.reason}" /></td>
															</tr>
														</c:forEach>
													</c:if>
													
												</tbody>
											</table>
										</div>
										
										<c:if test="${result.status == false || result.status == null}">
											<span class="exported red-color" style="padding: 0px;">
												<c:out value="Error, 22-01-2018" />
											</span>											
										</c:if>
										
										<%-- <span class="exported red-color" th:if="${result.status == false || result.status == null}" th:text="Error">Error, 22-01-2018</span> --%>
										<div class="ui fluid popup">
											<p >
											<c:out value="${result.journalRecords[0].billOpenDate}" />
											<table class="hover-table">
												<thead>
													<tr>
														<th></th>
														<th></th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${log.voucherId == result.journalRecords[0].voucherNo}">												
														<c:forEach items="${logs}" var="log">
															<tr class="hover-table" >
																<td> <fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${log.syncDate}" /> </td>
																<td> <c:out value="${log.reason}" /></td>
															</tr>
														</c:forEach>
													</c:if>												
												</tbody>
											</table>
										</div>
								</div>
								
								<div class="details-table summary-table">
									<table class="ui unstackable table">
										<thead>
											<tr>
												<th>Particulars</th>
												<th colspan="2">Reference Details:</th>
												<th class="text-right">Debit</th>
												<th class="text-right">Credit</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${result.journalRecords}" var="journalRecord">
										<tr>
											<td> <c:out value="${journalRecord.creditAccount}" /></td>
											<td colspan="2">BL000023, Child Tejaswi Singh</td>
											<td class="text-right"><div ><c:out value="${journalRecord.accountType=='debit'? journalRecord.netAmount : '0.00'}" /></div></td>
											<td class="text-right"><div ><c:out value="${journalRecord.accountType=='credit'? journalRecord.netAmount : '0.00'}" /></div></td>
										</tr>
										</c:forEach>
										</tbody>
									</table>
								</div>
							 </c:forEach>						
							
						</div>
					</div>	
				</c:if>
					
				<!-- Details section ends -->
				</div>
				<!-- Summary section starts -->
				<c:if test="${summary.size() > 0}">
					<div class="five wide column summary-block">
						<label class="thick-label-medium title-padding">SUMMARY</label><br>
						<label class="thick-label-medium">Receipt and Claims</label>
						<div class="summary-table">
							<table class="table-bottom-no-border ui unstackable table">
								<thead>
									<tr>
										<th colspan="3">Account</th>
										<th class="text-right">Debit</th>
										<th class="text-right">Credit</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${summary}" var="instance">
										<tr>
											<td colspan="3" > <c:out value="${instance.key}" /></td>
											<c:forEach items="${instance.value}" var="val">
											 <td class="text-right">
											 	<div>											 		
											 		<fmt:formatNumber value="${val.key == 'debit' ? val.value : '0.00'}" pattern="0.00"/>
											 	</div>
											 </td>
											</c:forEach>
											<c:forEach items="${instance.value}" var="val">
											 <td class="text-right">
											 	<div>
											 		<fmt:formatNumber value="${val.key == 'credit' ? val.value : '0.00'}" pattern="0.00"/>
											 	</div>
											 </td>
											</c:forEach>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</c:if>			
				
					
					<div class="ui tiny modal credential-modal">
						<a class="close pad0 modal-close" href="/practo">Close</a>
							<div class="header">
								<h3 class="ui header">Modify Credentials</h3>
							</div>
							<div class="ui grid padded">
								<div class="ui row fluid icon input stackable padded">
									<label class="ui eight wide column">Authtoken</label>
									<div class="ui seven wide column">
										<input type="text" name="Authtoken">
									</div>
								</div>
								<div class="ui row fluid icon input stackable padded">
									<label class="ui eight wide column">Organization id</label>
									<div class="ui seven wide column">
										<input type="text" name="Organization id">
									</div>
								</div>
							</div>
							<div class="actions">
								<button type="button" class="ui button">Update</button>
							</div>
					</div>
					
				<!-- Summary section ends -->
				<div class="footer">
					<a class="footer-link" href="/practo/logModal">Audit Log</a>
					<a class="footer-link" id='credential-modal'>Modify Credentials</a>
					<button type="submit" class="ui button basic f-right" id="zohoSync">Export</button>
				</div>
			</div>
			</form:form>
		</div>
		<!-- Main wrapper ends -->
	</div>
</body>
</html>
