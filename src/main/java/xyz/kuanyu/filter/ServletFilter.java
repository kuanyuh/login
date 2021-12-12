package xyz.kuanyu.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "ServletFilter", value="/*")
public class ServletFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String currentURL = httpRequest.getRequestURI();
        System.out.println("过滤器-currentURL"+currentURL);

        System.out.println(currentURL.split("/").length);

        if (currentURL.equals("/login/profile.jsp")){
            HttpSession session = httpRequest.getSession(false);//是否创建新的session
            if (session==null || session.getAttribute("userid")==null) {
                httpResponse.sendRedirect(httpRequest.getContextPath());
                return;
            }
        }

        chain.doFilter(httpRequest, httpResponse);
    }
}
