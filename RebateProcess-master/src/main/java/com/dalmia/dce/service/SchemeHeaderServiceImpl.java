package com.dalmia.dce.service;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dalmia.dce.dao.ShemCreationDetailDao;
import com.dalmia.dce.entities.SchemeHeader;
import com.dalmia.dce.entities.SchemeHeaderCond;
import com.dalmia.dce.entities.SchemeHeaderDetail;
import com.dalmia.dce.repositories.SchemeHeaderRepository;
import com.dalmia.dce.utilities.RangeCalculationUtil;
import com.dalmia.dce.utilities.RangeObject;
import com.dalmia.dce.vo.ApprovedVO;
import com.dalmia.dce.vo.SchemeHeaderCondVO;
import com.dalmia.dce.vo.SchemeHeaderGetVO;
import com.dalmia.dce.vo.SchemeHeaderIndexVO;
import com.dalmia.dce.vo.SchemeHeaderVO;
import com.dalmia.dce.vo.StatusVO;

@Service
public class SchemeHeaderServiceImpl implements SchemeHeaderService {

	@Autowired
	SchemeHeaderRepository schemeHeaderRepository;
	@Autowired
	RangeCalculationUtil rangeCalculationUtil;

	@Autowired
	ShemCreationDetailDao schemcreationDetaiDao;
	
	@Autowired
	ServletContext servletContext;


	@Transactional
	@Override
	public List<SchemeHeaderGetVO> getSchemeHeader() {
		List<SchemeHeader> lsh = schemeHeaderRepository.findAll();
		List<SchemeHeaderGetVO> lshvo = new ArrayList<SchemeHeaderGetVO>();
		for (SchemeHeader objsrc : lsh) {
			SchemeHeaderGetVO objtar = new SchemeHeaderGetVO();
			BeanUtils.copyProperties(objsrc, objtar);
			lshvo.add(objtar);
		}
		return lshvo;
	}

	@Transactional
	@Override
	public List<SchemeHeaderIndexVO> getSchemeIndex() {
		List<SchemeHeader> lsh = schemeHeaderRepository.findAll();
		List<SchemeHeaderIndexVO> lshvo = new ArrayList<SchemeHeaderIndexVO>();
		for (SchemeHeader objsrc : lsh) {
			SchemeHeaderIndexVO objtar = new SchemeHeaderIndexVO();
			BeanUtils.copyProperties(objsrc, objtar);
			objtar.setApprove_status(objsrc.getApproveStatus());
			lshvo.add(objtar);
		}
		return lshvo;
	}

