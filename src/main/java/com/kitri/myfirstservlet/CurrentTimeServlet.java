package com.kitri.myfirstservlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import static java.time.LocalTime.now;

//어노테이션
@WebServlet(name = "currenttime", value = "/time-servlet")
public class CurrentTimeServlet extends HttpServlet {

        private String time;

        public void init() {
            time = ""+now();
        }

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.setContentType("text/html");

            // Hello
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + time + "</h1>");
            out.println("</body></html>");
        }

        public void destroy() {
        }

}

