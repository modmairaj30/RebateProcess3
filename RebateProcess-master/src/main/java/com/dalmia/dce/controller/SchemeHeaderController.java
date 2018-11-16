package com.dalmia.dce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dalmia.dce.service.SchemeHeaderService;
import com.dalmia.dce.vo.ApprovedVO;
import com.dalmia.dce.vo.CompanyCodesVO;
import com.dalmia.dce.vo.SchemeHeaderGetVO;
import com.dalmia.dce.vo.SchemeHeaderIndexVO;
import com.dalmia.dce.vo.SchemeHeaderVO;
import com.dalmia.dce.vo.StatusVO;

@RestController
@RequestMapping("/rest")
@CrossOrigin("*")
public class SchemeHeaderController {
	@Autowired
	SchemeHeaderService schemeHeaderService;

	@PostMapping(value = "/saveschemeHeader")
	public ResponseEntity<?> UserRegister(@RequestBody SchemeHeaderVO schemeHeaderVO) {
		StatusVO res = schemeHeaderService.saveSchemeHeader(schemeHeaderVO);
		
		return ResponseEntity.ok(res);
	}
	
	@GetMapping(value = "/getSchemeHeader")
	public ResponseEntity<?> getSchemeHeader() {
		List<SchemeHeaderGetVO> res = schemeHeaderService.getSchemeHeader();
		
		return ResponseEntity.ok(res);
	}
	
	@GetMapping(value = "/getSchemeIndex")
	public ResponseEntity<?> getSchemeIndex() {
		List<SchemeHeaderIndexVO> res = schemeHeaderService.getSchemeIndex();
		
		return ResponseEntity.ok(res);
	}
	
	@PostMapping(value = "/updateApproved")
	public ResponseEntity<?> updateApproved(@RequestBody ApprovedVO approvedVO) {
		StatusVO res = schemeHeaderService.updateApproved(approvedVO);
		
		return ResponseEntity.ok(res);
	}
	
	@GetMapping(value = "/getSchemeHeader")
	public ResponseEntity<?> getViewScheme() {
		List<SchemeHeaderGetVO> res = schemeHeaderService.getSchemeHeader();
		
		return ResponseEntity.ok(res);
	}

}
