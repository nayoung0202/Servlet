package com.kitri.state;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestServlet",value = "/request")
public class RequestServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //HttpServletRequest 객체 이용 : 요청 단위로 생성
        //요청 : 객체 생성
        //응답 : 객체 소멸
        String site = req.getParameter("site");
        if (site.equals("naver")){
            //네이버로 이동(리다이렉트)
            resp.sendRedirect("http://www.naver.com");
        }else if(site.equals("google")){
            resp.sendRedirect("http://www.google.com");
        } else if (site.equals("daum")) {
            resp.sendRedirect("http://www.daum.net");
        }

    }
}
