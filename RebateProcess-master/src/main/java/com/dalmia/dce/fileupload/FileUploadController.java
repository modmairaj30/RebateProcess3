package com.dalmia.dce.fileupload;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dalmia.dce.vo.StatusVO;

@RestController
@CrossOrigin("*")
public class FileUploadController {

	@Autowired
	ExcelUploadService excUplService;
	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	// @PostMapping("/")
	@RequestMapping(value = "/fileupload", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE }, params={})
	public StatusVO handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("ftype")  Optional<String> ftype)
			throws FileNotFoundException, InvalidFormatException, IOException {
		if(!ftype.isPresent()) {
			ftype=Optional.of("SalesDistrict");
		}
		System.out.println("handleFileUpload " + file.getOriginalFilename()+" ftype = "+ftype);
		StatusVO sts = new StatusVO();
		if (file.isEmpty()) {
			sts.setCode("500");
			sts.setMessage("Empty File");
			sts.setStatus("Fail");

			return sts;
		} else {
			storageService.store(file);
			
			try {
			
				String key = excUplService.loaddataFromExcel(file.getOriginalFilename(),ftype);
				sts.setCode("200");
				sts.setMessage(key);
				sts.setStatus("success");
			} catch (Exception e) {
				sts.setCode("500");
				sts.setMessage(e.getMessage());
				sts.setStatus("Fail");
				e.printStackTrace();
			}

			return sts;
		}
	}

}
