package example;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "CreateStudentServlet", urlPatterns = "/student/add")
// 允许上传文件，最大1M，
@MultipartConfig(
        fileSizeThreshold = 1024*1024, // 小于1m的文件保存在内存中
        maxRequestSize = 1024*1024
)
public class CreateStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Student s = createStudent(request);
            StudentDAO dao = new StudentDAO();
            dao.save(s);
            // 设置用于显示给用户的成功消息
            String msg = "已成功添加学生：" + s.getName();
            // 传递给 jsp
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("list.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new IOException("save failed", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/student/create.jsp");
    }

    private Student createStudent(HttpServletRequest request) throws ServletException, SQLException, IOException {
        Student s = new Student();
        s.setNo(request.getParameter("No"));
        s.setName(request.getParameter("Name"));
        String a = request.getParameter("Age");
        if (a != null && a.length() > 0) {
            s.setAge(Integer.parseInt(a));
        }
        String b = request.getParameter("Birthday");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        if (b != null && b.length() > 0) {
            try {
                s.setBirthday(new java.sql.Date(sf.parse(b).getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        uploadPhoto(s, request);
        return s;
    }

    private void uploadPhoto(Student s, HttpServletRequest request) throws IOException, ServletException, SQLException {
        Part filePart = request.getPart("Photo");
        if (filePart == null) {
            return;
        }
        InputStream is = filePart.getInputStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        DbHelper.copy(is, os);
        Blob photo = new SerialBlob(os.toByteArray());
        s.setPhoto(photo);
    }
}
