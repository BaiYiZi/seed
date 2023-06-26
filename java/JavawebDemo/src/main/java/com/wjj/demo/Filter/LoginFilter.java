package com.wjj.demo.Filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = { "/" })
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化LoginFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        //判断访问路径是否为登录或注册路径
        String path = request.getContextPath();
        System.out.println(path);
        //如果路径中存在"login","Login","regist","Regist"说明要执行注册和登录操作
        if(path.contains("login") || path.contains("Login") || path.contains("regist") || path.contains("Regist")){
            //放行
            filterChain.doFilter(request,response);
        }
        //判断是否为已登陆状态
        else if(request.getSession().getAttribute("isLogin") == "yes"){
            //为登陆状态则放行
            filterChain.doFilter(request,response);
        }
        //既不是执行注册和登录操作，又不是已登陆状态
        else {
            System.out.println("既不是执行注册和登录操作，又不是已登陆状态");
            //则重定向至登录页面
            response.sendRedirect("/emp/login.html");
        }
    }


    @Override
    public void destroy() {
        System.out.println("LoginFilter destroyed");
    }
}
