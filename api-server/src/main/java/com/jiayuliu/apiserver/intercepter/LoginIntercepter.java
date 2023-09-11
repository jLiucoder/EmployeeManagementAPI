package com.jiayuliu.apiserver.intercepter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jiayuliu.apiserver.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component // register this class to spring container
public class LoginIntercepter implements HandlerInterceptor {

    @Autowired
    private Gson gson;

    @Override //before the request is handled
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        GsonBuilder builder = new GsonBuilder();

        String url = request.getRequestURI().toString();
        log.info("request url: {}", url);

        if(url.contains("/login")){
            return true;
        }

        String jwt = request.getHeader("token");

        if(!StringUtils.hasLength(jwt)){
            log.info("jwt is empty");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = gson.toJson(error);
            response.getWriter().write(notLogin);
            return false;
        }
        log.info("works fine, jwt: {}", jwt);

        return true;
    }

    @Override // after the request is handled
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override // after the response is handled
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
