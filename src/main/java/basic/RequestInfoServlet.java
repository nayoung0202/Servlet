package basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "requestinfo", value = "/request-info-servlet")
public class RequestInfoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<h3>클라이언트로부터 받은 요청 정보</h3>");
        out.printf("<p>method : %s</p>\n", req.getMethod());
//        out.printf("<p>user : %s</p>\n", req.getRemoteUser());
        out.printf("<p>address : %s</p>\n", req.getRemoteAddr());
        out.printf("<p>prot : %s</p>\n", req.getRemotePort());

        out.printf("<p>Contextpath : %s</p>\n", req.getContextPath());
        out.printf("<p>Protocol : %s</p>\n", req.getProtocol());
        out.printf("<p>ServletPath : %s</p>\n", req.getServletPath());

        out.printf("<p>헤더 정보</p>");
        Enumeration<String> names = req.getAttributeNames();
        while (names.hasMoreElements()){
            String s = names.nextElement();
            out.println(s + " : " + req.getHeader(s) + "<br>");
        }


    }
}
