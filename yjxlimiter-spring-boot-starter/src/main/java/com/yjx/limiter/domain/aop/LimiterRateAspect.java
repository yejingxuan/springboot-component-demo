package com.yjx.limiter.domain.aop;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LimiterRateAspect {

    @Pointcut("@annotation(LimiterRate)")
    public void limiterRate() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("limiterRate()")
    public void doBefore(JoinPoint joinPoint) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        LimiterRate annotation = method.getAnnotation(LimiterRate.class);
        System.out.println(annotation.key()+","+annotation.rate()+","+annotation.rateTime());
    }

}
