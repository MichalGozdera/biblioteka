package com.sda.biblioteka.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AddBookFilter", servletNames = "AddBookServlet")
public class addBookFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletrequest;
        HttpServletResponse response = null;
        if (servletresponse instanceof HttpServletResponse) {
            response = (HttpServletResponse) servletresponse;
        }
        if (!httpServletRequest.getMethod().equalsIgnoreCase("POST")) {
            chain.doFilter(servletrequest, servletresponse);
            return;
        }
        String title = servletrequest.getParameter("title");
        String author = servletrequest.getParameter("author");


        if (title == null || title.isEmpty()) {
            response.sendRedirect("./error?message=bad_title");
            return;
        }
        if (author == null || author.isEmpty()) {
            response.sendRedirect("./error?message=bad_author");
            return;
        }
        chain.doFilter(servletrequest, servletresponse);
    }

    @Override
    public void destroy() {

    }
}
