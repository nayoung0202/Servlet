package com.kitri.state;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "userVisitServlet", value = "/user-visit")
public class UserVisitServlet extends HttpServlet {
    //세션의 유저를 담기
    HashMap<HttpSession,User> users = new HashMap<>();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {req.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset = UTF-8");
       PrintWriter out = resp.getWriter();


        HttpSession session = req.getSession();
        //최초 방문일 때
        if (!users.containsKey(session)) {
            //user가 필요한 것 : name, city, count
            User user = new User();
            user.setName(req.getParameter("name"));
            user.setCity(req.getParameter("city"));
            user.setCount(0);
            users.put(session, user);
        }

        // 재방문일 때 회원 정보를 찾기
        //session으로 유저를 가져오고 방문횟수 +1
        User user = users.get(session);
        user.setCount(user.getCount() + 1);

        users.forEach((key, value) -> {
            out.println(value);
        });
    }
}
