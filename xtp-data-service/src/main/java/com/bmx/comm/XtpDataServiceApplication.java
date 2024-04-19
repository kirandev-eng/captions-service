package com.bmx.comm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class XtpDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(XtpDataServiceApplication.class, args);
		System.out.println("XtpDataServiceApplication started...");
	}

}
