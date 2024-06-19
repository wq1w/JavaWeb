package com.nit.booksmanagementsystem.filter;

import com.nit.booksmanagementsystem.utils.CommonUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * LoginFilter 用于拦截所有的请求和 JSP 页面,检查用户是否登录
 * 如果访问的是登录页面、注册页面或首页,则直接放行
 * 否则需要判断用户是否登录,如果已登录且身份信息不为空,则放行
 * 如果未登录或身份信息为空,则跳转到登录页面
 */
// 定义 WebFilter, 拦截所有请求和 JSP 页面
@WebFilter(urlPatterns = {"/**", "*.jsp"})
public class LoginFilter implements Filter {
    // 初始化过滤器
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    // 执行过滤操作
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String servletPath = httpServletRequest.getServletPath();
        // 如果访问路径是登录界面或注册界面,或者是首页,则放行
        if (servletPath.equals("/login?method=login-page") ||
                servletPath.equals("/login.jsp") ||
                servletPath.equals("register.jsp") ||
                servletPath.equals("/login?method=register-page") ||
                servletPath.equals("/")) {
            filterChain.doFilter(request, response);
        } else {
            // 否则需要判断用户是否登录
            HttpSession session = httpServletRequest.getSession();

            String sessionUserName = (String) session.getAttribute("username");
            if (CommonUtil.getIsLogin()) {
                // 如果已登录,且身份信息不为空,则放行
                if (CommonUtil.getIdentity() == null) {
                    // 如果身份信息为空,则跳转到登录页面
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
                filterChain.doFilter(request, response);
            } else {
                // 如果未登录,则跳转到登录页面
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                filterChain.doFilter(request, response);
            }
        }
    }

    // 销毁过滤器
    @Override
    public void destroy() {
    }
}
