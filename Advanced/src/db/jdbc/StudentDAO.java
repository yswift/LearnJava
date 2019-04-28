package db.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.serial.SerialBlob;

public class StudentDAO {
	DbHelper dbHelper = new DbHelper();

	public Student findByNo(String no) {
		String sql = "select * from Student where no='" + no + "'";
		try (Statement stmt = dbHelper.getMysqlConnection().createStatement(); ResultSet rs = stmt.executeQuery(sql);) {

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
}
