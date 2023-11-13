package basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MultiplicationServlet", value = "/multiplication-string-servlet")
public class MultiplicationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        int dan = Integer.parseInt(req.getParameter("dan"));
        out.println(dan + "단");

        for (int i = 1; i <= 9; i++) {
            out.println();
            out.println(dan + "*" + i + "=" + (dan * i));
        }
    }

}
