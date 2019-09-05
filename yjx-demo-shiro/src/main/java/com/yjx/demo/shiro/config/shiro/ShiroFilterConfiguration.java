package com.yjx.demo.shiro.config.shiro;

import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.ShiroWebFilterConfiguration;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroFilterConfiguration extends ShiroWebFilterConfiguration {

    @Override
    protected ShiroFilterFactoryBean shiroFilterFactoryBean() {
        //采用父类的默认方法生成shiroFilterFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = super.shiroFilterFactoryBean();
        //获取shiroFilterFactoryBean里的Filters集合
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        //put进一个自己编写的过滤器，并命名，上面会引用到
        filters.put("corsFilterAAAA", new ShiroFilter());
        shiroFilterFactoryBean.setFilters(filters);
        return shiroFilterFactoryBean;
    }

}
