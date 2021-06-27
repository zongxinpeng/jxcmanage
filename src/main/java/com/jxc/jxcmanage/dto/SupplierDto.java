package com.jxc.jxcmanage.dto;

import java.util.Date;

import org.springframework.util.ObjectUtils;

import com.jxc.jxcmanage.constants.Constant;

import cn.hutool.core.date.DateUtil;

public class SupplierDto extends BaseDto {

	private Long id;

	private String code;

	private String name;

	private String linkMobile;

	private String linkAddress;

	private String fax;

	private String cityCode;

	private String note;

	private Integer status;

	private String createdBy;

	private String updatedBy;

	private Date createdDate;

	private Date updatedDate;
	private String createdDateDisplay;

	private String updatedDateDisplay;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getLinkMobile() {
		return linkMobile;
	}

	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile == null ? null : linkMobile.trim();
	}

	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress == null ? null : linkAddress.trim();
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax == null ? null : fax.trim();
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode == null ? null : cityCode.trim();
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
