package com.kitri.state;

import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "stateStoreServlet" , value = "/state.store")
public class StateStoreServlet extends HttpServlet {
    int memberCount = 0; //필드 (멤버 함수)
    //ServletContext :
    ServletContext servletContext = null;
    //ServletContext가 실행될 때 초기화

    HashMap<HttpSession, Integer> users = new HashMap<>();

    //init()은 서블릿이 최초 생성될 때 1번 실행
    @Override
    public void init(ServletConfig config) throws ServletException {
        //ServeltContext를 반환해줌
        //(1)ServletContext는 init 안에 config를 통해 가져옴
        //(2)ServletContext는 servic안에서 this를 통해서도 가져올 수 있다. Httpservlet을 상속받았기 때문에
        servletContext = config.getServletContext();
        servletContext.setAttribute("count", new Integer(0));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int localCount = 0; //지역 변수 (메소드가 실행되면 초기화)
        resp.setContentType("text/plain;charset = UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("멤버 변수 카운트 : " + ++memberCount);
        out.println("로컬 변수 카운트 : " + ++localCount);

        //ServletContext
        //was가 생성되고 종료될 때 까지 유지가 되기 때문에 초기화가 되지 않음
        int applicationCount = (int) servletContext.getAttribute("count");
        servletContext.setAttribute("count", ++applicationCount);
        out.println("애플리케이션 단위 카운트 : " + applicationCount);

        //this.getServletContext(); ServletContext를 가져오는 (2)방법

        //Session : HttpServletRequest를 통해 가져옴
        HttpSession session = req.getSession(); // 세션 Id 추출
        //세션은 클라이언트가 시작되고 종료될 때 까지만 세션이 유지됨 (사이트를 종료하면 세션도 초기화)

        if(session.isNew()){
            out.println("세션 생성 완료 " + session.getId());
        }

        Integer sessionCount = (Integer) session.getAttribute("count");
        if(sessionCount == null){
            sessionCount = 0;
            session.setAttribute("count", sessionCount);

        }
        session.setAttribute("count", ++sessionCount);
        out.println("세션 단위 카운트 : " + sessionCount);

        //Session ID 확인하기
        out.println("--- 해당 페이지에 접속한 유저별 방문 횟수 ---");

        users.forEach((key,value) -> {
            out.println(key + " : " + value);
        });

    }
}
