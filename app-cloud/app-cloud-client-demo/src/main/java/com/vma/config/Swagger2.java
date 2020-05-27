package com.vma.config;

import com.vma.core.config.VmaSwaggerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 配置swagger
 *
 * @author huang
 */
@EnableSwagger2
@Configuration
public class Swagger2 extends VmaSwaggerConfig {

    /**
     * manager rest api
     *
     * @return Docket
     */
    @Bean
    public Docket createManagerRestApi() {
        return createRestApiDoc("管理后台", "com.vma", "/manager/v1.0/**");
    }

}
