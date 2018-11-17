package com.dalmia.dce.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dalmia.dce.vo.BillingTypeVO;
import com.dalmia.dce.vo.BusinessAreaVO;
import com.dalmia.dce.vo.CityCodeVO;
import com.dalmia.dce.vo.CompanyCodesVO;
import com.dalmia.dce.vo.CostCenterVO;
import com.dalmia.dce.vo.CountryCodeVO;
import com.dalmia.dce.vo.CustomerGroupVO;
import com.dalmia.dce.vo.CustomerVO;
import com.dalmia.dce.vo.DistributionChannelVO;
import com.dalmia.dce.vo.DivisionVO;
import com.dalmia.dce.vo.GLAccountVO;
import com.dalmia.dce.vo.MaterialGroupVO;
import com.dalmia.dce.vo.MaterialVO;
import com.dalmia.dce.vo.PaymentInsurenceTypeVO;
import com.dalmia.dce.vo.PlantVO;
import com.dalmia.dce.vo.ProfitCenterVO;
import com.dalmia.dce.vo.RegionVO;
import com.dalmia.dce.vo.SalesDistrictVO;
import com.dalmia.dce.vo.SalesGroupVO;
import com.dalmia.dce.vo.SalesOfficeVO;
import com.dalmia.dce.vo.SalesOrganizationVO;
import com.dalmia.dce.vo.SchemeTypeVO;
import com.dalmia.dce.vo.SchemeUniverseVO;
import com.dalmia.dce.vo.ShippingConditionsVO;
import com.dalmia.dce.vo.ViewSchemCreationVO;

@Service
public class ShemCreationDetailDao {
	

	
	@Autowired
	DataSource dataSource;
	
	private Connection jdbcConnection=null;
	
	public Map<String, SalesOrganizationVO> getSalesOrg() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
		 jdbcConnection = dataSource.getConnection();
		Map<String,SalesOrganizationVO> salesOrgMap = new LinkedHashMap<String,SalesOrganizationVO>();
		String sql = "select VKORG as SOrg from TVKO";
		
		 statement = jdbcConnection.createStatement();
		 resultSet = statement.executeQuery(sql);
		String idAndName = "";
		while (resultSet.next()) {
			
			String sorg = resultSet.getString("SOrg");
			//String name =  resultSet.getString("Name");
			SalesOrganizationVO salesOrg = new SalesOrganizationVO();
			salesOrg.setSorg(sorg);
			//salesOrg.setName(name);
			salesOrgMap.put(sorg, salesOrg);
			
		}
		
