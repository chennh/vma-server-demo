package com.vma.demo.gateway.config;

import com.vma.core.config.VmaSwaggerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

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
