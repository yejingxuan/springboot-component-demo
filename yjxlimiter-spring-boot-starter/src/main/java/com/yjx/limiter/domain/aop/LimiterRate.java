package com.yjx.limiter.domain.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LimiterRate {

    String key() default "limiterRate-common";

    long rate() default 10000L;

    long rateTime() default 1;

    TimeUnit rateTimeUnit() default TimeUnit.SECONDS;

}
