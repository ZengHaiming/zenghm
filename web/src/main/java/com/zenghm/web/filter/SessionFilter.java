package com.zenghm.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.zenghm.web.controller.ControllerConstant;

/**
 * Create date:2018/06/12.
 * Created by: Airlen.
 * Class name:SessionFilter.
 * 注意：不能拦截了静态资源，否则静态资源无法加载
 */
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        String path = uri.substring(request.getContextPath().length());
        if(!isLoginReq(path) &&
                (request.getSession()==null ||
                        request.getSession().getAttribute(ControllerConstant.AUTHENTICATED_USER)==null)){
            //跳转欢迎页面
            response.sendRedirect(request.getContextPath() +"/login.jsp");
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isLoginReq(String path){
        String [] loginReqAdd = new String[]{"/user/login.do","/login.jsp","/"};
        for (String str:loginReqAdd){
            if (str.equals(path)) return true;
        }
        return false;
    }

}
