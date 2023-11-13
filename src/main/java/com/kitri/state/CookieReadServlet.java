package com.kitri.state;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "cookiereadservlet", value = "/cookie-read")
public class CookieReadServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //쿠키 추출 (요청에 쿠키가 담아져서 옴)

        //클라이언트에 쿠키가 저장되어 있기 때문에 resp을 통해 쿠키를 받아옴
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        Cookie[] cookies = req.getCookies();
        for(Cookie cookie : cookies){
            out.println(cookie.getName() + " : " + cookie.getValue());
        }

    }
}
