package com.jxc.jxcmanage.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.util.ObjectUtils;

import com.jxc.jxcmanage.constants.Constant;

import cn.hutool.core.date.DateUtil;

public class SaleDetailDto extends BaseDto{

    private Long id;

    private Long supplierId;

    private Long productId;

    private String productCode;

    private BigDecimal price;

    private Integer count;

    private BigDecimal amount;

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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
