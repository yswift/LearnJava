package example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	DbHelper dbHelper = new DbHelper();

	public List<Student> listAll() throws SQLException {
		List<Student> list = new ArrayList<>();
		String sql = "select * from Student";
		try (Statement pstmt = dbHelper.getMysqlConnection().createStatement() ) {
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				Student s = createStudent(rs);
				list.add(s);
			}
			return list;
		}
	}

	private Student createStudent(ResultSet rs) throws SQLException {
		Student s = new Student();
		s.setId(rs.getInt("Id"));
		s.setNo(rs.getString("No"));
		s.setName(rs.getString("Name"));
		s.setAge(rs.getInt("Age"));
		s.setBirthday(rs.getDate("Birthday"));
		if (rs.wasNull())
			s.setBirthday(new Date(System.currentTimeMillis()));
		s.setPhoto(rs.getBlob("Photo"));
		return s;
	}

	public Student findByNo(String no) throws SQLException {
		String sql = "select * from Student where no=?";
		try (PreparedStatement pstmt = dbHelper.getMysqlConnection().prepareStatement(sql) ) {
			pstmt.setString(1, no);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			return createStudent(rs);
		}
	}

	public Student findById(String id) throws SQLException {
		int iid = Integer.parseInt(id);
		return findById(iid);
	}

	public Student findById(int id) throws SQLException {
		String sql = "select * from Student where id=?";
		try (PreparedStatement pstmt = dbHelper.getMysqlConnection().prepareStatement(sql) ) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			return createStudent(rs);
		}
	}

	public void save(Student s) throws SQLException {
		String sql = "insert into Student(No, Name, Age, Birthday, photo) values(?,?,?,?,?)";
		try (PreparedStatement pstmt = dbHelper.getMysqlConnection().prepareStatement(sql) ) {
			pstmt.setString(1, s.getNo());
			pstmt.setString(2, s.getName());
			pstmt.setInt(3, s.getAge());
			pstmt.setDate(4, s.getBirthday());
			pstmt.setBlob(5, s.getPhoto());
			pstmt.execute();
		}
	}
}
