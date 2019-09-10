package com.vma.demo.gateway.config;

import com.vma.core.config.VmaWebMvcConfig;
import com.vma.demo.gateway.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * ${DESCRIPTION}
 *
 * @author: chennaihua
 * @version: 1.created by chennaihua on 2018/10/23.
 */
@Configuration
public class WebMvcConfig extends VmaWebMvcConfig {


    /**
     * @return AuthorizationInterceptor
     */
    @Bean
    public AuthorizationInterceptor authorizationInterceptor() {
        return new AuthorizationInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(authorizationInterceptor()).addPathPatterns("/**");
    }

}
