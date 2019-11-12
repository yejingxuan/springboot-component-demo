package com.yjx.demo.retry.utils;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Spring工具类
 */
@Slf4j
@Component
public class SpringUtil implements ApplicationContextAware {

    /**
     * spring全局配置项
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        if (applicationContext == null) {
            applicationContext = context;
            log.info(
                    "ApplicationContext init success,you can invoke by SpringUtil.getApplicationContext() to get ApplicationContext,init bean count="
                            + applicationContext.getBeanDefinitionCount() + ",bean=" + JSONObject
                            .toJSONString(applicationContext.getBeanDefinitionNames()));
        }
    }

    /**
     * @return spring全局配置项
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     */
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 通过class获取Bean.
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    /**
     * 获取实现相关接口的类关系
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }

    /**
     * 发布事件
     */
    public static void publishEvent(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }

    /**
     * 获取环境信息
     */
    public static Environment getEnvironment() {
        return applicationContext.getEnvironment();
    }

}
