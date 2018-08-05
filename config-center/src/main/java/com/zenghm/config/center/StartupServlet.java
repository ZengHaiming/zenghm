package com.zenghm.config.center;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @author Airlen
 * @date 2018/08/05
 * Class name:StartupServlet
 */
public class StartupServlet extends HttpServlet {
    private static final long serialVersionUID = -1043067318149679716L;

    @Override
    public void init() throws ServletException {
        ConfigManager.init();
    }
}
