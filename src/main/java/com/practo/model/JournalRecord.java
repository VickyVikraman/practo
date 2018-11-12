package com.practo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "journal_record")
public class JournalRecord implements Serializable {

  private static final long serialVersionUID = -3465813074586302847L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="receipt_store_center")
  @JsonProperty("receipt_store_center")
  private String receiptStoreCenter;

  @Column(name="transaction_type")
  @JsonProperty("transaction_type")
  private String transactionType;

  @Column(name="po_number")
  @JsonProperty("po_number")
  private String poNumber;

  @Column(name="account_group")
  @JsonProperty("account_group")
  private int accountGroup;

  @Column(name="voucher_date")
  @JsonProperty("voucher_date")
  private Date voucherDate;

  @Column(name="gross_amount", precision=10, scale=2)
  @JsonProperty("gross_amount")
  private BigDecimal grossAmount;

  @Column(name="item_code")
  @JsonProperty("item_code")
  private String itemCode;

  @Column(name="round_off_amount", precision=10, scale=2)
  @JsonProperty("round_off_amount")
  private BigDecimal  roundOffAmount;

  @Column(name="voucher_no")
  @JsonProperty("voucher_no")
  private String voucherNo;

  @Column(name="grn_date")
  @JsonProperty("grn_date")
  private String grnDate;

  @Column(name="voucher_ref")
  @JsonProperty("voucher_ref")
  private String voucherRef;

  @Column(name="outhouse_name")
  @JsonProperty("outhouse_name")
  private String outhouseName;

  @Column(name="quantity", precision=10, scale=2)
  @JsonProperty("quantity")
  private BigDecimal quantity;

  @Column(name="visit_type")
  @JsonProperty("visit_type")
  private String visitType;

  @Column(name="bill_open_date")
  @JsonProperty("bill_open_date")
  private Date billOpenDate;

  @Column(name="prescribing_doctor")
  @JsonProperty("prescribing_doctor")
  private String prescribingDoctor;

  @Column(name="payee_doctor")
  @JsonProperty("payee_doctor")
  private String payeeDoctor;

  @Column(name="custom_1")
  @JsonProperty("custom_1")
  private String custom1;

  @Column(name="points_redeemed")
  @JsonProperty("points_redeemed")
  private int pointsRedeemed;

  @Column(name="custom_4")
  @JsonProperty("custom_4")
  private String custom4;

  @Column(name="points_redeemed_amount", precision=10, scale=2)
  @JsonProperty("points_redeemed_amount")
  private BigDecimal pointsRedeemedAmount;

  @Column(name="custom_3")
  @JsonProperty("custom_3")
  private String custom3;

  @Column(name="is_tpa")
  @JsonProperty("is_tpa")
  private String isTpa;

  @Column(name="custom_2")
  @JsonProperty("custom_2")
  private String custom2;

  @Column(name="supplier_name")
  @JsonProperty("supplier_name")
  private String supplierName;

  @Column(name="custom_9")
  @JsonProperty("custom_9")
  private String custom9;

  @Column(name="custom_8")
  @JsonProperty("custom_8")
  private String custom8;

  @Column(name="custom_7")
  @JsonProperty("custom_7")
  private String custom7;

  @Column(name="discount_amount", precision=10, scale=2)
  @JsonProperty("discount_amount")
  private BigDecimal discountAmount;

  @Column(name="cost_amount")
  @JsonProperty("cost_amount")
  private int costAmount;

  @Column(name="purchase_vat_amount", precision=10, scale=2)
  @JsonProperty("purchase_vat_amount")
  private BigDecimal purchaseVatAmount;

  @Column(name="center_name")
  @JsonProperty("center_name")
  private String centerName;

  @Column(name="mod_time")
  @JsonProperty("mod_time")
  private Date modTime;

  @Column(name="item_name")
  @JsonProperty("item_name")
  private String itemName;

  @Column(name="sales_vat_percent", precision=10, scale=2)
  @JsonProperty("sales_vat_percent")
  private BigDecimal salesVatPercent;

  @Column(name="visit_id")
  @JsonProperty("visit_id")
  private String visitId;

  @Column(name="admitting_doctor")
  @JsonProperty("admitting_doctor")
  private String admittingDoctor;

  @Column(name="invoice_no")
  @JsonProperty("invoice_no")
  private String invoiceNo;

  @Column(name="charge_head")
  @JsonProperty("charge_head")
  private String chargeHead;

  @Column(name="mr_no")
  @JsonProperty("mr_no")
  private String mrNo;

  @Column(name="receipt_store")
  @JsonProperty("receipt_store")
  private String receiptStore;

  @Column(name="update_status")
  @JsonProperty("update_status")
  private int updateStatus;

  @Column(name="net_amount", precision=10, scale=2)
  @JsonProperty("net_amount")
  private Double netAmount;

  @Column(name="issue_store_center")
  @JsonProperty("issue_store_center")
  private String issueStoreCenter;

  @Column(name="points_redeemed_rate", precision=10, scale=2)
  @JsonProperty("points_redeemed_rate")
  private BigDecimal pointsRedeemedRate;

  @Column(name="custom_11")
  @JsonProperty("custom_11")
  private String custom11;

  @Column(name="custom_10")
  @JsonProperty("custom_10")
  private String custom10;

  @Column(name="remarks")
  @JsonProperty("remarks")
  private String remarks;

  @Column(name="invoice_date")
  @JsonProperty("invoice_date")
  private Date invoiceDate;

  @Column(name="unit_rate", precision=10, scale=2)
  @JsonProperty("unit_rate")
  private BigDecimal unitRate;

  @Column(name="cust_item_code")
  @JsonProperty("cust_item_code")
  private String custItemCode;

  @Column(name="audit_control_number")
  @JsonProperty("audit_control_number")
  private String auditControlNumber;

  @Column(name="cust_supplier_code")
  @JsonProperty("cust_supplier_code")
  private String custSupplierCode;

  @Column(name="currency")
  @JsonProperty("currency")
  private String currency;

  @Column(name="old_mr_no")
  @JsonProperty("old_mr_no")
  private String oldMrNo;

  @Column(name="insurance_co")
  @JsonProperty("insurance_co")
  private String insuranceCo;

  @Column(name="counter_no")
  @JsonProperty("counter_no")
  private String counterNo;

  @Column(name="po_date")
  @JsonProperty("po_date")
  private Date poDate;

  @Column(name="tax_amount", precision=10, scale=2)
  @JsonProperty("tax_amount")
  private BigDecimal taxAmount;

  @Column(name="item_category_id")
  @JsonProperty("item_category_id")
  private int itemCategoryId;

  @Column(name="issue_store")
  @JsonProperty("issue_store")
  private String issueStore;

  @Column(name="credit_account")
  @JsonProperty("credit_account")
  private String creditAccount;

  @Column(name="currency_conversion_rate", precision=10, scale=2)
  @JsonProperty("currency_conversion_rate")
  private BigDecimal currencyConversionRate;

  @Column(name="debit_account")
  @JsonProperty("debit_account")
  private String debitAccount;

  @Column(name="bill_no")
  @JsonProperty("bill_no")
  private String billNo;

  @Column(name="conducting_department")
  @JsonProperty("conducting_department")
  private String conductingDepartment;

  @Column(name="voucher_type")
  @JsonProperty("voucher_type")
  private String voucherType;

  @Column(name="conductiong_doctor")
  @JsonProperty("conductiong_doctor")
  private String conductiongDoctor;

  @Column(name="incoimng_hospital")
  @JsonProperty("incoimng_hospital")
  private String incoimngHospital;

  @Column(name="bill_finalized_date")
  @JsonProperty("bill_finalized_date")
  private Date billFinalizedDate;

  @Column(name="referral_doctor")
  @JsonProperty("referral_doctor")
  private String referralDoctor;

  @Column(name="guid")
  @JsonProperty("guid")
  private String guid;

  @Column(name="unit")
  @JsonProperty("unit")
  private String unit;

  @Column(name="admitting_department")
  @JsonProperty("admitting_department")
  private String admittingDepartment;

  @Column(name="service_sub_group")
  @JsonProperty("service_sub_group")
  private String serviceSubGroup;

  @Column(name="charge_group")
  @JsonProperty("charge_group")
  private String chargeGroup;

  @Column(name="center_id")
  @JsonProperty("center_id")
  private int centerId;

  @Column(name="sales_vat_amount", precision=10, scale=2)
  @JsonProperty("sales_vat_amount")
  private BigDecimal salesVatAmount;

  @Column(name="purchase_vat_percent", precision=10, scale=2)
  @JsonProperty("purchase_vat_percent")
  private BigDecimal purchaseVatPercent;

  @Column(name="service_group")
  @JsonProperty("service_group")
  private String serviceGroup;
  
  @Column(name="account_type")
  private String accountType;
  
  @Column(name="journal_id")
  private Long journal_id;
	
  	  public Long getId() {
	    return id;
	  }

	  public String getReceiptStoreCenter() {
	    return receiptStoreCenter;
	  }

	  public void setReceiptStoreCenter(String receiptStoreCenter) {
	    this.receiptStoreCenter = receiptStoreCenter.trim();
	  }

	  public String getTransactionType() {
	    return transactionType;
	  }

	  public void setTransactionType(String transactionType) {
	    this.transactionType = transactionType.trim();
	  }

	  public String getPoNumber() {
	    return poNumber;
	  }

	  public void setPoNumber(String poNumber) {
	    this.poNumber = poNumber.trim();
	  }

	  public int getAccountGroup() {
	    return accountGroup;
	  }

	  public void setAccountGroup(int accountGroup) {
	    this.accountGroup = accountGroup;
	  }

	  public Date getVoucherDate() {
	    return voucherDate;
	  }

	  public void setVoucherDate(Date voucherDate) {
	    this.voucherDate = voucherDate;
	  }

	  public BigDecimal getGrossAmount() {
	    return grossAmount;
	  }

	  public void setGrossAmount(BigDecimal grossAmount) {
	    this.grossAmount = grossAmount;
	  }

	  public String getItemCode() {
	    return itemCode;
	  }

	  public void setItemCode(String itemCode) {
	    this.itemCode = itemCode.trim();
	  }

	  public BigDecimal getRoundOffAmount() {
	    return roundOffAmount;
	  }

	  public void setRoundOffAmount(BigDecimal roundOffAmount) {
	    this.roundOffAmount = roundOffAmount;
	  }

	  public String getVoucherNo() {
	    return voucherNo;
	  }

	  public void setVoucherNo(String voucherNo) {
	    this.voucherNo = voucherNo.trim();
	  }

	  public String getGrnDate() {
	    return grnDate;
	  }

	  public void setGrnDate(String grnDate) {
	    this.grnDate = grnDate;
	  }

	  public String getVoucherRef() {
	    return voucherRef;
	  }

	  public void setVoucherRef(String voucherRef) {
	    this.voucherRef = voucherRef.trim();
	  }

	  public String getOuthouseName() {
	    return outhouseName;
	  }

	  public void setOuthouseName(String outhouseName) {
	    this.outhouseName = outhouseName.trim();
	  }

	  public BigDecimal getQuantity() {
	    return quantity;
	  }

	  public void setQuantity(BigDecimal quantity) {
	    this.quantity = quantity;
	  }

	  public String getVisitType() {
	    return visitType;
	  }

	  public void setVisitType(String visitType) {
	    this.visitType = visitType.trim();
	  }

	  public Date getBillOpenDate() {
	    return billOpenDate;
	  }

	  public void setBillOpenDate(Date billOpenDate) {
	    this.billOpenDate = billOpenDate;
	  }

	  public String getPrescribingDoctor() {
	    return prescribingDoctor;
	  }

	  public void setPrescribingDoctor(String prescribingDoctor) {
	    this.prescribingDoctor = prescribingDoctor.trim();
	  }

	  public String getPayeeDoctor() {
	    return payeeDoctor;
	  }

	  public void setPayeeDoctor(String payeeDoctor) {
	    this.payeeDoctor = payeeDoctor.trim();
	  }

	  public String getCustom1() {
	    return custom1;
	  }

	  public void setCustom1(String custom1) {
	    this.custom1 = custom1.trim();
	  }

	  public int getPointsRedeemed() {
	    return pointsRedeemed;
	  }

	  public void setPointsRedeemed(int pointsRedeemed) {
	    this.pointsRedeemed = pointsRedeemed;
	  }

	  public String getCustom4() {
	    return custom4;
	  }

	  public void setCustom4(String custom4) {
	    this.custom4 = custom4.trim();
	  }

	  public BigDecimal getPointsRedeemedAmount() {
	    return pointsRedeemedAmount;
	  }

	  public void setPointsRedeemedAmount(BigDecimal pointsRedeemedAmount) {
	    this.pointsRedeemedAmount = pointsRedeemedAmount;
	  }

	  public String getCustom3() {
	    return custom3;
	  }

	  public void setCustom3(String custom3) {
	    this.custom3 = custom3.trim();
	  }

	  public String getIsTpa() {
	    return isTpa;
	  }

	  public void setIsTpa(String isTpa) {
	    this.isTpa = isTpa.trim();
	  }

	  public String getCustom2() {
	    return custom2;
	  }

	  public void setCustom2(String custom2) {
	    this.custom2 = custom2.trim();
	  }

	  public String getSupplierName() {
	    return supplierName;
	  }

	  public void setSupplierName(String supplierName) {
	    this.supplierName = supplierName.trim();
	  }

	  public String getCustom9() {
	    return custom9;
	  }

	  public void setCustom9(String custom9) {
	    this.custom9 = custom9.trim();
	  }

	  public String getCustom8() {
	    return custom8;
	  }

	  public void setCustom8(String custom8) {
	    this.custom8 = custom8.trim();
	  }

	  public String getCustom7() {
	    return custom7;
	  }

	  public void setCustom7(String custom7) {
	    this.custom7 = custom7.trim();
	  }

	  public BigDecimal getDiscountAmount() {
	    return discountAmount;
	  }

	  public void setDiscountAmount(BigDecimal discountAmount) {
	    this.discountAmount = discountAmount;
	  }

	  public int getCostAmount() {
	    return costAmount;
	  }

	  public void setCostAmount(int costAmount) {
	    this.costAmount = costAmount;
	  }

	  public BigDecimal getPurchaseVatAmount() {
	    return purchaseVatAmount;
	  }

	  public void setPurchaseVatAmount(BigDecimal purchaseVatAmount) {
	    this.purchaseVatAmount = purchaseVatAmount;
	  }

	  public String getCenterName() {
	    return centerName;
	  }

	  public void setCenterName(String centerName) {
	    this.centerName = centerName.trim();
	  }

	  public Date getModTime() {
	    return modTime;
	  }

	  public void setModTime(Date modTime) {
	    this.modTime = modTime;
	  }

	  public String getItemName() {
	    return itemName;
	  }

	  public void setItemName(String itemName) {
	    this.itemName = itemName.trim();
	  }

	  public BigDecimal getSalesVatPercent() {
	    return salesVatPercent;
	  }

	  public void setSalesVatPercent(BigDecimal salesVatPercent) {
	    this.salesVatPercent = salesVatPercent;
	  }

	  public String getVisitId() {
	    return visitId;
	  }

	  public void setVisitId(String visitId) {
	    this.visitId = visitId.trim();
	  }

	  public String getAdmittingDoctor() {
	    return admittingDoctor;
	  }

	  public void setAdmittingDoctor(String admittingDoctor) {
	    this.admittingDoctor = admittingDoctor.trim();
	  }

	  public String getInvoiceNo() {
	    return invoiceNo;
	  }

	  public void setInvoiceNo(String invoiceNo) {
	    this.invoiceNo = invoiceNo.trim();
	  }

	  public String getChargeHead() {
	    return chargeHead;
	  }

	  public void setChargeHead(String chargeHead) {
	    this.chargeHead = chargeHead.trim();
	  }

	  public String getMrNo() {
	    return mrNo;
	  }

	  public void setMrNo(String mrNo) {
	    this.mrNo = mrNo.trim();
	  }

	  public String getReceiptStore() {
	    return receiptStore;
	  }

	  public void setReceiptStore(String receiptStore) {
	    this.receiptStore = receiptStore.trim();
	  }

	  public int getUpdateStatus() {
	    return updateStatus;
	  }

	  public void setUpdateStatus(int updateStatus) {
	    this.updateStatus = updateStatus;
	  }

	  public Double getNetAmount() {
	    return netAmount;
	  }

	  public void setNetAmount(Double netAmount) {
	    this.netAmount = netAmount;
	  }

	  public String getIssueStoreCenter() {
	    return issueStoreCenter;
	  }

	  public void setIssueStoreCenter(String issueStoreCenter) {
	    this.issueStoreCenter = issueStoreCenter.trim();
	  }

	  public BigDecimal getPointsRedeemedRate() {
	    return pointsRedeemedRate;
	  }

	  public void setPointsRedeemedRate(BigDecimal pointsRedeemedRate) {
	    this.pointsRedeemedRate = pointsRedeemedRate;
	  }

	  public String getCustom11() {
	    return custom11;
	  }

	  public void setCustom11(String custom11) {
	    this.custom11 = custom11.trim();
	  }

	  public String getCustom10() {
	    return custom10;
	  }

	  public void setCustom10(String custom10) {
	    this.custom10 = custom10.trim();
	  }

	  public String getRemarks() {
	    return remarks;
	  }

	  public void setRemarks(String remarks) {
	    this.remarks = remarks.trim();
	  }

	  public Date getInvoiceDate() {
	    return invoiceDate;
	  }

	  public void setInvoiceDate(Date invoiceDate) {
	    this.invoiceDate = invoiceDate;
	  }

	  public BigDecimal getUnitRate() {
	    return unitRate;
	  }

	  public void setUnitRate(BigDecimal unitRate) {
	    this.unitRate = unitRate;
	  }

	  public String getCustItemCode() {
	    return custItemCode;
	  }

	  public void setCustItemCode(String custItemCode) {
	    this.custItemCode = custItemCode.trim();
	  }

	  public String getAuditControlNumber() {
	    return auditControlNumber;
	  }

	  public void setAuditControlNumber(String auditControlNumber) {
	    this.auditControlNumber = auditControlNumber.trim();
	  }

	  public String getCustSupplierCode() {
	    return custSupplierCode;
	  }

	  public void setCustSupplierCode(String custSupplierCode) {
	    this.custSupplierCode = custSupplierCode.trim();
	  }

	  public String getCurrency() {
	    return currency;
	  }

	  public void setCurrency(String currency) {
	    this.currency = currency.trim();
	  }

	  public String getOldMrNo() {
	    return oldMrNo;
	  }

	  public void setOldMrNo(String oldMrNo) {
	    this.oldMrNo = oldMrNo.trim();
	  }

	  public String getInsuranceCo() {
	    return insuranceCo;
	  }

	  public void setInsuranceCo(String insuranceCo) {
	    this.insuranceCo = insuranceCo.trim();
	  }

	  public String getCounterNo() {
	    return counterNo;
	  }

	  public void setCounterNo(String counterNo) {
	    this.counterNo = counterNo.trim();
	  }

	  public Date getPoDate() {
	    return poDate;
	  }

	  public void setPoDate(Date poDate) {
	    this.poDate = poDate;
	  }

	  public BigDecimal getTaxAmount() {
	    return taxAmount;
	  }

	  public void setTaxAmount(BigDecimal taxAmount) {
	    this.taxAmount = taxAmount;
	  }

	  public int getItemCategoryId() {
	    return itemCategoryId;
	  }

	  public void setItemCategoryId(int itemCategoryId) {
	    this.itemCategoryId = itemCategoryId;
	  }

	  public String getIssueStore() {
	    return issueStore;
	  }

	  public void setIssueStore(String issueStore) {
	    this.issueStore = issueStore.trim();
	  }

	  public String getCreditAccount() {
	    return creditAccount;
	  }

	  public void setCreditAccount(String creditAccount) {
	    this.creditAccount = creditAccount.trim();
	  }

	  public BigDecimal getCurrencyConversionRate() {
	    return currencyConversionRate;
	  }

	  public void setCurrencyConversionRate(BigDecimal currencyConversionRate) {
	    this.currencyConversionRate = currencyConversionRate;
	  }

	  public String getDebitAccount() {
	    return debitAccount;
	  }

	  public void setDebitAccount(String debitAccount) {
	    this.debitAccount = debitAccount.trim();
	  }

	  public String getBillNo() {
	    return billNo;
	  }

	  public void setBillNo(String billNo) {
	    this.billNo = billNo.trim();
	  }

	  public String getConductingDepartment() {
	    return conductingDepartment;
	  }

	  public void setConductingDepartment(String conductingDepartment) {
	    this.conductingDepartment = conductingDepartment.trim();
	  }

	  public String getVoucherType() {
	    return voucherType;
	  }

	  public void setVoucherType(String voucherType) {
	    this.voucherType = voucherType.trim();
	  }

	  public String getConductiongDoctor() {
	    return conductiongDoctor;
	  }

	  public void setConductiongDoctor(String conductiongDoctor) {
	    this.conductiongDoctor = conductiongDoctor.trim();
	  }

	  public String getIncoimngHospital() {
	    return incoimngHospital;
	  }

	  public void setIncoimngHospital(String incoimngHospital) {
	    this.incoimngHospital = incoimngHospital.trim();
	  }

	  public Date getBillFinalizedDate() {
	    return billFinalizedDate;
	  }

	  public void setBillFinalizedDate(Date billFinalizedDate) {
	    this.billFinalizedDate = billFinalizedDate;
	  }

	  public String getReferralDoctor() {
	    return referralDoctor;
	  }

	  public void setReferralDoctor(String referralDoctor) {
	    this.referralDoctor = referralDoctor.trim();
	  }

	  public String getGuid() {
	    return guid;
	  }

	  public void setGuid(String guid) {
	    this.guid = guid.trim();
	  }

	  public String getUnit() {
	    return unit;
	  }

	  public void setUnit(String unit) {
	    this.unit = unit.trim();
	  }

	  public String getAdmittingDepartment() {
	    return admittingDepartment;
	  }

	  public void setAdmittingDepartment(String admittingDepartment) {
	    this.admittingDepartment = admittingDepartment.trim();
	  }

	  public String getServiceSubGroup() {
	    return serviceSubGroup;
	  }

	  public void setServiceSubGroup(String serviceSubGroup) {
	    this.serviceSubGroup = serviceSubGroup.trim();
	  }

	  public String getChargeGroup() {
	    return chargeGroup;
	  }

	  public void setChargeGroup(String chargeGroup) {
	    this.chargeGroup = chargeGroup.trim();
	  }

	  public int getCenterId() {
	    return centerId;
	  }

	  public void setCenterId(int centerId) {
	    this.centerId = centerId;
	  }

	  public BigDecimal getSalesVatAmount() {
	    return salesVatAmount;
	  }

	  public void setSalesVatAmount(BigDecimal salesVatAmount) {
	    this.salesVatAmount = salesVatAmount;
	  }

	  public BigDecimal getPurchaseVatPercent() {
	    return purchaseVatPercent;
	  }

	  public void setPurchaseVatPercent(BigDecimal purchaseVatPercent) {
	    this.purchaseVatPercent = purchaseVatPercent;
	  }

	  public String getServiceGroup() {
	    return serviceGroup;
	  }

	  public void setServiceGroup(String serviceGroup) {
	    this.serviceGroup = serviceGroup.trim();
	  }
	  
	  public String getAccountType() {
		return accountType;
	  }
		
	  public void setAccountType(String accountType) {
		this.accountType = accountType;
	  }
	  
	  public Long getJournalId() {
	    return journal_id;
	  }

	  public void setJournalId(Long journal_id) {
	    this.journal_id = journal_id;
	  }

	  public JournalRecord(String receiptStoreCenter, String transactionType, String poNumber, int accountGroup,
				Date voucherDate, BigDecimal grossAmount, String itemCode, BigDecimal roundOffAmount, String voucherNo,
				String grnDate, String voucherRef, String outhouseName, BigDecimal quantity, String visitType,
				Date billOpenDate, String prescribingDoctor, String payeeDoctor, String custom1, int pointsRedeemed,
				String custom4, BigDecimal pointsRedeemedAmount, String custom3, String isTpa, String custom2,
				String supplierName, String custom9, String custom8, String custom7, BigDecimal discountAmount, int costAmount,
				BigDecimal purchaseVatAmount, String centerName, Date modTime, String itemName, BigDecimal salesVatPercent,
				String visitId, String admittingDoctor, String invoiceNo, String chargeHead, String mrNo, String receiptStore,
				int updateStatus, Double netAmount, String issueStoreCenter, BigDecimal pointsRedeemedRate, String custom11,
				String custom10, String remarks, Date invoiceDate, BigDecimal unitRate, String custItemCode,
				String auditControlNumber, String custSupplierCode, String currency, String oldMrNo, String insuranceCo,
				String counterNo, Date poDate, BigDecimal taxAmount, int itemCategoryId, String issueStore,
				String creditAccount, BigDecimal currencyConversionRate, String debitAccount, String billNo,
				String conductingDepartment, String voucherType, String conductiongDoctor, String incoimngHospital,
				Date billFinalizedDate, String referralDoctor, String guid, String unit, String admittingDepartment,
				String serviceSubGroup, String chargeGroup, int centerId, BigDecimal salesVatAmount,
				BigDecimal purchaseVatPercent, String serviceGroup, Long journal_id) {
			super();
			this.receiptStoreCenter = receiptStoreCenter;
			this.transactionType = transactionType;
			this.poNumber = poNumber;
			this.accountGroup = accountGroup;
			this.voucherDate = voucherDate;
			this.grossAmount = grossAmount;
			this.itemCode = itemCode;
			this.roundOffAmount = roundOffAmount;
			this.voucherNo = voucherNo;
			this.grnDate = grnDate;
			this.voucherRef = voucherRef;
			this.outhouseName = outhouseName;
			this.quantity = quantity;
			this.visitType = visitType;
			this.billOpenDate = billOpenDate;
			this.prescribingDoctor = prescribingDoctor;
			this.payeeDoctor = payeeDoctor;
			this.custom1 = custom1;
			this.pointsRedeemed = pointsRedeemed;
			this.custom4 = custom4;
			this.pointsRedeemedAmount = pointsRedeemedAmount;
			this.custom3 = custom3;
			this.isTpa = isTpa;
			this.custom2 = custom2;
			this.supplierName = supplierName;
			this.custom9 = custom9;
			this.custom8 = custom8;
			this.custom7 = custom7;
			this.discountAmount = discountAmount;
			this.costAmount = costAmount;
			this.purchaseVatAmount = purchaseVatAmount;
			this.centerName = centerName;
			this.modTime = modTime;
			this.itemName = itemName;
			this.salesVatPercent = salesVatPercent;
			this.visitId = visitId;
			this.admittingDoctor = admittingDoctor;
			this.invoiceNo = invoiceNo;
			this.chargeHead = chargeHead;
			this.mrNo = mrNo;
			this.receiptStore = receiptStore;
			this.updateStatus = updateStatus;
			this.netAmount = netAmount;
			this.issueStoreCenter = issueStoreCenter;
			this.pointsRedeemedRate = pointsRedeemedRate;
			this.custom11 = custom11;
			this.custom10 = custom10;
			this.remarks = remarks;
			this.invoiceDate = invoiceDate;
			this.unitRate = unitRate;
			this.custItemCode = custItemCode;
			this.auditControlNumber = auditControlNumber;
			this.custSupplierCode = custSupplierCode;
			this.currency = currency;
			this.oldMrNo = oldMrNo;
			this.insuranceCo = insuranceCo;
			this.counterNo = counterNo;
			this.poDate = poDate;
			this.taxAmount = taxAmount;
			this.itemCategoryId = itemCategoryId;
			this.issueStore = issueStore;
			this.creditAccount = creditAccount;
			this.currencyConversionRate = currencyConversionRate;
			this.debitAccount = debitAccount;
			this.billNo = billNo;
			this.conductingDepartment = conductingDepartment;
			this.voucherType = voucherType;
			this.conductiongDoctor = conductiongDoctor;
			this.incoimngHospital = incoimngHospital;
			this.billFinalizedDate = billFinalizedDate;
			this.referralDoctor = referralDoctor;
			this.guid = guid;
			this.unit = unit;
			this.admittingDepartment = admittingDepartment;
			this.serviceSubGroup = serviceSubGroup;
			this.chargeGroup = chargeGroup;
			this.centerId = centerId;
			this.salesVatAmount = salesVatAmount;
			this.purchaseVatPercent = purchaseVatPercent;
			this.serviceGroup = serviceGroup;
			this.journal_id = journal_id;
		}

	  protected JournalRecord() {
	  }
}
