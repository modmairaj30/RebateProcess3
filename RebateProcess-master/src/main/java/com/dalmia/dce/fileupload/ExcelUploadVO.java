package com.dalmia.dce.fileupload;

import org.springframework.web.multipart.MultipartFile;

public class ExcelUploadVO {

	
	private String fType;

	private String fValue;

	private double vFrom;

	private double vTo;

	private double rate;
	
	private String fileName;
	
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ExcelUploadVO(String fileName, String fileDownloadUri, String contentType, long size) {
		// TODO Auto-generated constructor stub
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
