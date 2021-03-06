package com.dalmia.dce.vo;

import java.util.List;

public class CustomerVO {
	private String customer;
	private String name;
	private List range;
	private List values;
	private List excludeValues;
	private String schKeyName;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List getRange() {
		return range;
	}

	public void setRange(List range) {
		this.range = range;
	}

	public List getValues() {
		return values;
	}

	public void setValues(List values) {
		this.values = values;
	}

	public List getExcludeValues() {
		return excludeValues;
	}

	public void setExcludeValues(List excludeValues) {
		this.excludeValues = excludeValues;
	}

	public String getSchKeyName() {
		return schKeyName;
	}

	public void setSchKeyName(String schKeyName) {
		this.schKeyName = schKeyName;
	}

}
