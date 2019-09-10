package com.vma.config;

import com.vma.assist.wraps.LogWrap;
import com.vma.assist.wraps.SpringYmlConfigWrap;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author huang
 * @create 2018-10-22 21:23
 **/

public class ApplicationEnvironListener implements
        SpringApplicationRunListener, PriorityOrdered {

    private Logger logger = LogWrap.getLogger(this.getClass());

    /**
     * 通过反射创建该实例对象的，构造方法中的参数要加上如下参数
     */
    public ApplicationEnvironListener(SpringApplication application, String[] args) {
    }

    @Override
    public void starting() {
        logger.info("load app-config");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        SpringYmlConfigWrap.loadYmlAndProfileConfig(context, "config/application-config.yml");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
    }

    @Override
    public int getOrder() {
        return 12;
    }
}
