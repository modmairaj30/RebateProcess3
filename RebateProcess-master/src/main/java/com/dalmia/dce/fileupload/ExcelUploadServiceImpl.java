package com.dalmia.dce.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.dalmia.dce.entities.SchemeHeaderCond;

@Service
public class ExcelUploadServiceImpl implements ExcelUploadService {

	@Autowired
	ExcelUploadRepository excUpldRepo;
	@Autowired
	ServletContext servletContext;

	@Autowired
	private Environment env;

	@Override
	public String loaddataFromExcel(String fileName, Optional<String> ftype)
			throws InvalidFormatException, IOException {
		String key = "";
		List<SchemeHeaderCond> excelUplList = new ArrayList<SchemeHeaderCond>();
		

		String filePath = env.getProperty("com.dalmia.dce.fileupload.filePath");
		File file = new File(filePath + fileName);
		FileInputStream fis = new FileInputStream(file);

		OPCPackage pkg = OPCPackage.open(fis);
		XSSFWorkbook wb = new XSSFWorkbook(pkg);
		XSSFSheet sheet = wb.getSheetAt(0);
		Row row = null;
		
		if ( ftype != null && ftype.get()!= null && ftype.get().equals("SalesDistrict")) {
			

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);

				SchemeHeaderCond eu = new SchemeHeaderCond();
				eu.setFtype("SalesDistrict");
				if (!(row.getCell(0) == null)) {
					eu.setFvalue(row.getCell(0).getStringCellValue());
					eu.setFromValue(row.getCell(2).getNumericCellValue());
					eu.setToValue(row.getCell(3).getNumericCellValue());
					eu.setRate(row.getCell(4).getNumericCellValue());
					eu.setFilename(fileName);
				}
				excelUplList.add(eu);
			}
			UUID uuid = UUID.randomUUID();
			key = uuid.toString();
			System.out.println("Key:"+key);
			servletContext.setAttribute(key, excelUplList);
			
		}
		return key;
	}

}
