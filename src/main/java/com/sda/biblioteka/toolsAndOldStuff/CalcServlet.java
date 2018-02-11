package com.sda.biblioteka.toolsAndOldStuff;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name= "CalcAnnotationSevlet", urlPatterns= "/calc")
public class CalcServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream out=response.getOutputStream();
        String param1=request.getParameter("operator");
        String[] param2=request.getParameterValues("liczby");
        List<Integer> numbers = new ArrayList<>();
        for (String param:param2){
            numbers.add(Integer.parseInt(param));
        }
        switch (param1){
            case "plus":{
                int result=0;
                for (Integer i:numbers) {
                    result+=i;
                }
                out.println("Suma tych liczb wynosi "+result);
            }
            break;
            case "minus":{
                int result=numbers.get(0);
                for (int i=1;i<numbers.size();i++){
                    result-=numbers.get(i);
                }
                out.println("Roznica tych liczb wynosi "+result);
            }
            break;
            case "razy":{
              int result=1;
                for (Integer i:numbers) {
                    result*=i;
                }
                out.println("Iloczyn tych liczb wynosi "+result);
            }
            break;
            case "przez":{
               double result1=(double)numbers.get(0);
                for (int i=1;i<numbers.size();i++){
                    if (numbers.get(i)==0){
                      response.sendRedirect("https://www.youtube.com/watch?v=EtaPo7PRVqg");
                        break;
                    }
                    else{
                        result1/=numbers.get(i);
                    }

                }
                out.println("Iloraz tych liczb wynosi "+result1);
                break;
            }
            default:{
                response.sendRedirect("https://www.youtube.com/watch?v=EtaPo7PRVqg");
            }
        }
    }
}
