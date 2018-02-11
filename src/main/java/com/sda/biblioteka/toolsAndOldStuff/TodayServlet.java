package com.sda.biblioteka.toolsAndOldStuff;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name= "TodayAnnotationSevlet", urlPatterns= "/today_annotation")
public class TodayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream out=response.getOutputStream();
        out.println("<!doctype html>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t\t<meta charset=\"UTF-8\">\n" +
                "\t\t<title>Dzisiaj</title>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<p style=\"text-align:center\"><strong>"+LocalDateTime.now()+"</strong><p>\n" +
                "\t</body>\n" +
                "</html>");
    }
}
