package com.jiaxingrong.config;

import com.jiaxingrong.config.realm.AdminRealm;
import com.jiaxingrong.config.realm.CustomRealm;
import com.jiaxingrong.config.sessionmanager.CustomSessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 需要setLoginUrl吗
        shiroFilterFactoryBean.setLoginUrl("http://localhost:9527/#/login?redirect=%2Fdashboard");
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 对请求过滤，这个是有顺序的
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/admin/auth/login", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/Pic/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    // shiro核心组件：SecurityManager
    @Bean
    public DefaultWebSecurityManager securityManager(AdminRealm adminRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(adminRealm);
        defaultWebSecurityManager.setSessionManager(webSessionManager());
        return defaultWebSecurityManager;
    }

    @Bean
    public DefaultWebSessionManager webSessionManager() {
        CustomSessionManager customSessionManager = new CustomSessionManager();
        return customSessionManager;
    }

    // 声明式鉴权，注册需要的组件
    // 开启注解
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }


}
