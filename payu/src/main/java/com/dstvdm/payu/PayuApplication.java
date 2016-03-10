package com.dstvdm.payu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard
public class PayuApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayuApplication.class, args);
	}
}
