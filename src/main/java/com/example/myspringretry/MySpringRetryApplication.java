package com.example.myspringretry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@EnableRetry
public class MySpringRetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringRetryApplication.class, args);
	}
	
	@Autowired
	public CheckStatusService checkStatusService;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String createOrder() {
		return checkStatusService.checkStatus("1");
	}

}
