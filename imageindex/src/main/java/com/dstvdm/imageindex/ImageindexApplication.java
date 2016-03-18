package com.dstvdm.imageindex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
//@EnableDiscoveryClient
public class ImageindexApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageindexApplication.class, args);
    }

    @Bean
    public MetadataExtractor extractor() {
        return new MetadataExtractor();
    }
}
