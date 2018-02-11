package com.sda.biblioteka.toolsAndOldStuff;

import com.sda.biblioteka.servlets.spring.db.model.User;
import com.sda.biblioteka.servlets.spring.db.UserContext;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@WebServlet(name = "LoginServlet", urlPatterns = {"/loginForm", "/login"})
public class LoginServlet extends HttpServlet {
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
            "\n" +
            "        img {\n" +
            "            z-index: -1;\n" +
            "            position: absolute;\n" +
            "            opacity: 0.2;\n" +
            "        }\n" +
            "        input{\n" +
            "            font-size: inherit;\n" +
            "            height: 35px;\n" +
            "            float: right;\n" +
            "        }\n" +
            "        #register{\n" +
            "            font-size: 30px;\n" +
            "            position: absolute;\n" +
            "            top:30%;\n" +
            "            left:40%;\n" +
            "            width: auto;\n" +
            "        }\n" +
            "\n" +
            "    </style>\n" +
            "\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "\n" +
            "<img src=\"http://mylittleponyn6.blog.pl/files/2015/02/rainbow_dash_s_hot_minute_by_mrlolcats17-d5lo21h.png\">\n" +
            "</br>\n" +
            "\n" +
            "<div id=\"register\">\n" +
            "<form action=\"./login\" method=\"POST\">";
    private static final String HTML_END = "name:\n" +
            "    <input type=\"text\" name=\"login\"required pattern=\".{5,}\" title=\"Must contain at least 5 characters\">\n" +
            "    </br>\n" +
            "    password:\n" +
            "    <input type=\"password\" name=\"password\" pattern=\"(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}\"\n" +
            "           title=\"Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters\">\n" +
            "    </br>\n" +
            "    <input type=\"submit\" value=\"submit\">\n" +
            "</form>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getRequestURI().contains("loginForm")) {
            resp.sendError(403);
            return;
        }
        ServletOutputStream out = resp.getOutputStream();
        String param1 = req.getParameter("login");
        String param2 = req.getParameter("login2");
        out.println(HTML_START);
        if (param1 != null || param2 != null) {


            if (param1.length() > 4) {
                out.println("<p>User " + param1 + " succesfully registered</p>\n" +
                        "    <p>Please login</p>");
            } else if (param1.equals("invP")) {
                UserContext uc0 = new UserContext();
                User user0 = uc0.findUser(param2);
                if (user0.getInvPassCount() > 2) {
                    resp.sendRedirect("https://www.youtube.com/watch?v=LT3kI3BsxlQ");
                } else {
                    out.println("<p>Invalid password</p>\n" +
                            "    <p>Please try again</p>");
                    int temp = user0.getInvPassCount();
                    temp++;
                    user0.setInvPassCount(temp);
                    uc0.updateUser(user0);
                }
            } else if (param1.equals("invL")) {
                out.println("<p>Invalid login</p>\n" +
                        "    <p>Please try again</p>");
            }
        }
        out.println(HTML_END);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().contains("loginForm")) {
            resp.sendError(403);
            return;
        }

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserContext uc0 = new UserContext();
        User user0 = uc0.findUser(login);
        if (user0 != null) {
            if (user0.getPassword().equals(password)) {
                resp.sendRedirect("./home?username=" + user0.getLogin());
                user0.setInvPassCount(0);
                uc0.updateUser(user0);
            } else {
                resp.sendRedirect("./loginForm?login=invP&login2=" + user0.getLogin());
            }
        } else {
            resp.sendRedirect("./loginForm?login=invL");
        }
    }

}
