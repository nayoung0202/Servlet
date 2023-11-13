package basic;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CalcSevlet", value = "/calc")
public class CalcServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        int val1 = Integer.parseInt(req.getParameter("val1"));
        int val2 = Integer.parseInt(req.getParameter("val2"));

        switch (req.getParameter("op")){
            case "plus":
                out.print(val1 + " + " +  val2 + " 결과 => ");
                out.println(val1 + val2);
                break;
            case "minus":
                out.print(val1 + " - " +  val2 + " 결과 => ");
                out.println(val1 - val2);
                break;
            case "mul":
                out.print(val1 + " * " +  val2 + " 결과 => ");
                out.println(val1 * val2);
                break;
            case "div":
                out.print(val1 + " / " +  val2 + " 결과 => ");
                out.println(val1 / val2);
                break;
            case "mod":
                out.print(val1 + " % " +  val2 + " 결과 => ");
                out.println(val1 % val2);
                break;
            default:
                out.println("계산 오류");
                break;
        }
    }
}