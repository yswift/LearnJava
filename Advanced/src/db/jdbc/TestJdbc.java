package db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJdbc {
	static String[] CollegeIds = { "01", "02", "03" };
	static String[] CollegeNames = { "计科", "通信", "电器" };

	Connection connection;

	void insertCollege() throws SQLException {
		String sql = "insert into College(id,name) values(?,?)";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			for (int i = 0; i < CollegeIds.length; i++) {
				pstmt.setString(1, CollegeIds[i]);
				pstmt.setString(2, CollegeNames[i]);
				pstmt.execute();
			}
		}
		System.out.println("插入完成");
	}

	void listCollege() throws SQLException {
		String sql = "select * from College";
		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString("name");
				System.out.println("部门：" + id + ", " + name);
			}
		}
	}

	void updateCollege(String id, String name) throws SQLException {
		String sql = "update College set name=? where id=?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.execute();
		}
		System.out.println("修改完成");
	}

	void deleteCollege(String id) throws SQLException {
		String sql = "delete from College where id = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.execute();
		}
		System.out.println("删除完成");
	}

	void deleteAll() throws SQLException {
		String sql = "delete from College";
		try (Statement stmt = connection.createStatement()) {
			stmt.execute(sql);
		}
		System.out.println("全部删除");
	}

	public static void main(String[] args) throws SQLException {
		TestJdbc test = new TestJdbc();
		DbHelper helper = new DbHelper();
		try (Connection connection = helper.getMysqlConnection()) {
			test.connection = connection;
			test.deleteAll();
			
			System.out.println("插入3个部门");
			test.insertCollege();
			test.listCollege();

			System.out.println("\n修改电器名称");
			test.updateCollege("03", "电气");
			test.listCollege();

			System.out.println("\n删除机械");
			test.deleteCollege("02");
			test.listCollege();
		}
	}

}
