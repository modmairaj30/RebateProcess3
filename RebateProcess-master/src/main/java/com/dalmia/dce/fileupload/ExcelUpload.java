package com.dalmia.dce.fileupload;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Entity
@ConfigurationProperties(prefix = "file")
@Table(name = "sch_hdr_cond_ext")
public class ExcelUpload {
	@Id
	@Column(name = "sch_hdr_cond_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int schHdrCondId;

	@Column(name = "header_id",length=11)
	private int headerId;
	
	@Column(name = "FTYPE",length=20)
	private String fType;
	
	@Column(name = "FVALUE",length=20)
	private String fValue;
	
	@Column(name = "VFROM")
	private double vFrom;
	
	@Column(name = "VTO")
	private double vTo;
	
	@Column(name = "RATE")
	private double rate;

	public int getSchHdrCondId() {
		return schHdrCondId;
	}

	public void setSchHdrCondId(int schHdrCondId) {
		this.schHdrCondId = schHdrCondId;
	}

	public int getHeaderId() {
		return headerId;
	}

	public void setHeaderId(int headerId) {
		this.headerId = headerId;
	}

	public String getfType() {
		return fType;
	}

	public void setfType(String fType) {
		this.fType = fType;
	}

	public String getfValue() {
		return fValue;
	}

	public void setfValue(String fValue) {
		this.fValue = fValue;
	}

	public double getvFrom() {
		return vFrom;
	}

	public void setvFrom(double vFrom) {
		this.vFrom = vFrom;
	}

	public double getvTo() {
		return vTo;
	}

	public void setvTo(double vTo) {
		this.vTo = vTo;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

		

}
