package com.sda.biblioteka.toolsAndOldStuff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookAdded", urlPatterns = "/bookAdded")
public class BookAddedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookNumber = req.getParameter("bookNumber");
        String[] info = req.getParameterValues("info");
        resp.getOutputStream().println("Ksiazka zapisana pod numerem " + bookNumber);
        resp.getOutputStream().println("Ksiazka pod tytulem " + info[0]);
        resp.getOutputStream().println("Jej autor " + info[1]);
        resp.getOutputStream().println("Data dodania " + info[2]);
        resp.getOutputStream().println("User Agent " + info[3]);
    }
}