		/*resultSet.close();
		statement.close();
		jdbcConnection.close();*/
		return salesOrgMap;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
	}
	
	public Map<String,SalesDistrictVO> getSalesDistrict() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String,SalesDistrictVO> salesDistrictMap = new LinkedHashMap<String,SalesDistrictVO>();
			//String sql = "select SDst,District_Name from sales_district";
			String sql = "select BZIRK as SDst,BZTXT as District_Name from T171T";
			//connect();
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			String idAndName = "";
			
			while (resultSet.next()) {
				
				SalesDistrictVO  sdv= new SalesDistrictVO();
				String sdst = resultSet.getString("SDst");
				sdv.setSdst(resultSet.getString("SDst"));
				sdv.setDistrictName(resultSet.getString("SDst"));
				salesDistrictMap.put(sdst, sdv);
				
			}
			
			
			return salesDistrictMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
	}
	
	public Map<String,CompanyCodesVO> getComp() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String,CompanyCodesVO> compMap = new LinkedHashMap<String,CompanyCodesVO>();
			String sql = "select BUKRS as CoCd,BUTXT as Company_Name from T001";
			//connect();
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				CompanyCodesVO compCode = new CompanyCodesVO();
				String comCode = resultSet.getString("CoCd");
				 compCode.setComCode(resultSet.getString("CoCd"));
				compCode.setName(resultSet.getString("Company_Name"));
				
				compMap.put(comCode, compCode);
				
			}
			
			
			return compMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
	}
	
	public Map<String,SchemeUniverseVO> getSchemUnverse() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String, SchemeUniverseVO> schemUnivMap = new LinkedHashMap<String,SchemeUniverseVO>();
			String sql = "select Business_Name,Field_Type from scheme_universe order by Business_Name";
			//connect();
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				
				String businessName = resultSet.getString("Business_Name");
				//String fieldType =  resultSet.getString("Field_Type"); 
				String value = businessName.replaceAll("\\s","");;
				SchemeUniverseVO schemeUnv = new SchemeUniverseVO();
				schemeUnv.setBusinessName(businessName);
				schemeUnv.setValue(value);
				schemUnivMap.put(businessName, schemeUnv);
				
			}
			
			
			return schemUnivMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
	}

	//
	
	public Map<String,DistributionChannelVO> getDistbChanel() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String,DistributionChannelVO> distChanelMap = new LinkedHashMap<String,DistributionChannelVO>();
			String sql = "select VTWEG as DChl,VTEXT as Name from TVTWT";
			//connect();
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			String idAndName ="";
			while (resultSet.next()) {
				
				String dch = resultSet.getString("DChl");
				String name =  resultSet.getString("Name");
				DistributionChannelVO distChannel = new DistributionChannelVO();
				distChannel.setDch(dch);
				distChannel.setName(name);
				distChanelMap.put(dch, distChannel);
				
			}
			
			return distChanelMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
	}

	
	public Map<String,DivisionVO> getDivision() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		
		try {
			Map<String,DivisionVO> divisionMap = new LinkedHashMap<String,DivisionVO>();
			jdbcConnection = dataSource.getConnection();
			String sql = "select SPART as Dv,VTEXT as Name from TSPAT where SPRAS='EN'";
			//connect();
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			String idNameMap = "";
			while (resultSet.next()) {
				DivisionVO divisionVO = new DivisionVO();
				String dv = resultSet.getString("Dv");
				divisionVO.setDv(resultSet.getString("Dv"));
				divisionVO.setName(resultSet.getString("Name")); 
				divisionMap.put(dv, divisionVO);
				
			}


			return divisionMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
}
//select BillT,Description from billing_type
	public Map<String,BillingTypeVO> getBilling() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String,BillingTypeVO> billingMap = new LinkedHashMap<String,BillingTypeVO>();
			//String sql = "select BT_id,Description from billing_type";
			String sql = "select BillT,Description from billing_type";
			
			//connect();
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			String idAndName = "";
			while (resultSet.next()) {
				BillingTypeVO bType = new BillingTypeVO();
				String btId = resultSet.getString("BillT");
				bType.setBtId(resultSet.getString("BillT"));
				bType.setDescription(resultSet.getString("Description"));
				billingMap.put(btId, bType);
				
			}


			return billingMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
}
	
	//
	public Map<String,SalesOfficeVO> getSalesOffice() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String,SalesOfficeVO> salesOfficeMap = new LinkedHashMap<String,SalesOfficeVO>();
			//sales office
			String sql = "select VKBUR as SOff,BEZEI as Description from TVKBT";
			//connect();
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			String idName = "";
			while (resultSet.next()) {
				SalesOfficeVO sof = new SalesOfficeVO();
				String soff = resultSet.getString("SOff");
				sof.setSoff(resultSet.getString("SOff"));
				sof.setDescription(resultSet.getString("Description")); 
				salesOfficeMap.put(soff, sof);
				
			}


			return salesOfficeMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
}
//
	public Map<String,SalesGroupVO> getSalesGroup() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String,SalesGroupVO> salesGroupMap = new LinkedHashMap<String,SalesGroupVO>();
			//String sql = "select SGrp,Description from sales_group";
			String sql = "select VKGRP as SGrp,BEZEI as Description from TVGRT";
			//connect();
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				SalesGroupVO sGroup = new SalesGroupVO();
				String sgrp = resultSet.getString("SGrp");
				sGroup.setSgrp(resultSet.getString("SGrp"));
				sGroup.setDescription(resultSet.getString("Description")); 
				salesGroupMap.put(sgrp,sGroup);
				
			}


return salesGroupMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
}
	
	public Map<String,RegionVO> getRegion() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String,RegionVO> regionMap = new LinkedHashMap<String,RegionVO>();
			//String sql = "select Rg,Description from region";
			String sql = "select BLAND as Rg,BEZEI as Description from T005U WHERE LAND1='IN'";
			
			//connect();
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				RegionVO regionVO = new RegionVO();
				String rg = resultSet.getString("Rg");
				regionVO.setRg(resultSet.getString("Rg"));
				regionVO.setDescription(resultSet.getString("Description")); 
				regionMap.put(rg, regionVO);
				
			}


			return regionMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
}
//
	public Map<String,CityCodeVO> getCity() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		 
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String,CityCodeVO> cityMap = new LinkedHashMap<String,CityCodeVO>();
			String sql = "SELECT cityc as city_id,BEZEI as Description FROM T005h where LAND1='IN'";
			
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				CityCodeVO cityCodeVO = new CityCodeVO();
				String city = resultSet.getString("city_id");
				cityCodeVO.setCity(resultSet.getString("city_id"));
				cityCodeVO.setDescription(resultSet.getString("Description")); 
				cityMap.put(city, cityCodeVO);
				
			}


			return cityMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
}
	//
	public Map<String,CustomerGroupVO> getCustmGrp() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String,CustomerGroupVO> customerGrpMap = new LinkedHashMap<String,CustomerGroupVO>();
			String sql = "select CGrp,Name from customer_group";
			//connect();
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				CustomerGroupVO custGrp = new CustomerGroupVO();
				String cgrp = resultSet.getString("CGrp");
				custGrp.setCgrp(resultSet.getString("CGrp"));
				custGrp.setName(resultSet.getString("Name")); 
				customerGrpMap.put(cgrp, custGrp);
				
			}


			return customerGrpMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
}
//
	public Map<String,CustomerVO> getCustomer() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		jdbcConnection = dataSource.getConnection();
		Map<String,CustomerVO> customerMap = new LinkedHashMap<String,CustomerVO>();
		//String sql = "";
		String sql = "SELECT kunnr as Customer,Name1 as Name1  FROM kna1";
		//connect();
		statement = jdbcConnection.createStatement();
		resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			
			CustomerVO cust = new CustomerVO();
			String customer = resultSet.getString("Customer");
			cust.setCustomer(resultSet.getString("Customer"));
			cust.setName(resultSet.getString("Name1")); 
			customerMap.put(customer, cust);
			
		}
	
	resultSet.close();
	statement.close();
	jdbcConnection.close();
	return customerMap;
}
	//
	public Map<String,ShippingConditionsVO> getShippingCondition() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String,ShippingConditionsVO> shippingConditionMap = new LinkedHashMap<String,ShippingConditionsVO>();
			String sql = "select SC,Description from shipping_conditions";
			//connect();
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				ShippingConditionsVO shipCond = new ShippingConditionsVO();
				String sc = resultSet.getString("SC");
				shipCond.setSc(resultSet.getString("SC"));
				shipCond.setDescription(resultSet.getString("Description")); 
				shippingConditionMap.put(sc,shipCond);
				
			}


return shippingConditionMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
}
	public Map<String,MaterialGroupVO> getMaterialGrp() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String,MaterialGroupVO> materialGrpMap = new LinkedHashMap<String,MaterialGroupVO>();
			String sql = "select Matl_Group,Material_Group_Desc from material_group";
			//connect();
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				MaterialGroupVO matGrp = new MaterialGroupVO();
				String materialGrp = resultSet.getString("Matl_Group");
				matGrp.setMaterialGrp(resultSet.getString("Matl_Group"));
				matGrp.setMaterialGrpDesc(resultSet.getString("Material_Group_Desc")); 
				materialGrpMap.put(materialGrp,matGrp);
				
			}


			return materialGrpMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
}
  //
	
	public Map<String,MaterialVO> getMaterial() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String,MaterialVO> materialMap = new LinkedHashMap<String,MaterialVO>();
			//String sql = "select ma.matnr as Material, mt.mat_desc as Material_Description from makt mt, mara ma where ma.matnr=mt.matnr";
			String sql = "select ma.matnr as Material, mt.maktx as Material_Description from makt mt, mara ma where ma.matnr=mt.matnr";
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				MaterialVO materialVO = new MaterialVO();
				materialVO.setMaterialGrp(resultSet.getString("Material"));
				String materialGrp = resultSet.getString("Material");
				materialVO.setMaterialGrpDesc(resultSet.getString("Material_Description")); 
				materialMap.put(materialGrp, materialVO);
				
			}


			return materialMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
}
	public Map<String,CostCenterVO> getCostCenter() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String,CostCenterVO> costCenterMap = new LinkedHashMap<String,CostCenterVO>();
			String sql = "select KOKRS as COAr,KOSTL as Cost_Ctr,KTEXT as Name from cskt where SPRAS='EN'";
			//connect();
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				CostCenterVO costCenterVO = new CostCenterVO();
				costCenterVO.setCoar(resultSet.getString("COAr"));
				String coAR = resultSet.getString("Cost_Ctr");
				costCenterVO.setCostCtr(resultSet.getString("Cost_Ctr")); 
				//costCenterVO.setToDate(resultSet.getString("Material_Description")); 
				costCenterVO.setName(resultSet.getString("Name")); 
				costCenterVO.setCoCd(resultSet.getString("Cost_Ctr")); 
				costCenterMap.put(coAR, costCenterVO);				
			}


			return costCenterMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
}

	public Map<String,BusinessAreaVO> getBusinessArea() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String,BusinessAreaVO> busAreaMap = new LinkedHashMap<String,BusinessAreaVO>();
			String sql = "select BusA,Description from business_area";
			//connect();
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				BusinessAreaVO busAreaVO = new BusinessAreaVO();
				busAreaVO.setBusinessArea(resultSet.getString("BusA"));
				String businessArea = resultSet.getString("BusA");
				busAreaVO.setDescription(resultSet.getString("Description")); 
				busAreaMap.put(businessArea, busAreaVO);
				
			}


			return busAreaMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
}
	
	public Map<String,ProfitCenterVO> getProfitCenter() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String,ProfitCenterVO> profitCenterMap = new LinkedHashMap<String,ProfitCenterVO>();
			String sql = "select prctr as Profit_Ctr, ktext as Name from cepct";
			//connect();
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				ProfitCenterVO profCentVO = new ProfitCenterVO();
				profCentVO.setName(resultSet.getString("Name"));
				String prftCntr = resultSet.getString("Profit_Ctr");
				profCentVO.setProfitCenter(resultSet.getString("Profit_Ctr")); 
				profitCenterMap.put(prftCntr, profCentVO);
				
			}


			return profitCenterMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
}

	public Map<String,PaymentInsurenceTypeVO> getPaymentInsType() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String,PaymentInsurenceTypeVO> payInsTypeMap = new LinkedHashMap<String,PaymentInsurenceTypeVO>();
			String sql = "select F0002 from payment_insurance_type";
			//connect();
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				PaymentInsurenceTypeVO payInsTypeVO = new PaymentInsurenceTypeVO();
				payInsTypeVO.setF0002(resultSet.getString("F0002"));
				String f0002 = resultSet.getString("F0002"); 
				payInsTypeMap.put(f0002, payInsTypeVO);
				
			}


			return payInsTypeMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
}
	
	public Map<String,GLAccountVO> getGlAccount() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String,GLAccountVO> glAcctMap = new LinkedHashMap<String,GLAccountVO>();
			String sql = "select s1.saknr as GL_Acct,sk.KTOPL as  Ch_Ac,sk.TXT20 as Long_Text from ska1 s1,skat sk where s1.saknr=sk.saknr";
			//connect();
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				GLAccountVO glAccVO = new GLAccountVO();
				glAccVO.setGlAccount(resultSet.getString("GL_Acct"));
				glAccVO.setChAccount(resultSet.getString("Ch_Ac"));
				glAccVO.setLongText(resultSet.getString("Long_Text"));
				String GLAcct = resultSet.getString("GL_Acct"); 
				glAcctMap.put(GLAcct, glAccVO);
				
			}


return glAcctMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
}
	public Map<String,PlantVO> getPlant() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String,PlantVO> plantMap = new LinkedHashMap<String,PlantVO>();
			String sql = "SELECT WERKS as plant_id,NAME1 FROM T001W";
			//connect();
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				PlantVO plantVO = new PlantVO();
				String plantId = resultSet.getString("plant_id");
				plantVO.setPlantId(resultSet.getString("plant_id"));
				plantVO.setName(resultSet.getString("NAME1")); 
				plantMap.put(plantId, plantVO);
				
			}


return plantMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
}

	public Map<String,CountryCodeVO> getCountryCode() throws SQLException {
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		try {
			jdbcConnection = dataSource.getConnection();
			Map<String,CountryCodeVO> countryCodeMap = new LinkedHashMap<String,CountryCodeVO>();
			//String sql = "SELECT Ctr, Rg, Ccd, Description FROM county_code";
			String sql = "SELECT distinct(land1) as Ctr FROM t005u";
			
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			int i = 1 ;
			while (resultSet.next()) {
				CountryCodeVO countryCodeVO = new CountryCodeVO();
				String plantId = resultSet.getString("Ctr");
				countryCodeVO.setCtr(resultSet.getString("Ctr"));
				countryCodeVO.setCcd(String.valueOf(i));
				
				countryCodeMap.put(plantId, countryCodeVO);
				i = i++;
				
			}


			return countryCodeMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
}
	public ViewSchemCreationVO getViewSchemCreation() throws SQLException {

		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		
		try {
		
		 jdbcConnection = dataSource.getConnection();
		ViewSchemCreationVO viewSchemCreation = new ViewSchemCreationVO();
		String sql = "select Company_Code,scheme_No,Scheme_Category,Scheme_Type,From_Date,To_Date,Active,Exclude_CST_Sale,Sales_Org,"
                +"Distrbution_Channel,Division,Billing_Type,Sales_Office,Sales_Group,Plant,Region,Sales_District,"
                +"Country_Code,City_Code,Customer_Group,Customer,Shipping_Cond,Material_Group,Material,Business_Area,"
                +"Payment_Method,Profit_Center,GL_Account,Cost_Center,Prev_From_Date,Prev_To_Date"
                +" from qd_definition_header order by QD_Definition_Header_ID desc limit 1 ";
		//connect();
		 statement = jdbcConnection.createStatement();
		 resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			
			   String companyCode = resultSet.getString("Company_Code");
			   String compName="";
			   if(companyCode !=null && !companyCode.equals(""))
			   {
				   String query2 = "select Company_Name from company_codes where CoCd = '"+companyCode+"' ";
				   Statement statement2 = jdbcConnection.createStatement();
				   ResultSet resultSetComp = statement2.executeQuery(query2);
					while (resultSetComp.next()) {
				     compName =resultSetComp.getString("Company_Name");
				    if(compName == null)
				    	compName="NA";
					}
					resultSetComp.close();
					statement2.close();
			   }
			   else
			   {
				   compName="NA";
			   }
			   viewSchemCreation.setCompName(compName);
			   String schemNumb = resultSet.getString("scheme_No");
			   if(schemNumb == null || schemNumb.equals(""))
			   {
				   schemNumb = "NA";
			   }
			   
			   viewSchemCreation.setSchemNumb(schemNumb);
			   String schemCategory =  resultSet.getString("Scheme_Category");
			   if(schemCategory == null || schemCategory.equals(""))
			       schemCategory = "NA";
			   viewSchemCreation.setSchemCategory(schemCategory);
			   String schemType = resultSet.getString("Scheme_Type");
			   if(schemType == null || schemType.equals(""))
				   schemType = "NA";
			   viewSchemCreation.setSchemType(schemType);
			   String fromDate = resultSet.getString("From_Date");
			   if(fromDate == null || fromDate.equals(""))
				   fromDate = "NA";
			   viewSchemCreation.setFromDate(fromDate);
			   String toDate = resultSet.getString("To_Date");
			   if(toDate == null || toDate.equals(""))
				   toDate = "NA";
			   viewSchemCreation.setToDate(toDate);
			   String active = resultSet.getString("Active");
			   if(active == null || active.equals(""))
				   active = "NA";
			   viewSchemCreation.setActive(active);
			   String excCstScale = resultSet.getString("Exclude_CST_Sale");
			   if(excCstScale ==null || excCstScale.equals(""))
				   excCstScale = "NA";
			   viewSchemCreation.setExcCstScale(excCstScale);
			   String billingType = resultSet.getString("Billing_Type");
			   if(billingType == null || billingType.equals(""))
				   billingType = "NA";
			   if(billingType != null && billingType.equals("Select"))
				   billingType = "All";
			   viewSchemCreation.setBillingType(billingType);
			   String salesOffice = resultSet.getString("Sales_Office");
			   if(salesOffice ==null || !salesOffice.equals(""))
				   salesOffice = "NA";
			   if(salesOffice != null && salesOffice.equals("Select"))
				   salesOffice = "All";
			   viewSchemCreation.setSalesOffice(salesOffice);
			   String salesGrp = resultSet.getString("Sales_Group");
			   if(salesGrp ==null || salesGrp.equals(""))
				   salesGrp = "NA";
			   if(salesGrp != null && salesGrp.equals("Select"))
				   salesGrp = "All";
			   viewSchemCreation.setSalesGrp(salesGrp);
			   String plant  = resultSet.getString("Plant");
			   if(plant ==null || !plant.equals(""))
				   plant = "NA";
			   if(plant!=null && plant.equals("Select"))
				   plant = "All";
			   viewSchemCreation.setPlant(plant);
			   String salesDistrict = resultSet.getString("Sales_District");
			   if(salesDistrict ==null || salesDistrict.equals(""))
				   salesDistrict = "NA";
			   if(salesDistrict!=null && salesDistrict.equals("Select"))
				   salesDistrict = "All";
			   viewSchemCreation.setSalesDistrict(salesDistrict);
			   String countryCode = resultSet.getString("Country_Code");
			   if(countryCode ==null || countryCode.equals(""))
				   countryCode = "NA";
			   if(countryCode.equals("Select"))
				   countryCode = "All";
			   viewSchemCreation.setCountryCode(countryCode);
			   String cityCode = resultSet.getString("City_Code");
			   if(cityCode == null || cityCode.equals(""))
				   cityCode = "NA";
			   if(cityCode != null && cityCode.equals("Select"))
				   cityCode = "All";
			   viewSchemCreation.setCityCode(cityCode); 
			   String customerGrp =  resultSet.getString("Customer_Group");
			   if(customerGrp == null || customerGrp.equals(""))
				   customerGrp = "NA";
			   if(customerGrp != null && customerGrp.equals("Select"))
				   customerGrp = "All";
			   viewSchemCreation.setCustomerGrp(customerGrp);
			   String customer  = resultSet.getString("Customer");
			   if(customer == null || customer.equals(""))
				   customer = "NA";
			   if(customer != null && customer.equals("Select"))
				   customer = "All";
			   viewSchemCreation.setCustomer(customer);
			   String shoppingCondition =  resultSet.getString("Shipping_Cond");
			   if(shoppingCondition == null || shoppingCondition.equals(""))
				   shoppingCondition = "NA";
			   if(shoppingCondition != null && shoppingCondition.equals("Select"))
				   shoppingCondition = "All";
			   viewSchemCreation.setShoppingCondition(shoppingCondition);
			   String materialGroup = resultSet.getString("Material_Group");
			   if(materialGroup == null || materialGroup.equals(""))
				   materialGroup = "NA";
			   if(materialGroup != null && materialGroup.equals("Select"))
				   materialGroup = "All";
			   viewSchemCreation.setMaterialGroup(materialGroup);
			   String material = resultSet.getString("Material");;
			   if(material == null || material.equals(""))
				   material = "NA";
			   if(material != null && material.equals("Select"))
				   material = "All";
			   viewSchemCreation.setMaterial(material);
			   
			   String buisnessArea = resultSet.getString("Business_Area");
			   if(buisnessArea == null || buisnessArea.equals(""))
				   buisnessArea = "NA";
			   if(buisnessArea != null && buisnessArea.equals("Select"))
				   buisnessArea = "All";
			   viewSchemCreation.setBuisnessArea(buisnessArea);
			   String paymentMethod = resultSet.getString("Payment_Method");
			   if(paymentMethod == null || paymentMethod.equals(""))
				   paymentMethod = "NA";
			   if(paymentMethod != null && paymentMethod.equals("Select"))
				   paymentMethod = "All";
			   viewSchemCreation.setPaymentMethod(paymentMethod);
			    String profitCenter = resultSet.getString("Profit_Center");
			   if(profitCenter == null || profitCenter.equals(""))
				   profitCenter = "NA";
			   if(profitCenter == null || profitCenter.equals("Select"))
				   profitCenter = "All";
			   viewSchemCreation.setProfitCenter(profitCenter);
			   String glAccount = resultSet.getString("GL_Account");
			   if(glAccount == null || glAccount.equals(""))
				   glAccount = "NA";
			   if(glAccount != null && glAccount.equals("Select"))
				   glAccount = "All";
			   viewSchemCreation.setGlAccount(glAccount);
			    String costCenter = resultSet.getString("Cost_Center");
			    if(costCenter ==null || costCenter.equals(""))
			    	costCenter = "NA";
				   if(costCenter != null && costCenter.equals("Select"))
					   costCenter = "All";
				   viewSchemCreation.setCostCenter(costCenter);
				   
				   String prevfromDate = resultSet.getString("Prev_From_Date");
				   if(prevfromDate == null || prevfromDate.equals(""))
					   prevfromDate = "NA";
				   viewSchemCreation.setPrevfromDate(prevfromDate);
				   String prevToDate = resultSet.getString("Prev_To_Date");
				   if(prevToDate == null || prevToDate.equals(""))
					   prevToDate = "NA";
				   viewSchemCreation.setPrevToDate(prevToDate);
			   String sorg = resultSet.getString("Sales_Org");
			   String soName="";
			   if(sorg !=null && !sorg.equals(""))
			   {
				   String query2 = "select Name from sales_organization where SOrg = '"+sorg+"'";
				   Statement statementSo = jdbcConnection.createStatement();
				   ResultSet resultSetSO = statementSo.executeQuery(query2);
					while (resultSetSO.next()) {
						soName =resultSetSO.getString("Name");
				    if(soName == null)
				    	soName="NA";
					}
					resultSetSO.close();
					statementSo.close();
			   }
			   viewSchemCreation.setSoName(soName);
			   String dchl  = resultSet.getString("Distrbution_Channel");
			   String dchlName = "";
			   if(dchl !=null && !dchl.equals(""))
			   {
				   String query2 = "select Name from distribution_channel where DChl = '"+dchl+"'";
				   Statement statementDchl = jdbcConnection.createStatement();
				   ResultSet resultSetDchl = statementDchl.executeQuery(query2);
					while (resultSetDchl.next()) {
						dchlName =resultSetDchl.getString("Name");
				    if(dchlName == null)
				    	dchlName="NA";
					}
					
					resultSetDchl.close();
					statementDchl.close();
			   }
			   viewSchemCreation.setDchlName(dchlName);
			   String div = resultSet.getString("Division");
			   String divName ="";
			   if(div !=null && !div.equals(""))
			   {
				   String query2 = "select Name from division where Dv = '"+div+"'";
				   Statement statementDiv = jdbcConnection.createStatement();
				   ResultSet resultSetDiv = statementDiv.executeQuery(query2);
					while (resultSetDiv.next()) {
						divName =resultSetDiv.getString("Name");
				    if(divName == null)
				    	divName="NA";
					}
					resultSetDiv.close();
					statementDiv.close();
			   }
			   viewSchemCreation.setDivName(divName);
			   String region = resultSet.getString("Region");;
			   String regionName = "";
			   if(region !=null && !region.equals(""))
			   {
				   String query2 = "select Description from region where Rg = '"+region+"'";
				   Statement statementRgn = jdbcConnection.createStatement();
				   ResultSet resultSetRgn = statementRgn.executeQuery(query2);
					while (resultSetRgn.next()) {
						regionName =resultSetRgn.getString("Description");
				    if(regionName == null)
				    	regionName="NA";
					}
					resultSetRgn.close();
					statementRgn.close();
			   }
			   viewSchemCreation.setRegionName(regionName);
			
			
		}
	
	
	return viewSchemCreation;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
		}

	public Map<String,SchemeTypeVO> getSchemeType(String scheme_type) throws SQLException {
		
		Connection jdbcConnection =null;
		Statement statement = null;
		ResultSet resultSet =null;
		
		try {
		jdbcConnection = dataSource.getConnection();
		Map<String,SchemeTypeVO> countryCodeMap = new LinkedHashMap<String,SchemeTypeVO>();
		String sql = "SELECT scheme_type, scheme_name FROM scheme_def where scheme_type='"+scheme_type+"'";
		statement = jdbcConnection.createStatement();
		resultSet = statement.executeQuery(sql);
		int i=1;
		while (resultSet.next()) {
			SchemeTypeVO schTypeVO = new SchemeTypeVO();
			String schType = resultSet.getString("scheme_type");
			schTypeVO.setSchemeType(resultSet.getString("scheme_type"));
			schTypeVO.setSchemeName(resultSet.getString("scheme_name"));
			countryCodeMap.put(i+"", schTypeVO);
			i++;
			
		}
	
	
	return countryCodeMap;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			resultSet.close();
			statement.close();
			jdbcConnection.close();
		}
		return null;
		}

public Map<String, String> getRange(String sch_key,String pfrom,String pto) throws SQLException {
		
	Connection jdbcConnection =null;
	Statement statement = null;
	Statement statement1 = null;
	ResultSet resultSet =null;
	ResultSet resultSet1 = null;
	try {
	
		jdbcConnection = dataSource.getConnection();
		Map<String,String> map = new HashMap<String,String>();
		String sql = "SELECT sch_key, table_name, field_name FROM string_keys where sch_key='"+sch_key+"'";
		System.out.println(sql);
		 statement = jdbcConnection.createStatement();
		 resultSet = statement.executeQuery(sql);
		
		 statement1 = jdbcConnection.createStatement();
		
		
		if (resultSet.next()) {
			
			String table_name = resultSet.getString("table_name");
			String field_name =  resultSet.getString("field_name");
			String sql1="SELECT "+ field_name +" FROM  "+table_name+" WHERE  "+field_name+" BETWEEN '"+pfrom+"' AND  '"+pto+"'";
			System.out.println(sql1);
			 resultSet1 = statement.executeQuery(sql1);
			while (resultSet1.next()) {
			map.put(resultSet1.getString(field_name), sch_key);	
			}
			
		}
		
	
		return map;
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		resultSet.close();
		statement.close();
		jdbcConnection.close();
	}
	return null;
	}
	
public String getMaxId() throws SQLException {
	
	Connection jdbcConnection =null;
	Statement statement = null;
	ResultSet resultSet =null;
	
	try {
	
		jdbcConnection = dataSource.getConnection();
		Map<String,String> map = new HashMap<String,String>();
		String sql = "select max(header_id)+1 as id from sch_header";
		System.out.println(sql);
		 statement = jdbcConnection.createStatement();
		 resultSet = statement.executeQuery(sql);
		 int maxId = 1;
			String sr = "";
			if (resultSet.next()) {

				maxId = resultSet.getInt("id");

				if (maxId < 10) {
					sr = "00" + maxId;
				} else if (maxId < 100) {
					sr = "0" + maxId;
				} else {
					sr = String.valueOf(maxId);
				}

				System.out.println(sr);

			}
	
			
		}
		
		return maxId;
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		resultSet.close();
		statement.close();
		jdbcConnection.close();
	}
	return null;
	}

