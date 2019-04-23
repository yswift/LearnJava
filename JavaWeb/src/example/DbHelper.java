package example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
    public static final String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=LearnJava;user=LearnJava;password=LearnJavaPwd";
    //	public static final String connectionUrl = "jdbc:sqlserver://10.2.7.39:1433;databaseName=LearnJava;user=LearnJava;password=LearnJavaPwd";
//    public static final String mySqlConUrl = "jdbc:mysql://localhost:3306/LearnJava?serverTimezone=UTC";
    public static final String mySqlConUrl = "jdbc:mysql://10.10.50.54:3306/LearnJava";

    Connection connection;
    Connection mySqlCon;

    public void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(connectionUrl);
            return;
        }
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            connect();
        }
        return connection;
    }

    public Connection getMysqlConnection() throws SQLException {
        if (mySqlCon == null || mySqlCon.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            mySqlCon = DriverManager.getConnection(mySqlConUrl, "learnjavauser", "learnjavapwd");
        }
        return mySqlCon;
    }

    public static void copy(InputStream is, OutputStream os) throws IOException {
        int rlen;
        byte[] buffer = new byte[1024];
        while ((rlen = is.read(buffer)) != -1) {
            os.write(buffer, 0, rlen);
        }
    }
}
