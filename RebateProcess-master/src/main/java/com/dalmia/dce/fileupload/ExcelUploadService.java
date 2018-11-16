package com.dalmia.dce.fileupload;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import com.dalmia.dce.vo.StatusVO;

public interface ExcelUploadService {

	public String loaddataFromExcel(String file,Optional<String> ftype) throws FileNotFoundException, InvalidFormatException, IOException;


	//String loaddataFromExcel(MultipartFile file, ExcelUploadVO excelVO);
	

}
