package com.dstvdm.uploader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@EnableAutoConfiguration
@ComponentScan
public class UploaderApplication {

    public static String ROOT = "upload-dir";

    public static void main(String[] args) {
        SpringApplication.run(UploaderApplication.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return (String[] args) -> {
            new File(ROOT).mkdir();
        };
    }
}