	@Transactional
	@Override
	public StatusVO saveSchemeHeader(SchemeHeaderVO shVO) {
		SchemeHeader sh = new SchemeHeader();
		SchemeHeaderDetail shd = new SchemeHeaderDetail();
		SchemeHeaderCond shc = new SchemeHeaderCond();

		List<SchemeHeaderDetail> lshd = new ArrayList<SchemeHeaderDetail>();
		List<SchemeHeaderCond> lshc = new ArrayList<SchemeHeaderCond>();

		sh.setSalesDocType(shVO.getSalesDocType());

		sh.setActive(shVO.getActive());

		sh.setFromDate(shVO.getFromDate());
		sh.setToDate(shVO.getToDate());
		sh.setApproveStatus(shVO.getActive());
		sh.setBudgetReferenceNo(shVO.getBudgetReferenceNo());
		sh.setBusinessPlace(shVO.getBusinessPlace());
		sh.setExclusiveCSTSale(shVO.getExclusiveCSTSale());
		sh.setFromDate(shVO.getFromDate());
		sh.setOrderNo(shVO.getOrderNo());
		sh.setPrevfromDate(shVO.getPrevfromDate());
		sh.setPrevtoDate(shVO.getPrevtoDate());
		sh.setPriceList(shVO.getPriceList());
		sh.setApproveStatus("pending");
		sh.setCreatedBy("user");
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		sh.setCreatedOn(strDate);
		sh.setSchemeType(this.getSchemeType(shVO.getSchemeType()));

		sh.setSchemeCategory(shVO.getSchemeCategory());

		String seq = this.generateSchemeNumber(shVO);
		sh.setSchemNumb(seq);
		// sh.setSchemCategory(schemCategory);

		RangeObject rangeObj = new RangeObject();

		if (shVO.getCompanyCode() != null) {
			rangeObj.setProperty(shVO.getCompanyCode().getSchKeyName());
			rangeObj.setType(shVO.getCompanyCode().getType());
			rangeObj.setRange(shVO.getCompanyCode().getRange());
			rangeObj.setValues(shVO.getCompanyCode().getValues());
			rangeObj.setExcludeValues(shVO.getCompanyCode().getExcludeValues());
			Map<String, String> compCodeMap = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : compCodeMap.keySet()) {
				String sch_key = compCodeMap.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}
		if (shVO.getSalesOrg() != null) {
			rangeObj.setProperty(shVO.getSalesOrg().getSchKeyName());
			rangeObj.setType(shVO.getSalesOrg().getType());
			rangeObj.setRange(shVO.getSalesOrg().getRange());
			rangeObj.setValues(shVO.getSalesOrg().getValues());
			rangeObj.setExcludeValues(shVO.getSalesOrg().getExcludeValues());
			Map<String, String> salesOrgMap = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : salesOrgMap.keySet()) {
				String sch_key = salesOrgMap.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}

		if (shVO.getDistributionChannel() != null) {
			rangeObj.setProperty(shVO.getDistributionChannel().getSchKeyName());
			rangeObj.setType(shVO.getDistributionChannel().getType());
			rangeObj.setRange(shVO.getDistributionChannel().getRange());
			rangeObj.setValues(shVO.getDistributionChannel().getValues());
			rangeObj.setExcludeValues(shVO.getDistributionChannel().getExcludeValues());
			Map<String, String> dbChMap = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : dbChMap.keySet()) {
				String sch_key = dbChMap.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}
		if (shVO.getDivision() != null) {
			rangeObj.setProperty(shVO.getDivision().getSchKeyName());
			rangeObj.setType(shVO.getDivision().getType());
			rangeObj.setRange(shVO.getDivision().getRange());
			rangeObj.setValues(shVO.getDivision().getValues());
			rangeObj.setExcludeValues(shVO.getDivision().getExcludeValues());
			Map<String, String> getDivisionMap = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : getDivisionMap.keySet()) {
				String sch_key = getDivisionMap.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}
		if (shVO.getSalseOffice() != null) {
			rangeObj.setProperty(shVO.getSalseOffice().getSchKeyName());
			rangeObj.setType(shVO.getSalseOffice().getType());
			rangeObj.setRange(shVO.getSalseOffice().getRange());
			rangeObj.setValues(shVO.getSalseOffice().getValues());
			rangeObj.setExcludeValues(shVO.getSalseOffice().getExcludeValues());
			Map<String, String> getSalseOfficeMap = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : getSalseOfficeMap.keySet()) {
				String sch_key = getSalseOfficeMap.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}
		if (shVO.getSalesGroup() != null) {
			rangeObj.setProperty(shVO.getSalesGroup().getSchKeyName());
			rangeObj.setType(shVO.getSalesGroup().getType());
			rangeObj.setRange(shVO.getSalesGroup().getRange());
			rangeObj.setValues(shVO.getSalesGroup().getValues());
			rangeObj.setExcludeValues(shVO.getSalesGroup().getExcludeValues());
			Map<String, String> getSalesGroupMap = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : getSalesGroupMap.keySet()) {
				String sch_key = getSalesGroupMap.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}
		if (shVO.getSalseDistrict() != null) {
			rangeObj.setProperty(shVO.getSalseDistrict().getSchKeyName());
			rangeObj.setType(shVO.getSalseDistrict().getType());
			rangeObj.setRange(shVO.getSalseDistrict().getRange());
			rangeObj.setValues(shVO.getSalseDistrict().getValues());
			rangeObj.setExcludeValues(shVO.getSalseDistrict().getExcludeValues());
			Map<String, String> getSalseDistrict = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : getSalseDistrict.keySet()) {
				String sch_key = getSalseDistrict.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}
		if (shVO.getRegion() != null) {
			rangeObj.setProperty(shVO.getRegion().getSchKeyName());
			rangeObj.setType(shVO.getRegion().getType());
			rangeObj.setRange(shVO.getRegion().getRange());
			rangeObj.setValues(shVO.getRegion().getValues());
			rangeObj.setExcludeValues(shVO.getRegion().getExcludeValues());
			Map<String, String> getRegion = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : getRegion.keySet()) {
				String sch_key = getRegion.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}
		if (shVO.getCountryCode() != null) {
			rangeObj.setProperty(shVO.getCountryCode().getSchKeyName());
			rangeObj.setType(shVO.getCountryCode().getType());
			rangeObj.setRange(shVO.getCountryCode().getRange());
			rangeObj.setValues(shVO.getCountryCode().getValues());
			rangeObj.setExcludeValues(shVO.getCountryCode().getExcludeValues());
			Map<String, String> getCountryCode = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : getCountryCode.keySet()) {
				String sch_key = getCountryCode.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}
		if (shVO.getCityCode() != null) {
			rangeObj.setProperty(shVO.getCityCode().getSchKeyName());
			rangeObj.setType(shVO.getCityCode().getType());
			rangeObj.setRange(shVO.getCityCode().getRange());
			rangeObj.setValues(shVO.getCityCode().getValues());
			rangeObj.setExcludeValues(shVO.getCityCode().getExcludeValues());
			Map<String, String> getCityCode = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : getCityCode.keySet()) {
				String sch_key = getCityCode.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}
		if (shVO.getCustomer() != null) {
			rangeObj.setProperty(shVO.getCustomer().getSchKeyName());
			rangeObj.setType(shVO.getCustomer().getType());
			rangeObj.setRange(shVO.getCustomer().getRange());
			rangeObj.setValues(shVO.getCustomer().getValues());
			rangeObj.setExcludeValues(shVO.getCustomer().getExcludeValues());
			Map<String, String> getCustomer = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : getCustomer.keySet()) {
				String sch_key = getCustomer.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}
		if (shVO.getCustomerGroup() != null) {
			rangeObj.setProperty(shVO.getCustomerGroup().getSchKeyName());
			rangeObj.setType(shVO.getCustomerGroup().getType());
			rangeObj.setRange(shVO.getCustomerGroup().getRange());
			rangeObj.setValues(shVO.getCustomerGroup().getValues());
			rangeObj.setExcludeValues(shVO.getCustomerGroup().getExcludeValues());
			Map<String, String> getCustomerGroup = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : getCustomerGroup.keySet()) {
				String sch_key = getCustomerGroup.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}
		if (shVO.getMaterial() != null) {
			rangeObj.setProperty(shVO.getMaterial().getSchKeyName());
			rangeObj.setType(shVO.getMaterial().getType());
			rangeObj.setRange(shVO.getMaterial().getRange());
			rangeObj.setValues(shVO.getMaterial().getValues());
			rangeObj.setExcludeValues(shVO.getMaterial().getExcludeValues());
			Map<String, String> getMaterial = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : getMaterial.keySet()) {
				String sch_key = getMaterial.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}
		if (shVO.getMaterialGroup() != null) {
			rangeObj.setProperty(shVO.getMaterialGroup().getSchKeyName());
			rangeObj.setType(shVO.getMaterialGroup().getType());
			rangeObj.setRange(shVO.getMaterialGroup().getRange());
			rangeObj.setValues(shVO.getMaterialGroup().getValues());
			rangeObj.setExcludeValues(shVO.getMaterialGroup().getExcludeValues());
			Map<String, String> getMaterialGroup = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : getMaterialGroup.keySet()) {
				String sch_key = getMaterialGroup.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}
		if (shVO.getPlant() != null) {
			rangeObj.setProperty(shVO.getPlant().getSchKeyName());
			rangeObj.setType(shVO.getPlant().getType());
			rangeObj.setRange(shVO.getPlant().getRange());
			rangeObj.setValues(shVO.getPlant().getValues());
			rangeObj.setExcludeValues(shVO.getPlant().getExcludeValues());
			Map<String, String> getPlant = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : getPlant.keySet()) {
				String sch_key = getPlant.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}
		if (shVO.getPaymentMethod() != null) {
			rangeObj.setProperty(shVO.getPaymentMethod().getSchKeyName());
			rangeObj.setType(shVO.getPaymentMethod().getType());
			rangeObj.setRange(shVO.getShippingCond().getRange());
			rangeObj.setValues(shVO.getShippingCond().getValues());
			rangeObj.setExcludeValues(shVO.getShippingCond().getExcludeValues());
			Map<String, String> getShippingCond = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : getShippingCond.keySet()) {
				String sch_key = getShippingCond.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}
		if (shVO.getPaymentMethod() != null) {
			rangeObj.setProperty(shVO.getPaymentMethod().getSchKeyName());
			rangeObj.setType(shVO.getPaymentMethod().getType());
			rangeObj.setRange(shVO.getPaymentMethod().getRange());
			rangeObj.setValues(shVO.getPaymentMethod().getValues());
			rangeObj.setExcludeValues(shVO.getPaymentMethod().getExcludeValues());
			Map<String, String> getPaymentMethod = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : getPaymentMethod.keySet()) {
				String sch_key = getPaymentMethod.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}
		if (shVO.getBillingType() != null) {
			rangeObj.setProperty(shVO.getBillingType().getSchKeyName());
			rangeObj.setType("str");
			rangeObj.setRange(shVO.getBillingType().getRange());
			rangeObj.setValues(shVO.getBillingType().getValues());
			rangeObj.setExcludeValues(shVO.getBillingType().getExcludeValues());
			Map<String, String> getBillingType = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : getBillingType.keySet()) {
				String sch_key = getBillingType.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}
		if (shVO.getProfitCenter() != null) {
			rangeObj.setProperty(shVO.getProfitCenter().getSchKeyName());
			rangeObj.setType(shVO.getProfitCenter().getType());
			rangeObj.setRange(shVO.getProfitCenter().getRange());
			rangeObj.setValues(shVO.getProfitCenter().getValues());
			rangeObj.setExcludeValues(shVO.getProfitCenter().getExcludeValues());
			Map<String, String> getProfitCenter = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : getProfitCenter.keySet()) {
				String sch_key = getProfitCenter.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}
		if (shVO.getCostCenter() != null) {
			rangeObj.setProperty(shVO.getCostCenter().getSchKeyName());
			rangeObj.setType(shVO.getCostCenter().getType());
			rangeObj.setRange(shVO.getCostCenter().getRange());
			rangeObj.setValues(shVO.getCostCenter().getValues());
			rangeObj.setExcludeValues(shVO.getCostCenter().getExcludeValues());
			Map<String, String> getCostCenter = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : getCostCenter.keySet()) {
				String sch_key = getCostCenter.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}
		if (shVO.getBuisnessArea() != null) {
			rangeObj.setProperty(shVO.getBuisnessArea().getSchKeyName());
			rangeObj.setType(shVO.getBuisnessArea().getType());
			rangeObj.setRange(shVO.getBuisnessArea().getRange());
			rangeObj.setValues(shVO.getBuisnessArea().getValues());
			rangeObj.setExcludeValues(shVO.getBuisnessArea().getExcludeValues());
			Map<String, String> getBuisnessArea = rangeCalculationUtil.rangeCalculation(rangeObj);

			for (String sch_values : getBuisnessArea.keySet()) {
				String sch_key = getBuisnessArea.get(sch_values);
				SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
				obj_detail.setSchKey(sch_key);
				obj_detail.setSchemNumb(seq);
				obj_detail.setSchValue(sch_values + "");
				lshd.add(obj_detail);

			}
		}

		rangeObj.setProperty(shVO.getGlAccount().getSchKeyName());
		rangeObj.setRange(shVO.getGlAccount().getRange());
		rangeObj.setValues(shVO.getGlAccount().getValues());
		rangeObj.setExcludeValues(shVO.getGlAccount().getExcludeValues());
		Map<String, String> getGlAccount = rangeCalculationUtil.rangeCalculation(rangeObj);

		for (String sch_values : getGlAccount.keySet()) {
			String sch_key = getGlAccount.get(sch_values);
			SchemeHeaderDetail obj_detail = new SchemeHeaderDetail();
			obj_detail.setSchKey(sch_key);
			obj_detail.setSchemNumb(seq);
			obj_detail.setSchValue(sch_values + "");
			lshd.add(obj_detail);

		}

		 if (shVO.getFileKey().isEmpty()) {
				for (SchemeHeaderCondVO obj_src : shVO.getRows()) {
					SchemeHeaderCond obj_tar = new SchemeHeaderCond();
					if (obj_src.getSlab_value().length() > 0) {
						if (obj_src.getSlab_from().length() > 0) {
							obj_tar.setFromValue(Double.parseDouble(obj_src.getSlab_from()));
						}
						if (obj_src.getSlab_to().length() > 0) {
							obj_tar.setToValue(Double.parseDouble(obj_src.getSlab_to()));
						}
						obj_tar.setRate(Double.parseDouble(obj_src.getSlab_value()));
						if (obj_src.getRow_id().length() > 0) {
							obj_tar.setRowId(Integer.parseInt(obj_src.getRow_id()));
						}
					} else {
						if (obj_src.getSlab_from().length() > 0) {
							obj_tar.setFromValue(Double.parseDouble(obj_src.getSlab_from()));
						}
						if (obj_src.getSlab_to().length() > 0) {
							obj_tar.setToValue(Double.parseDouble(obj_src.getSlab_to()));
						}

						// obj_tar.setRate(Double.parseDouble(obj_src.getSlab_value()));
						if (obj_src.getRow_id().length() > 0) {
							obj_tar.setRowId(Integer.parseInt(obj_src.getRow_id()));
						}
						if (obj_src.getC1().getC1_from().length() > 0) {
							obj_tar.setC1From(Double.parseDouble(obj_src.getC1().getC1_from()));
						}
						if (obj_src.getC1().getC1_to().length() > 0) {
							obj_tar.setC1To(Double.parseDouble(obj_src.getC1().getC1_to()));
						}
						if (obj_src.getC1().getC1_value().length() > 0) {
							obj_tar.setC1Value(Double.parseDouble(obj_src.getC1().getC1_value()));
						}

						if (obj_src.getC2().getC2_from().length() > 0) {
							obj_tar.setC2From(Double.parseDouble(obj_src.getC2().getC2_from()));
						}
						if (obj_src.getC2().getC2_to().length() > 0) {
							obj_tar.setC2To(Double.parseDouble(obj_src.getC2().getC2_to()));
						}
						if (obj_src.getC2().getC2_value().length() > 0) {
							obj_tar.setC2Value(Double.parseDouble(obj_src.getC2().getC2_value()));
						}

						if (obj_src.getC3().getC3_from().length() > 0) {
							obj_tar.setC3From(Double.parseDouble(obj_src.getC3().getC3_from()));
						}
						if (obj_src.getC3().getC3_to().length() > 0) {
							obj_tar.setC3To(Double.parseDouble(obj_src.getC3().getC3_to()));
						}
						if (obj_src.getC3().getC3_value().length() > 0) {
							obj_tar.setC3Value(Double.parseDouble(obj_src.getC3().getC3_value()));
						}

						if (obj_src.getC4().getC4_from().length() > 0) {
							obj_tar.setC4From(Double.parseDouble(obj_src.getC4().getC4_from()));
						}
						if (obj_src.getC4().getC4_to().length() > 0) {
							obj_tar.setC4To(Double.parseDouble(obj_src.getC4().getC4_to()));
						}
						if (obj_src.getC4().getC4_value().length() > 0) {
							obj_tar.setC4Value(Double.parseDouble(obj_src.getC4().getC4_value()));
						}

						if (obj_src.getC5().getC5_from().length() > 0) {
							obj_tar.setC5From(Double.parseDouble(obj_src.getC5().getC5_from()));
						}
						if (obj_src.getC5().getC5_to().length() > 0) {
							obj_tar.setC5To(Double.parseDouble(obj_src.getC5().getC5_to()));
						}
						if (obj_src.getC5().getC5_value().length() > 0) {
							obj_tar.setC5Value(Double.parseDouble(obj_src.getC5().getC5_value()));
						}

					}
					lshc.add(obj_tar);
				}

				
				sh.setSchemeHeaderCond(lshc);
				 }
				 else
				 { 
					List<SchemeHeaderCond> schemeHeaderCondList = (List<SchemeHeaderCond>) servletContext.getAttribute(shVO.getFileKey());
					if(!schemeHeaderCondList.isEmpty()) {
						sh.setSchemeHeaderCond(schemeHeaderCondList);
					}
				 }
				sh.setSchemeHeaderDetail(lshd);
				schemeHeaderRepository.save(sh);
				StatusVO status = new StatusVO();
				status.setMessage("Successfully");
				return status;
			}

