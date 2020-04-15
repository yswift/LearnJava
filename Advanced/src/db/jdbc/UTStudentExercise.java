package db.jdbc;

import db.h2.H2DbHelper;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import static org.junit.Assert.*;

public class UTStudentExercise {
    static Long oneDay = 24 * 60 * 60 * 1000L;

    Connection connection;
    StudentExercise studentExercise;

    public UTStudentExercise() throws SQLException {
        H2DbHelper helper = new H2DbHelper();
        connection = helper.getConnection();
        studentExercise = new StudentExercise(connection);
    }

    @Before
    public void clean() throws SQLException {
        // 清除数据
        String sql = "delete from Student";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.execute();
        }
    }

    @Test
    public void testInsert() throws SQLException {
        Random random = new Random();
        String no = String.valueOf(random.nextInt(10000));
        String name = "name" + random.nextInt(10);
        int age = random.nextInt(100);
        // 生成生日，去掉小时，分等
        long b = System.currentTimeMillis();
        Date birthday = new Date(b - b % oneDay);
        // 插入数据
        studentExercise.insert(no, name, age, birthday);
        // 找到插入数据
        ResultSet rs = executeQuery(" no='" + no + "'");
        // 检查插入数据是否正确
        check(rs, no, name, age, birthday);
        rs.close();
    }

    @Test
    public void testDelte() throws SQLException {
        // 先插入2条数据
        execute("insert into Student(no) values('testdelete1')");
        execute("insert into Student(no) values('testdelete2')");
        // 找到插入数据的id
        ResultSet rs = executeQuery(" no='testdelete1'");
        rs.next();
        int id = rs.getInt(1);
        rs.close();
        // 删除
        studentExercise.delete(id);
        // 验证删除是否成功
        rs = executeQuery(" id=" + id);
        assertFalse("检查删除是否成功", rs.next());
        rs.close();
        // 验证没有多删除记录
        rs = executeQuery("no='testdelete2'");
        assertTrue("检查删除是否成功", rs.next());
        rs.close();
    }

    @Test
    public void testUpdate() throws SQLException {
        // 先插入2条数据
        execute("insert into Student(no) values('testdelete1')");
        // 找到插入数据的id
        ResultSet rs = executeQuery(" no='testdelete1'");
        rs.next();
        int id = rs.getInt(1);
        rs.close();
        // 生成插入值
        Random random = new Random();
        String no = String.valueOf(random.nextInt(10000));
        String name = "name" + random.nextInt(10);
        int age = random.nextInt(100);
        long b = System.currentTimeMillis();
        Date birthday = new Date(b - b % oneDay);
        // 修改数据
        studentExercise.update(id, no, name, age, birthday);
        // 找到插入数据
        rs = executeQuery(" id=" + id);
        // 验证
        check(rs, no, name, age, birthday);
        rs.close();
    }

    @Test
    public void testFind() throws SQLException, ParseException {
        // 先插入数据
        execute("insert into Student values(null, '201187654321', '憨豆', 67, '1955-01-06', null)");
        // 找到插入数据的id
        ResultSet rs = executeQuery(" no='201187654321'");
        rs.next();
        int id = rs.getInt(1);
        rs.close();
        Student s = studentExercise.findById(id);
        assertEquals("Id", id, s.getId());
        assertEquals("学号", "201187654321", s.getNo());
        assertEquals("姓名", "憨豆", s.getName());
        assertEquals("年龄", 67, s.getAge());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date(sdf.parse("1955-01-06").getTime());
        assertEquals("出生日期", d, s.getBirthday());
    }

    ResultSet executeQuery(String where) throws SQLException {
        String sql = "select id,no,name,age,birthday from Student where " + where;
        PreparedStatement pstmt = connection.prepareStatement(sql);
        return pstmt.executeQuery();
    }

    void execute(String sql) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.execute();
        }
    }

    // 验证数据是否正确
    void check(ResultSet rs, String no, String name, int age, Date birthday) throws SQLException {
        assertTrue("有数据", rs.next());
        assertEquals("学号", no, rs.getString("no"));
        assertEquals("姓名", name, rs.getString("name"));
        assertEquals("年龄", age, rs.getInt("age"));
        assertEquals("出生日期", birthday, rs.getDate("birthday"));
    }
}
