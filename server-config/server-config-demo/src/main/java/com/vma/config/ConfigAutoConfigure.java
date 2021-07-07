package com.vma.config;

import com.vma.properties.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * DESCRIPTION
 *
 * @author: chennaihua
 * @version: 1.created by chennaihua on 2019/6/12.
 */
@Configuration
@EnableConfigurationProperties(ConfigProperties.class)
public class ConfigAutoConfigure {

    @Autowired
    private ConfigProperties configProperties;

}
