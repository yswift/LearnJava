package db.jdbc;

import java.sql.*;

public class StudentExercise {
    Connection connection;

    // 构造函数，传入数据库连接
    public StudentExercise(Connection connection) {
        this.connection = connection;
    }

    // 用给定的no学号，name姓名，age年龄，birthday出生日期，插入到表中
    public void insert(String no, String name, int age, Date birthday) throws SQLException {
    }

    // 删除指定id的记录
    public void delete(int id) throws SQLException {
    }

    // 修改表数据，按id修改数据，类似insert
    public void update(int id, String no, String name, int age, Date birthday) throws SQLException {
    }

    // 按 id 查询学生信息，把查到的数据保存到Student对象中，
    // 参考：StudentDAO 的 createStudent 方法
    public Student findById(int id) throws SQLException {
        return new Student();
    }

    public void insertPhoto() {
        // 插入数据时同时插入图片，这个不好编写测试，各位同学完成后，可以使用
        // StudentView 验证
    }
}
