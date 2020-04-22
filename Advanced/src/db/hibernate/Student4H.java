package db.hibernate;

import db.jdbc.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.SQLException;

public class Student4H {
    SessionFactory factory;

    public Student4H() {
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
        factory = metadata.buildSessionFactory();
    }

    // 插入student对象到表中
    public void insert(Student student) throws SQLException {

    }

    // 删除指定id的记录
    public void delete(int id) throws SQLException {

    }

    // 修改表数据，类似insert
    public void update(Student student) throws SQLException {

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
