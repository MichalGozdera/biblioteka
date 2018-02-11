package com.sda.biblioteka.toolsAndOldStuff;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream out=response.getOutputStream();
        out.println("Hello servlet!!!");
        String param1=request.getParameter("param1");
        out.println("Podany parametr to: " +param1);

    }
}
