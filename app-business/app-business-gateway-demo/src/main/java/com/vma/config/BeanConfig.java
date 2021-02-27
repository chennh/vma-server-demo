package com.vma.config;

import com.vma.logger.entity.SystemLogger;
import com.vma.logger.service.ISystemLoggerService;
import com.vma.logger.service.impl.DaemonSystemLoggerServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * DESCRIPTION
 *
 * @author: chennaihua
 * @version: 1.created by chennaihua on 2019/6/12.
 */
@Configuration
public class BeanConfig {

    /**
     * 存储日志
     *
     * @return
     */
    @Bean
    public ISystemLoggerService systemLoggerService() {
        return new DaemonSystemLoggerServiceImpl(new ISystemLoggerService() {
            @Override
            public void before(JoinPoint joinPoint, SystemLogger systemLogger) {

            }

            @Override
            public void afterReturning(JoinPoint joinPoint, SystemLogger systemLogger) {

            }

            @Override
            public void afterThrowing(JoinPoint joinPoint, SystemLogger systemLogger) {

            }

            @Override
            public Map<String, Object> globalParameterMap() {
                return null;
            }
        });
    }
}
