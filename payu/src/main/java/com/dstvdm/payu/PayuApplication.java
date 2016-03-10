package com.dstvdm.payu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
@EnableDiscoveryClient
@EnableHystrix
public class PayuApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayuApplication.class, args);
	}
}
