package db.h2;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

// 数据库表初始化工具
public class H2Initer {
    static final String sqlfn = System.getProperty("user.dir") + "\\Advanced\\src\\db\\h2\\Init.sql";

    public static void main(String[] args) throws Exception {
        init();
        System.out.println("初始化成功。");
    }

    @Test
    public void test() throws SQLException {
        H2DbHelper helper = new H2DbHelper();
        try (Connection conn = helper.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("insert into College values('00','Test')");
            pstmt.executeUpdate();
            String sql = "select * from College";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("Id") + ", " + rs.getString("Name"));
            }
        }
    }

    static void init() throws SQLException, IOException {
        H2DbHelper helper = new H2DbHelper();
        try (Connection conn = helper.getConnection()) {
            String sql = readSql();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        }
    }

    static String readSql() throws IOException {
        File f = new File(sqlfn);
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f))) {
            byte[] bytes = bis.readAllBytes();
            String sql = new String(bytes, "UTF-8");
            return sql;
        }
    }
}
