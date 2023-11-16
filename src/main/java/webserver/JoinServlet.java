package webserver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

//어노테이션을 이용해 서블릿 매핑
@WebServlet(name = "joinservlet", value = "/join")
public class JoinServlet extends HttpServlet {

    //member로 HashMap 생성
    //hashmap(파우치)이 ServletContext(가방)보다 더 작은 개념
    //hashmap을 ServletContext에 넣고 하나씩 값을 빼서 사용
    static HashMap<String, Member> member = new HashMap<>();

    //ServletContext sc로 생성후 member에 저장한 값을 ServletContext에 저장하기
    static ServletContext sc = null;

    //init() 메소드를 통해 선언, HttpServlet은 ServletConfig를 구현하고 있으므로 getServletContext()메소드를 바로 이용할 수 있다.
    @Override
    public void init(ServletConfig config) throws ServletException {
        sc = config.getServletContext();
    }

    //doPOST를 통해 URL에 Mapping 정보만 표시 / GET 방식과 달리 URL에 데이터가 노출되지 않음 (높은 보안성)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset = UTF-8"); // 인코딩 속성을 UTf-8로 변경하여 한글 패치
        PrintWriter out = resp.getWriter();

        String id = req.getParameter("id"); //HTML에 있는 id값 가져오기
        String pw = req.getParameter("pw"); //HTML에 있는 pw값 가져오기
        String pwcheck = req.getParameter("pwcheck"); //HTMl에 있는 pwcheck값 가져오기


        if(sc.getAttribute(id) == null){ //ServletContext에 id값을 가져오기
            if (pw.equals(pwcheck)) { //pw와 pwcheck가 같은지 비교
                sc.setAttribute(id, new Member(id, pw)); //비밀번호가 같으면

                out.println("회원가입을 완료하였습니다.");
                out.println("<hr><a href=practice/login.html>로그인 화면으로 돌아가기</a></hr>");
            } else {
                out.println("비밀번호가 다릅니다.");
                out.println("<hr><a href=practice/login.html>로그인 화면으로 돌아가기</a></hr>");
            }
        } else{
            out.println("이미 존재하는 회원입니다.");
            out.println("<hr><a href=practice/login.html>로그인 화면으로 돌아가기</a></hr>");
        }
    }
}