	public String getSchemeType(String schemeType) {
		return "RBQD";
	}

	public String generateSchemeNumber(SchemeHeaderVO schVO) {
		// String schemeNo = "RBQD";
		String schemeNo = "";
		// scheme_no = RBQD+State+Year+Month+HeaderId
		/*
		 * Division/Region/State Code/Distribution channel/Scheme Category/Scheme
		 * Type/Period/Year/Running Sl No. Sample for Cash Discount scheme for South
		 * region - CEMSTTN1TRSSRBCD082018001 (The same will be included in Document
		 * Header Text in FI Posting in SAP)
		 */

		String division = schVO.getDivision().getRange().get(0) == null ? ""
				: schVO.getDivision().getRange().get(0).toString();
		String region = schVO.getRegion().getRange().get(0) == null ? ""
				: schVO.getRegion().getRange().get(0).toString();
		String stateCode = schVO.getCityCode().getRange().get(0) == null ? ""
				: schVO.getCityCode().getRange().get(0).toString();
		String customer = schVO.getCustomer().getRange().get(0) == null ? ""
				: schVO.getCustomer().getRange().get(0).toString();
		String saleOrg = schVO.getSalesOrg().getRange().get(0) == null ? ""
				: schVO.getSalesOrg().getRange().get(0).toString();
		String distributionChannel = schVO.getDistributionChannel().getRange().get(0) == null ? ""
				: schVO.getDistributionChannel().getRange().get(0).toString();
		String schemeCategory = schVO.getSchemeCategory();
		String schemeType = schVO.getSchemeType();
		// Date date = new Date();
		// DateFormat dateFormat = new SimpleDateFormat("yyyymmdd");
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern("dd");
		String day = simpleDateFormat.format(date);
		simpleDateFormat.applyPattern("mm");
		String month = simpleDateFormat.format(date);
		simpleDateFormat.applyPattern("yyyy");
		String maxId = "";
		String nomenclature = "";
		String year = "";
		String period="";
		try {
			maxId = schemcreationDetaiDao.getMaxId();
			nomenclature = schemcreationDetaiDao.getNomenclature(customer, saleOrg, division, region, stateCode,
					distributionChannel, schemeCategory, schemeType);
			year = simpleDateFormat.format(date);
			period = PeriodUtil.calcPeriod(schVO.getFromDate().toString(),schVO.getToDate().toString());

		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schemeNo = nomenclature  +period+ year + maxId;

		return schemeNo;
	}

	@Transactional
	@Override
	public StatusVO updateApproved(ApprovedVO apVO) {
		String msg = "";
		try {
			msg = schemcreationDetaiDao.updateAproved(apVO.getSchemeNO());

			StatusVO status = new StatusVO();
			status.setCode("200");
			status.setMessage(msg);
			status.setStatus("Approved");
			return status;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*@Transactional
	@Override
	public List<SchemeHeaderGetVO> getSchemeHeaderByHeaderId() {
		List<SchemeHeader> lsh = schemeHeaderRepository.findAll();
		List<SchemeHeaderGetVO> lshvo = new ArrayList<SchemeHeaderGetVO>();
		for (SchemeHeader objsrc : lsh) {
			SchemeHeaderGetVO objtar = new SchemeHeaderGetVO();
			BeanUtils.copyProperties(objsrc, objtar);
			lshvo.add(objtar);
		}
		return lshvo;
	}*/

	@Override
	public StatusVO updateSchemeHeader(SchemeHeaderVO shVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatusVO deleteSchemeHeader(SchemeHeaderVO shVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SchemeHeaderGetVO findBySchemeNo(SchemeHeaderVO shVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SchemeHeaderGetVO findByHeaderId(SchemeHeaderVO shVO) {
		// TODO Auto-generated method stub
		return null;
	}

}
