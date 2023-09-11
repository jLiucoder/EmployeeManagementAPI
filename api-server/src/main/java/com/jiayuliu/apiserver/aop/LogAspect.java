package com.jiayuliu.apiserver.aop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jiayuliu.apiserver.mapper.OperateLogMapper;
import com.jiayuliu.apiserver.pojo.OperateLog;
import com.jiayuliu.apiserver.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private Gson gson;

    @Around("@annotation(com.jiayuliu.apiserver.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

//        user id
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseToken(jwt);
        Integer userId = (Integer) claims.get("id");



//        time
        LocalDateTime operateTime = LocalDateTime.now();
//        class name
        String className = joinPoint.getTarget().getClass().getName();
//        method name
        String methodName = joinPoint.getSignature().getName();
//        params
        Object[] args = joinPoint.getArgs();
        String params = Arrays.toString(args);


//        start timer and call to original method
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
//        end timer
        long end = System.currentTimeMillis();

        String jsonVal = gson.toJson(result);
//        time elapsed
        long time = end - begin;

//        build log with above info
        OperateLog log = new OperateLog(null, userId, operateTime, className, methodName, params, jsonVal, time);

        operateLogMapper.insert(log);

        return result;
    }
}
