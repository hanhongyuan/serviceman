package com.dstvdm.payu.rest;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by paul on 2016/03/10.
 */
@RestController
@RequestMapping("/payu")
public class PayUController {

    @Autowired
    private OAuth2RestOperations restTemplate;

    @Value("${payu.resource.baseResourceUri}")
    private String resourceURI;


    @RequestMapping("/")
    public JsonNode home() {
        return restTemplate.getForObject(resourceURI, JsonNode.class);
    }
}
