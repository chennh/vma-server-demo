package com.vma.config;

import com.vma.logger.entity.SystemLogger;
import com.vma.logger.service.ISystemLoggerService;
import org.aspectj.lang.JoinPoint;

import java.util.HashMap;
import java.util.Map;

/**
 * DESCRIPTION
 *
 * @author: chennaihua
 * @version: 1.created by chennaihua on 2021/5/7.
 */
public class AppSystemLogger implements ISystemLoggerService {


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
        Map<String, Object> map = new HashMap<>();
        return map;
    }
}
