package com.vma.app.demo.service.config;

import com.vma.assist.global.globalenum.GlobalOrderConstant;
import com.vma.assist.wraps.SpringYmlConfigLoadWrap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author huang
 * @create 2018-10-22 21:23
 **/
@Slf4j
public class ApplicationEnvironListener implements
        SpringApplicationRunListener, PriorityOrdered {

    private SpringApplication application;

    private String[] args;

    /**
     * 通过反射创建该实例对象的，构造方法中的参数要加上如下参数
     */
    public ApplicationEnvironListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    @Override
    public void starting() {
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        SpringYmlConfigLoadWrap.loadYmlConfigAfterFlag(context, "config/application-service.yml");
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
        return GlobalOrderConstant.APP_SERVICE;
    }

}
