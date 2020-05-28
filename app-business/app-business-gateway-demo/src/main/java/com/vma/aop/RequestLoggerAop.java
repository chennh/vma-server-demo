package com.vma.aop;

import com.vma.core.mvc.logger.request.aop.helper.RequestLoggerHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 日志打印
 *
 * @author: chennaihua
 * @version: 1.created by chennaihua on 2019/5/11.
 */
@Aspect
@Component
public class RequestLoggerAop {

    /**
     * 拦截控制器
     */
    @Pointcut("execution(* com.vma.app.demo..controller..*Controller.*(..))")
    public void webLog() {
    }

    /**
     * 前置拦截
     *
     * @param joinPoint
     */
    @Before("webLog()")
    public void before(JoinPoint joinPoint) {
        RequestLoggerHelper.before(joinPoint);
    }

    /**
     * 后置拦截
     *
     * @param ret 控制器返回对象
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void afterReturn(Object ret) {
        RequestLoggerHelper.afterReturn(ret);
    }

    /**
     * 异常拦截
     *
     * @param exception
     */
    @AfterThrowing(pointcut = "webLog()", throwing = "exception")
    public void afterThrowing(Exception exception) {
        RequestLoggerHelper.afterThrowing(exception);
    }

}
