package com.edmond.cam.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HttpInterceptorImpl implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpInterceptorImpl.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (response.getStatus() != HttpStatus.OK.value()) {
            LOGGER.info(">>> Handled request : " + response.getStatus() + " for " + request.getRequestURL());
        }
    }
}
