package com.dstvdm.imageindex.rest;

import com.dstvdm.imageindex.MetadataExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by paul on 2016/03/18.
 */
@RestController
@RequestMapping("/imagemeta")
public class ImageController {

    @Autowired
    private MetadataExtractor extractor;

    @RequestMapping(method = RequestMethod.GET)
    public String findAllPersons() {
        return "I don't speak GET";
    }
}
