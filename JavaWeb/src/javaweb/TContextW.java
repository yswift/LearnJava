package javaweb;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TContextW", urlPatterns = "/contextW")
public class TContextW extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        // 获取ServletContext对象的引用
        // 第一种方法
        ServletContext servletContext = this.getServletContext();
        // 第二种方法
        // ServletContext servletContext2 = this.getServletConfig().getServletContext();
        servletContext.setAttribute("name", "小明");
        out.println("将 name=小明  写入了ServletContext");
    }
}