public String getNomenclature(String customer,String saleOrg,String division,String region,String stateCode,String distributionChannel,String schemeCategory,String schemeType)throws SQLException {
	/*example query:-
	select SPART as Dv,VTEXT as Name from TSPAT where spart='01' limit 1;
	select BLAND as Rg,BEZEI as Description from T005U WHERE LAND1='IN' and bland='1' limit 1;
	SELECT cityc as city_id,BEZEI as Description FROM T005h where LAND1='IN' and cityc='9999' limit 1;
	select VTWEG as DChl,VTEXT as Name from TVTWT  where VTWEG='01' limit 1;
	select * from knvv
	CEMSTTN1TRSSRBCD082018001
	x.chars().filter(c -> Character.isUpperCase(c))
                .forEach(c -> System.out.print((char) c + " "));
                
                select kvgr2 from knvv; state code
select VKORG  from TVKO  sale org
SELECT kunnr as Customer,Name1 as Name1  FROM kna1 customer
State code:---
-----------------------------
select kvgr2 from knvv where vkorg='1000' and vtweg='1' and kunnr='7013899';
select VKORG  from TVKO
SELECT kunnr as Customer,Name1 as Name1  FROM kna1;
select vkorg,vtweg,kunag from wb2_v_vbrk_vbrp2


State code query==
select kvgr2 from knvv where vkorg='1000' and vtweg='1' and kunnr='7013899';

	*/
	Connection jdbcConnection =null;
	Statement statement = null;
	ResultSet resultSet =null;
	String nomenclatureNo="";
	try {
		
		
		jdbcConnection = dataSource.getConnection();
		if(division.length()>0) {
		String sqlDivision = "select SPART as Dv,VTEXT as Name from TSPAT  where SPART='"+division+"' limit 1";
		statement = jdbcConnection.createStatement();
		resultSet = statement.executeQuery(sqlDivision);
		if (resultSet.next()) {
			nomenclatureNo=	resultSet.getString("Dv").substring(0, 3);
		}
		}
		
		if(region.length()>0) {
			String sqlRegion = "select BLAND as Rg,BEZEI as Description from T005U WHERE LAND1='IN' and BLAND='"+region+"' limit 1";
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(region);
			if (resultSet.next()) {
				nomenclatureNo +=	resultSet.getString("Rg").substring(0, 2);
			}

		}
		
		if(stateCode.length()>0) {
		String sqlCityCode = "select kvgr2 from knvv where vkorg='"+saleOrg+"' and vtweg='"+distributionChannel+"' and kunnr='"+customer+"' limit 1";
		
		statement = jdbcConnection.createStatement();
		resultSet = statement.executeQuery(sqlCityCode);
		if (resultSet.next()) {
			nomenclatureNo +=	resultSet.getString("kvgr2");
		}
		}
		if(distributionChannel.length()>0) {
	//	String sqlDistributionChannel = "select VTWEG as DChl,VTEXT as Name from TVTWT  where VTWEG='"+sqlDistributionChannel+"' limit 1";
		statement = jdbcConnection.createStatement();
		resultSet = statement.executeQuery(distributionChannel);
		if (resultSet.next()) {
			nomenclatureNo +=	resultSet.getString("DChl").substring(0, 2);
		}
		}
		
		
		
		statement = jdbcConnection.createStatement();
		
		String divName = "";
		while (resultSet.next()) {
		divName = resultSet.getString("Name");
		}


		return divName.substring(0,3);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		resultSet.close();
		statement.close();
		jdbcConnection.close();
	}
	return null;
}


public String updateAproved(String scheme_no)throws SQLException {
	Connection jdbcConnection =null;
	Statement statement = null;
	String result="";
	
	try {
		
		jdbcConnection = dataSource.getConnection();
		String sql = "update  sch_header set approve_status='Approved'   where scheme_no='"+scheme_no+"'";
		statement = jdbcConnection.createStatement();
		 statement.executeUpdate(sql);
		 result="saved";
		return  result;

		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		statement.close();
		jdbcConnection.close();
	}
	return null;
}



}
