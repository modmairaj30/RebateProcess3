package com.dalmia.dce.fileupload.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dalmia.dce.entities.SchemeHeaderCond;
@Service
public class FileUploadKeyStore {
	Map<String,List<SchemeHeaderCond>> map = new HashMap<String,List<SchemeHeaderCond>>();

	public Map<String, List<SchemeHeaderCond>> getMap() {
		return map;
	}

	public void setMap(Map<String, List<SchemeHeaderCond>> map) {
		this.map = map;
	}

	
}
