package com.dalmia.dce.service;

import java.util.List;

import com.dalmia.dce.vo.ApprovedVO;
import com.dalmia.dce.vo.SchemeHeaderGetVO;
import com.dalmia.dce.vo.SchemeHeaderIndexVO;
import com.dalmia.dce.vo.SchemeHeaderVO;
import com.dalmia.dce.vo.StatusVO;

public interface SchemeHeaderService {
	List<SchemeHeaderGetVO> getSchemeHeader();
	List<SchemeHeaderIndexVO> getSchemeIndex();
	public StatusVO saveSchemeHeader(SchemeHeaderVO shVO);
	public StatusVO updateApproved(ApprovedVO apVO) ;
	public StatusVO updateSchemeHeader(SchemeHeaderVO shVO);
	public StatusVO deleteSchemeHeader(SchemeHeaderVO shVO);
	public SchemeHeaderGetVO findBySchemeNo(SchemeHeaderVO shVO);
	public SchemeHeaderGetVO findByHeaderId(SchemeHeaderVO shVO);
	

}
