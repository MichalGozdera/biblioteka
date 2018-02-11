package com.sda.biblioteka.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebFilter(filterName = "AddInfoFilter", servletNames = "AddBookServlet")
public class AddInfoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse response = null;
        if (servletResponse instanceof HttpServletResponse) {
            response = (HttpServletResponse) servletResponse;
        }
        if (!httpServletRequest.getMethod().equalsIgnoreCase("POST")) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        String data = LocalDate.now().toString();
        String userAgent = httpServletRequest.getHeader("User-Agent");
        httpServletRequest.setAttribute("data",data);
        httpServletRequest.setAttribute("userAgent",userAgent);
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
