package com.kitri.state;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "cookieservlet", value = "/cookie")
public class CookieServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        //쿠키 : 상태 정보를 저장하는 기술 (서버로 전송할 때 자동으로 포함)
        //세션과의 차이점 : 클라이언트 측에 저장 (세션은 서버측에 저장)
        //서버에 접속할 때 자동으로 요청에 포함되어 전달

        Cookie cookie1 = new Cookie("id","guest");
        cookie1.setMaxAge(60 * 60 * 24); //초단위 : 60 = 1분, (24시간 유지가능한)
        resp.addCookie(cookie1);

        //새로운 쿠키 생성
        //name = key
        Cookie cookie2 = new Cookie("code", "007");
        //시간을 지정을 하지 않으면 세션을 진행하는 동안만 유지됨
        cookie2.setPath("/cookie-read"); // 지정된 url에 접속할 때만 쿠키 전송 (쿠키 경로 지정)
        resp.addCookie(cookie2);

        out.println("쿠키 전송 완료"); //안내 메시지


    }
}
