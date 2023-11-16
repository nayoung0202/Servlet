package webserver;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

public class Member {
    String id;
    String pw;

    public Member(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
