package com.dstvdm.consulservice;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by paul on 2016/03/07.
 */

@ConfigurationProperties("consulservice")
@Data
public class ConsulProperties {

    private String prop = "default value";

    public String getProp() {
        return prop;
    }
}