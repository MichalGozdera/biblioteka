package com.sda.biblioteka.toolsAndOldStuff;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    private static final String HTML_START = "<html>\n" +
            "<head>\n" +
            "    <meta charset=\"utf-8\" />\n" +
            "    <style>\n" +
            "        body {\n" +
            "            font-family: Comic Sans MS;\n" +
            "            background-color: black;\n" +
            "            color: yellow;\n" +
            "\n" +
            "        }\n" +
            "        a{\n" +
            "            font-family: Comic Sans MS;\n" +
            "            color: yellow;\n" +
            "        }\n" +
            "\n" +
            "\n" +
            "        img {\n" +
            "            z-index: -1;\n" +
            "            position: absolute;\n" +
            "            opacity: 0.2;\n" +
            "        }\n" +
            "\n" +
            "\n" +
            "        #Powitanie{\n" +
            "            float: left;\n" +
            "        }\n" +
            "        #login{\n" +
            "            float: right;\n" +
            "        }\n" +
            "    </style>\n" +
            "\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "<nav >\n" +
            "    <span id=\"Welcome\">";
    private static final String HTML_END = "</span>\n" +
            "    <span id=\"login\">\n" +
            "    <a href=\"./loginForm\">Login</a>\n" +
            "    <a href=\"./registrationForm\">Register</a>\n" +
            "    </span>\n" +
            "</nav>\n" +
            "<img src=\"http://mylittleponyn6.blog.pl/files/2015/02/rainbow_dash_s_hot_minute_by_mrlolcats17-d5lo21h.png\">\n" +
            "</br>\n" +
            "<h1 style=\"text-align: center\">\n" +
            "    Witaj na stronie biblioteki\n" +
            "</h1>";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();
        String param1 = request.getParameter("username");
        out.println(HTML_START);
        if (param1 != null) {
            out.println(param1);
        }
        out.println(HTML_END);
    }
}
