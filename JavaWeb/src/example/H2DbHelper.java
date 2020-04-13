package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 使用Advanced module中的 db.h2.H2Initer 来初始化数据库
 * 或者，直接执行 Advanced module中的 db.h2.H2Initer 下的 Init.sql
 */
public class H2DbHelper {
    public static final String H2_DRIVER = "org.h2.Driver";
    public static final String JDBC_URL = "jdbc:h2:~/h2/h2demo;AUTO_SERVER=TRUE";

    Connection connection;

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName(H2_DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(JDBC_URL, "sa", "");
        }
        return connection;
    }
}
