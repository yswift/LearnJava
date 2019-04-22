package example;

import java.sql.*;

public class StudentDAO {
	DbHelper dbHelper = new DbHelper();

	public Student findByNo(String no) {
		String sql = "select * from Student where no='?'";
		try (PreparedStatement pstmt = dbHelper.getConnection().prepareStatement(sql) ) {
			pstmt.setString(1, no);
			ResultSet rs = pstmt.executeQuery(sql);
			if (!rs.next()) {
				return null;
			}
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void save(Student s) {
		String sql = "insert into Student(No, Name, Age, Birthday, photo) values(?,?,?,?,?)";
		try (PreparedStatement pstmt = dbHelper.getConnection().prepareStatement(sql) ) {
			pstmt.setString(1, s.getNo());
			pstmt.setString(2, s.getName());
			pstmt.setInt(3, s.getAge());
			pstmt.setDate(4, s.getBirthday());
			pstmt.setBlob(5, s.getPhoto());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
