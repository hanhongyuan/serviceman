package com.dstvdm.person;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
@EnableEurekaClient
public class PersonApplication {

    public static void main(String... args) {
        new SpringApplicationBuilder()
                .sources(PersonApplication.class)
                .showBanner(false)
                .run(args);
    }
}