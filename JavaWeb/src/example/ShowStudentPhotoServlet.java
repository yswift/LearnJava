package example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

@WebServlet(name = "ShowStudentPhotoServlet", urlPatterns = "/student/photo")
public class ShowStudentPhotoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        StudentDAO dao = new StudentDAO();
        try {
            Student s= dao.findById(id);
            if (s == null || s.getPhoto() == null) {
                response.sendError(404, "cannot found student by id:" + id);
            }
            response.setContentType("image/jpg");
            OutputStream os = response.getOutputStream();
            Blob photo = s.getPhoto();
            InputStream is = photo.getBinaryStream();
            DbHelper.copy(is, os);
            os.close();
        } catch (SQLException e) {
            throw new IOException("cannot found student by id: " + id, e);
        }

    }
}
