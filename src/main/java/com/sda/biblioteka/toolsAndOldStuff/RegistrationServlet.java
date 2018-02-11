package com.sda.biblioteka.toolsAndOldStuff;

import com.sda.biblioteka.servlets.spring.db.model.User;
import com.sda.biblioteka.servlets.spring.db.UserContext;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

//@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registrationForm", "/register"})
public class RegistrationServlet extends HttpServlet {
private static final String HTML_START="<html>\n" +
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
        "<form action=\"./register\" method=\"POST\">";
private static final String HTML_END=" name:\n" +
        "    <input type=\"text\" name=\"name\" required>\n" +
        "    </br>\n" +
        "    last name:\n" +
        "    <input type=\"text\" name=\"lastName\"required>\n" +
        "    </br>\n" +
        "    email\n" +
        "    <input type=\"email\" name=\"email\" required>\n" +
        "    </br>\n" +
        "    login\n" +
        "    <input type=\"text\" name=\"login\"required pattern=\".{5,}\" title=\"Must contain at least 5 characters\">\n" +
        "    </br>\n" +
        "    password\n" +
        "    <input type=\"password\" name=\"password\" pattern=\"(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}\"\n" +
        "           title=\"Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters\">\n" +
        "    </br>\n" +
        "    date of birth\n" +
        "    <input type=\"date\" name=\"date\" required max=\"2000-02-01\">\n" +
        "    </br>\n" +
        "    <input type=\"submit\" value=\"submit\">\n" +
        "</form>\n" +
        "</div>\n" +
        "</body>\n" +
        "</html>";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getRequestURI().contains("registrationForm")) {
            resp.sendError(403);
            return;
        }
        ServletOutputStream out = resp.getOutputStream();
        String param1 = req.getParameter("login");
        out.println(HTML_START);
        if(param1!=null){
            out.println("<p>User "+ param1+" already exists</p>\n" +
                    "    <p>Try different login</p>");
        }
        out.println(HTML_END);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().contains("registrationForm")) {
            resp.sendError(403);
            return;
        }
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String date = req.getParameter("date");
        String[] dateDetails = date.split("-");
        LocalDate date0 = LocalDate.of(Integer.parseInt(dateDetails[0]),Integer.parseInt(dateDetails[1]), Integer.parseInt(dateDetails[2]));
        User user0 = new User(name, lastName, email, login, password, date);
        UserContext uc1=new UserContext();
        if(!uc1.addUser(user0)){
            resp.sendRedirect("./registrationForm?login="+user0.getLogin());
        }
        else{
            resp.sendRedirect("./loginForm?login="+user0.getLogin());
        }
    }
}
