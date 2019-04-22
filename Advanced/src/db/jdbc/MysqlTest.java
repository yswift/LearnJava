package db.jdbc;

import java.sql.*;

public class MysqlTest {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://10.10.50.54:3306/LearnJava";
        Connection conn = DriverManager.getConnection(url, "learnjavauser", "learnjavapwd");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select ProductName from Products");
        //执行查询
        while (rs.next()) {
            //遍历查询结果
            System.out.println(rs.getString(1));
        }
        stmt.close();//显示关闭Statement对象，释放资源
        conn.close();
    }
}
