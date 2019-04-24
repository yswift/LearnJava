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
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "StudentServlet", urlPatterns = {"/student/create", "/student/edit", "/student/photo", "/student/delete"})
// 允许上传文件，最大1M，
@MultipartConfig(
        fileSizeThreshold = 1024*1024, // 小于1m的文件保存在内存中
        maxRequestSize = 1024*1024
)
public class StudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
        String path = request.getServletPath();
        switch (path) {
            case "/student/create" :
                create(request, response);
                break;
            case "/student/edit" :
                edit(request, response);
                break;
            default:
                request.setAttribute("msg", "不支持的url: " + path);
                request.getRequestDispatcher("/student/list.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/student/delete" :
                delete(request, response);
                break;
            case "/student/photo" :
                getPhoto(request, response);
                break;
            default:
                request.setAttribute("msg", "不支持的url: " + path);
                request.getRequestDispatcher("/student/list.jsp").forward(request, response);
        }
    }

    // 新建学生
    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Student s = new Student();
            updateStudent(s, request);
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

    // 使用客户端提交的信息更新student对象
    private void updateStudent(Student s, HttpServletRequest request) throws ServletException, SQLException, IOException {
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
    }

    // 处理照片上传
    private void uploadPhoto(Student s, HttpServletRequest request) throws IOException, ServletException, SQLException {
        Part filePart = request.getPart("Photo");
        if (filePart == null || filePart.getSize() <= 0) {
            return;
        }
        InputStream is = filePart.getInputStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        copy(is, os);
        Blob photo = new SerialBlob(os.toByteArray());
        s.setPhoto(photo);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String id = request.getParameter("Id");
            StudentDAO dao = new StudentDAO();
            Student s = dao.findById(id);
            updateStudent(s, request);
            dao.update(s);
            // 设置用于显示给用户的成功消息
            String msg = "已成功修改学生信息：" + s.getName();
            // 传递给 jsp
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("list.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new IOException("save failed", e);
        }
    }

    // 删除学生
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            StudentDAO dao = new StudentDAO();
            dao.delete(id);
            // 传递给 jsp
            request.setAttribute("msg", "删除学生成功");
            request.getRequestDispatcher("list.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new IOException("cannot delete student by id: " + id, e);
        }
    }

    // 输出学生照片
    private void getPhoto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        StudentDAO dao = new StudentDAO();
        try {
            Student s= dao.findById(id);
            if (s == null || s.getPhoto() == null) {
                response.sendError(404, "cannot found student by id:" + id);
            }
            // 设置结果MIME
            response.setContentType("image/jpg");
            // 照片直接写入输出流
            OutputStream os = response.getOutputStream();
            Blob photo = s.getPhoto();
            InputStream is = photo.getBinaryStream();
            copy(is, os);
            os.close();
        } catch (SQLException e) {
            throw new IOException("cannot found student by id: " + id, e);
        }
    }

    // 把数据从输入流is，复制到输出流os中
    public static void copy(InputStream is, OutputStream os) throws IOException {
        int rlen;
        byte[] buffer = new byte[1024];
        while ((rlen = is.read(buffer)) != -1) {
            os.write(buffer, 0, rlen);
        }
    }
}
