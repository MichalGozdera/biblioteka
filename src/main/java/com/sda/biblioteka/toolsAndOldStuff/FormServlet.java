package com.sda.biblioteka.toolsAndOldStuff;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name= "formServlet", urlPatterns="/form")
public class FormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream out=response.getOutputStream();
     out.println("<!DOCTYPE html>\n" +
             "<html>\n" +
             "<head>\n" +
             "<meta charset=\"utf-8\"/>\n" +
             "<title>Formularz</title>\n" +
             "</head>\n" +
             "<body>\n" +
             "\n" +
             "<form action=\"./addBook\" method=\"POST\">\n" +
             "  Tytul:<br>\n" +
             "  <input type=\"text\" name=\"title\">\n" +
             "  <br>\n" +
             " Autor:<br>\n" +
             "  <input type=\"text\" name=\"author\" >\n" +
             "  <br><br>\n" +
             "  <input type=\"submit\" value=\"Submit\">\n" +
             "</form> \n" +
             "\n" +
             "</body>\n" +
             "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String title=request.getParameter("title");
       String author=request.getParameter("author");

    }
}
