package com.dalmia.dce.vo;

import java.util.Date;


public class SchemeHeaderIndexVO {
	private int schemeId;

	private String salesDocType;

	private String OrderNo;

	private String schemNumb="QD1";

	private String priceList;

	private String selfOrGroup;

	private String taxCode;

	private Date fromDate;

	private Date toDate;

	private String budgetReferenceNo;

	private String businessPlace;

	private String active;

	private String exclusiveCSTSale;

	private String singleCharecterInd;

	private String sapNonSapDate;

	private Date prevfromDate;
	
	//private String costCenter;

	private String createdBy;

	private String createdOn;
	
	private String approve_status="pending";
	
	private String schemeType;
	private String schemeCategory;
	private Date prevtoDate;
	public int getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(int schemeId) {
		this.schemeId = schemeId;
	}
	public String getSalesDocType() {
		return salesDocType;
	}
	public void setSalesDocType(String salesDocType) {
		this.salesDocType = salesDocType;
	}
	public String getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	public String getSchemNumb() {
		return schemNumb;
	}
	public void setSchemNumb(String schemNumb) {
		this.schemNumb = schemNumb;
	}
	public String getPriceList() {
		return priceList;
	}
	public void setPriceList(String priceList) {
		this.priceList = priceList;
	}
	public String getSelfOrGroup() {
		return selfOrGroup;
	}
	public void setSelfOrGroup(String selfOrGroup) {
		this.selfOrGroup = selfOrGroup;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getBudgetReferenceNo() {
		return budgetReferenceNo;
	}
	public void setBudgetReferenceNo(String budgetReferenceNo) {
		this.budgetReferenceNo = budgetReferenceNo;
	}
	public String getBusinessPlace() {
		return businessPlace;
	}
	public void setBusinessPlace(String businessPlace) {
		this.businessPlace = businessPlace;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getExclusiveCSTSale() {
		return exclusiveCSTSale;
	}
	public void setExclusiveCSTSale(String exclusiveCSTSale) {
		this.exclusiveCSTSale = exclusiveCSTSale;
	}
	public String getSingleCharecterInd() {
		return singleCharecterInd;
	}
	public void setSingleCharecterInd(String singleCharecterInd) {
		this.singleCharecterInd = singleCharecterInd;
	}
	public String getSapNonSapDate() {
		return sapNonSapDate;
	}
	public void setSapNonSapDate(String sapNonSapDate) {
		this.sapNonSapDate = sapNonSapDate;
	}
	public Date getPrevfromDate() {
		return prevfromDate;
	}
	public void setPrevfromDate(Date prevfromDate) {
		this.prevfromDate = prevfromDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getApprove_status() {
		return approve_status;
	}
	public void setApprove_status(String approve_status) {
		this.approve_status = approve_status;
	}
	public String getSchemeType() {
		return schemeType;
	}
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}
	public String getSchemeCategory() {
		return schemeCategory;
	}
	public void setSchemeCategory(String schemeCategory) {
		this.schemeCategory = schemeCategory;
	}
	public Date getPrevtoDate() {
		return prevtoDate;
	}
	public void setPrevtoDate(Date prevtoDate) {
		this.prevtoDate = prevtoDate;
	}
	
}
