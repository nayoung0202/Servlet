package webserver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import static webserver.JoinServlet.member;
import static webserver.JoinServlet.sc;

@WebServlet(name = "loginservlet", value = "/login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(); //세션 ID 추출
//        HashMap<String,Member> member = new HashMap<>();

        req.setCharacterEncoding("UTF-8"); //한글 패치/ 파일의 인코딩 속성을 "UTF-8"로 변경
        resp.setContentType("text/html;charset = UTF-8"); //resp 세팅
        PrintWriter out = resp.getWriter();


        String id = req.getParameter("id"); //HTML에 있는 id값 가져오기
        String pw = req.getParameter("pw"); //HTML에 있는 pw값 가져오기
        Member member1 = (Member) sc.getAttribute(id); //Hashmap에서 가져온 member를 가져오기 위해 (Member)로 형변환후 가져옴

        //세션값
        if (session.getAttribute("id") == null) {
            if (member1 != null) { // 가져온 member값(member1) 안에 값이 있는지 비교하기(id가 있는지 먼저 비교)
                if (pw.equals(member1.getPw())) { //pw 비교
                    out.println("로그인 성공 했습니다");
                    out.println("<hr><a href=practice/login.html>로그인 화면으로 돌아가기</a></hr>");
                    session.setAttribute("id", id); // id값에 세션 저장
                } else {
                    out.println("비밀번호가 틀렸습니다.");
                    out.println("<hr><a href=practice/login.html>로그인 화면으로 돌아가기</a></hr>");
                }
            } else {
                out.println("해당 ID는 존재하지 않습니다.");
                out.println("<hr><a href=practice/join.html>회원가입 화면으로 돌아가기</a></hr>");
            }
        } else {
            out.println("현재 로그인 상태입니다.");
        }
    }

    //앵커는 무조건 get으로 받기 때문에 메소드 설정을 안해줘도 됨
    //HTML의 form 안에 넣어도, 안 넣어도 get으로 받음
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset = UTF-8");
        HttpSession session = req.getSession(false);
        PrintWriter out = resp.getWriter();

        if (session != null && session.getAttribute("id") != null) {
            session.invalidate(); // 세션의 모든 데이터 값을 삭제
            out.print("로그아웃 되었습니다.");
        } else {
            out.print("현재 로그인 상태가 아닙니다.");
            out.println("<hr><a href=practice/login.html>로그인 화면으로 돌아가기</a></hr>");
        }
        out.close(); // "PrintWriter" 스트림을 사용하였기 때문에 스트림을　닫아줌
    }
}


