package com.zenghm.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Create date:2018/06/12.
 * Created by: Airlen.
 * Class name:SessionListener.
 */
public class SessionListener implements HttpSessionListener {
    private Logger logger = LoggerFactory.getLogger(SessionListener.class);
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        //设置session 超时时间为 60 秒
        session.setMaxInactiveInterval(10*60);
        logger.debug(String.format("新用户接入网站：%1$s",session.getId()));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
