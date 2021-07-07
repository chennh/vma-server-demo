package com.vma.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * DESCRIPTION
 *
 * @author: chennaihua
 * @version: 1.created by chennaihua on 2021/6/30.
 */
@ConfigurationProperties
@Data
public class ConfigProperties {

    @Autowired
    private Upload upload;


    /**
     * 上传
     */
    @Data
    @Component
    @ConfigurationProperties(prefix = "app.upload")
    public static class Upload {
        private String tempDir;
    }
}
