package com.dalmia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.dalmia.dce.fileupload.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class RebateProcessApplication {

	public static void main(String[] args) {
		SpringApplication.run(RebateProcessApplication.class, args);
	}
}
