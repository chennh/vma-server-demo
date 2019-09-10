package com.vma.demo.gateway.interceptor;

import com.vma.authorization.service.IAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author: chennaihua
 * @version: 1.created by chennaihua on 2018/10/23.
 */
//@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private IAuthorizationService authorizationService;

    private static final List<String> EXCLUDE_METHODS = Collections.singletonList("options");


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (EXCLUDE_METHODS.contains(request.getMethod().toLowerCase())) {
            return true;
        }
        //authorizationService.verify(request);
        return true;
    }
}
