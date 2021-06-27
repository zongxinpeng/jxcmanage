package com.jxc.jxcmanage.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.util.ObjectUtils;

import com.jxc.jxcmanage.constants.Constant;

import cn.hutool.core.date.DateUtil;

public class StorageDto extends BaseDto{
    private Long id;

    private Long supplierId;

    private BigDecimal amount;

    private String supplierMobile;

    private String expressMobile;

    private String receiver;

    private String deliverer;

    private String deliverDate;

    private String receiveDate;

    private String batch;

    private String note;

    private Integer status;

    private String createdBy;

    private String updatedBy;

    private Date createdDate;

    private Date updatedDate;
    private Date startDate;
	private String createdDateDisplay;

	private String updatedDateDisplay;
	private Date endDate;
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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSupplierMobile() {
        return supplierMobile;
    }

    public void setSupplierMobile(String supplierMobile) {
        this.supplierMobile = supplierMobile == null ? null : supplierMobile.trim();
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

    public String getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(String deliverDate) {
        this.deliverDate = deliverDate == null ? null : deliverDate.trim();
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