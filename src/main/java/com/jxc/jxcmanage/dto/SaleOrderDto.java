package com.jxc.jxcmanage.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.util.ObjectUtils;

import com.jxc.jxcmanage.constants.Constant;

import cn.hutool.core.date.DateUtil;

public class SaleOrderDto extends BaseDto{
	private Long id;

	private Long customerId;

	private Long accountId;

	private BigDecimal amount;

	private String receiverMobile;

	private String expressMobile;

	private String receiver;

	private String deliverer;

	private String saleDate;

	private String receiveDate;

	private String batch;

	private String note;

	private Integer status;

	private String createdBy;

	private String updatedBy;

	private Date createdDate;

	private Date updatedDate;
	private Date startDate;

	private Date endDate;
	private String createdDateDisplay;

	private String updatedDateDisplay;
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile == null ? null : receiverMobile.trim();
	}

	public String getExpressMobile() {
		return expressMobile;
	}

	public void setExpressMobile(String expressMobile) {
		this.expressMobile = expressMobile == null ? null : expressMobile.trim();
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver == null ? null : receiver.trim();
	}

	public String getDeliverer() {
		return deliverer;
	}

	public void setDeliverer(String deliverer) {
		this.deliverer = deliverer == null ? null : deliverer.trim();
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate == null ? null : saleDate.trim();
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate == null ? null : receiveDate.trim();
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch == null ? null : batch.trim();
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy == null ? null : createdBy.trim();
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy == null ? null : updatedBy.trim();
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getCreatedDateDisplay() {
		if (!ObjectUtils.isEmpty(createdDate)) {
			createdDateDisplay = DateUtil.format(createdDate, Constant.TIME);
		}
		return createdDateDisplay;
	}

	public void setCreatedDateDisplay(String createdDateDisplay) {
		this.createdDateDisplay = createdDateDisplay;
	}

	public String getUpdatedDateDisplay() {
		if (!ObjectUtils.isEmpty(updatedDate)) {
			updatedDateDisplay = DateUtil.format(updatedDate, Constant.TIME);
		}
		return updatedDateDisplay;
	}

	public void setUpdatedDateDisplay(String updatedDateDisplay) {
		this.updatedDateDisplay = updatedDateDisplay;
	}
}