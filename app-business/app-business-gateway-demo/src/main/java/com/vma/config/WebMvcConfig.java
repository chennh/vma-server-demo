package com.vma.config;

import com.vma.authorization.interceptor.IAuthorizationInterceptor;
import com.vma.core.config.VmaWebMvcConfig;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IAuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(
                authorizationInterceptor.addExcludeMethod("options")
                        .addPathPattern("/system/**", () -> true))
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources")
                .excludePathPatterns("/v2/api-docs");
    }

}
