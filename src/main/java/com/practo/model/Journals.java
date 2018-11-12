package com.practo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "journal")
public class Journals implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;
	
	@Transient
	private List<JournalRecord> journalRecords;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String billNo;

	@Column
	private Date voucherDate;

	@Column
	private String voucherType;
	
	@Column
	private Date billOpenDate;
	
	@Column
	private Boolean Status;

	@Column
	private int recordCount;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Date getVoucherDate() {
		return voucherDate;
	}

	public void setVoucherDate(Date voucherDate) {
		this.voucherDate = voucherDate;
	}

	public String getVoucherType() {
		return voucherType;
	}

	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}

	public Date getBillOpenDate() {
		return billOpenDate;
	}

	public void setBillOpenDate(Date billOpenDate) {
		this.billOpenDate = billOpenDate;
	}
	
	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}
    
	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	
	public void  setJournalRecords(List<JournalRecord> jl){
		this.journalRecords = jl;
	}
	
	public List<JournalRecord> getJournalRecords(){
		return journalRecords;
	}

	public Journals(String billNo, Date voucherDate, String voucherType, Date billOpenDate) {
		this.billNo = billNo;
		this.voucherDate = voucherDate;
		this.voucherType = voucherType;
		this.billOpenDate = billOpenDate;
	}

	public Journals() {
	}
}