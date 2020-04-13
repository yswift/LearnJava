package db.jdbc;

import db.h2.H2DbHelper;

import java.sql.*;

public class StudentDAO {
	H2DbHelper dbHelper = new H2DbHelper();

	public Student findByNo(String no) {
		// TODO 不要这样拼接sql
		String sql = "select * from Student where no='" + no + "'";
		try (Statement stmt = dbHelper.getConnection().createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
			if (!rs.next()) {
				return null;
			}
			return createStudent(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// 从rs 中读取数据，建立 Student 对象
	Student createStudent(ResultSet rs) throws SQLException {
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


	public Student findByNo2(String no) {
		String sql = "select * from Student where no=?";
		try (PreparedStatement pstmt = dbHelper.getConnection().prepareStatement(sql)) {
			// 设置参数
			pstmt.setString(1, no);
			// 执行查询
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			return createStudent(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
