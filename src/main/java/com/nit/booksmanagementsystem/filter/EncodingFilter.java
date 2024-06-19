package com.nit.booksmanagementsystem.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * EncodingFilter 作用是设置请求和响应的编码格式为 UTF-8
 */
@WebFilter(urlPatterns = {"/*", "*.jsp"})
public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");  //设置请求的编码
        response.setCharacterEncoding("UTF-8"); //设置响应的编码
        response.setContentType("text/html");   //设置响应的文档类型
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
