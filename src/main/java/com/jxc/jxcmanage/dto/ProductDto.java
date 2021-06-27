package com.jxc.jxcmanage.dto;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.util.ObjectUtils;
import com.jxc.jxcmanage.constants.Constant;
import cn.hutool.core.date.DateUtil;

public class ProductDto extends BaseDto {

	private Long id;

	private long supplierId;

	private String code;

	private String name;

	private String specs;

	private String unit;

	private String note;

	private BigDecimal price;

	private Integer status;

	private String createdBy;

	private String updatedBy;

	private Date createdDate;

	private Date updatedDate;

	private String createdDateDisplay;

	private String updatedDateDisplay;

	private Date startDate;

	private Date endDate;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs == null ? null : specs.trim();
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit == null ? null : unit.trim();
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

}
