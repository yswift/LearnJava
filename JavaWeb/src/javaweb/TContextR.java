package javaweb;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TContextR", urlPatterns = "/contextR")
public class TContextR extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        // 取出ServletContext的某个属性
        //1.首先获取到ServletContext
        ServletContext servletContext = this.getServletContext();
        //2.取出属性
        String name = (String)servletContext.getAttribute("name");
        out.println("name="+name);
    }
}
