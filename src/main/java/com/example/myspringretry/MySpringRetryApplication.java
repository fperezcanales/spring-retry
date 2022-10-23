package com.example.myspringretry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RestController
public class MySpringRetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringRetryApplication.class, args);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String createOrder() {
		return "Success";
	}
}
