package com.practo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "journal_log")
public class JournalLog implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="sync_date")
	private Date syncDate;
	
	@Column(name="group_id")
	private Long groupId;
	
	@Column(name="sync_status")
	private String syncStatus;
	
	@Column(name="reason")
	private String reason;
	
	@Column(name="voucher_id")
	private String voucherId;
	
	@Column(name="guid")
	private String guid;
	
	@Column(name="journal_id")
	private Long journal_id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(String syncStatus) {
		this.syncStatus = syncStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId.trim();
	}
	
	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	public Long getJournalId() {
		return journal_id;
	}

	public void setJournalId(Long journal_id) {
		this.journal_id = journal_id;
	}

	public JournalLog(Date syncDate, Long groupId, String syncStatus, String reason, Long journal_id, String voucherId, String guid) {
		super();
		this.syncDate = syncDate;
		this.groupId = groupId;
		this.syncStatus = syncStatus;
		this.reason = reason;
		this.voucherId = voucherId;
		this.guid = guid;
		this.journal_id = journal_id;
	}

	public JournalLog() {
	}
}
