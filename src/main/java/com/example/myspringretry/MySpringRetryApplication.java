package com.example.myspringretry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RestController
@EnableRetry
public class MySpringRetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringRetryApplication.class, args);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	@Retryable(value = { NumberFormatException.class }, maxAttempts = 5)
	public String createOrder() {
		Integer.parseInt("");
		return "Success";
	}

	@Recover
	public String recover(NumberFormatException ex){
		System.out.println("Recover method for NumberFormatException");
		return "Recover method for NumberFormatException";
	}
}
