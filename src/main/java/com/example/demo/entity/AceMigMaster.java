package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACE_MIG_MASTER")
public class AceMigMaster {
	
	@Id
	@Column(name = "LGC_CUSTOMER_ID")
	private Long legacyCusomerId;
	
	@Column(name="TAR_CUSTOMER_ID")
	private String targetCustomerId;
	
	@Column(name = "CREATE_DATE")
	private Date createDate;
	
	@Column(name = "UPDATE_DATE")
	private Date updateDate;
	
	@Column(name = "PROC_IND")
	private String procIndicator;
	
	@Column(name = "PROC_DESCRIPTION")
	private String procDescription;
	
	@Column(name = "EXEC_SEQ")
	private String executionSequence;

	public Long getLegacyCusomerId() {
		return legacyCusomerId;
	}

	public void setLegacyCusomerId(Long legacyCusomerId) {
		this.legacyCusomerId = legacyCusomerId;
	}

	public String getTargetCustomerId() {
		return targetCustomerId;
	}

	public void setTargetCustomerId(String targetCustomerId) {
		this.targetCustomerId = targetCustomerId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getProcIndicator() {
		return procIndicator;
	}

	public void setProcIndicator(String procIndicator) {
		this.procIndicator = procIndicator;
	}

	public String getProcDescription() {
		return procDescription;
	}

	public void setProcDescription(String procDescription) {
		this.procDescription = procDescription;
	}

	public String getExecutionSequence() {
		return executionSequence;
	}

	public void setExecutionSequence(String executionSequence) {
		this.executionSequence = executionSequence;
	}

}
