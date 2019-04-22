package db.jdbc;

import java.sql.*;

public class MysqlTest {
    public static void main(String[] args) throws SQLException {
        DbHelper dbHelper = new DbHelper();
        Connection conn = dbHelper.getMysqlConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Student");
        //执行查询
        while (rs.next()) {
            //遍历查询结果
            System.out.println(rs.getString(1));
        }
        stmt.close();//显示关闭Statement对象，释放资源
        conn.close();
    }
}
