package com.kitri.state;

import basic.HelloServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/dispatcher1")
public class DispatcherServlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h3> Dispatcher1 수행결과 </h3>");

        //forword (전달)
        //서블릿이 분업화 되어 다른 서블릿이 필요할 때 사용
        ServletContext sc = this.getServletContext();
        RequestDispatcher rd  = sc.getRequestDispatcher("/dispatcher2");
        //forward할 땐 정보를 같이 넘겨줘야 됨

        req.setAttribute("name","키트리");
        //포워드가 된 서블릿을 꺼내서 쓸 수 있음
        rd.forward(req, resp);
        //rd.include(req, resp); //정보를 포함시켜 정보를 보냈다가 돌아옴


    }
}
