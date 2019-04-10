package javaweb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowStatus", urlPatterns = "/showStatus")
public class ShowStatus extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cs = request.getParameter("status");
        int code = 200;

        if (cs != null && cs.matches("[12345]\\d{2}")) {
            code = Integer.parseInt(cs);
        }
        response.sendError(code, "自定义错误");
    }

    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
