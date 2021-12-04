package xyz.kuanyu.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/profile.html")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String currentURL = request.getRequestURI();
//        System.out.println(currentURL);
        System.out.println("过滤器");
        HttpSession session = request.getSession(false);
        if (session==null || session.getAttribute("userid")==null) {
//            System.out.println(session.getAttribute("userid"));
            response.sendRedirect(request.getContextPath());
            return;
        }

        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
