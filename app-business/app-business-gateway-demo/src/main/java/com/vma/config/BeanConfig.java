package com.vma.config;

import com.vma.logger.service.ISystemLoggerService;
import com.vma.logger.service.impl.DaemonSystemLoggerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        return new DaemonSystemLoggerServiceImpl(new AppSystemLogger());
    }
}
