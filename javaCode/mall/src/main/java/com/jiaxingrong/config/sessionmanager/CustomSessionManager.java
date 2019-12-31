package com.jiaxingrong.config.sessionmanager;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class CustomSessionManager extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String sessionId = ((HttpServletRequest) request).getHeader("X-cskaoyan-mall-Admin-Token");
        if (sessionId != null && !"".equals(sessionId))
        {
            return sessionId;
        }
        return super.getSessionId(request, response);
    }
}
