package db.springdata;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import db.model.College;

@Component
public class TestSpringDataCollege {
    @Autowired
    CollegeRepository repository;

    void list() {
        List<College> cs = repository.findAll();
        for (College c : cs) {
            System.out.println(c);
        }
    }

    void insert() {
        College[] cs = {new College("01", "计科"), new College("02", "通信"), new College("03", "电器"),};
        for (College c : cs) {
            repository.save(c);
        }
    }

    public void update(College c) {
        repository.save(c);
    }

    public void update2() {
        // 修改名称：计算机科学与技术
        System.out.println("计算机科学与技术");
        Optional<College> oc = repository.findById("01");
        College c = oc.get();
        c.setName("计算机科学与技术");
        repository.save(c);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    void deleteAll() {
        repository.deleteAll();
    }

    void findById(String id) {
        System.out.println("find by id: " + id);
        Optional<College> c = repository.findById(id);
        System.out.println(c.get());
    }

    void findByName(String name) {
        System.out.println("find by name: " + name);
        College c = repository.findByName(name);
        System.out.println(c);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig4Mysql.class);
        TestSpringDataCollege t = ctx.getBean(TestSpringDataCollege.class);

        t.deleteAll();

        System.out.println("插入3个部门");
        t.insert();
        t.list();

        System.out.println("\n修改电器名称");
        College c = new College("03", "电气");
        t.update(c);
        t.list();

        System.out.println("\n删除机械");
        t.delete("02");
        t.list();

        System.out.println("查找计科");
        t.findByName("计科");

        System.out.println("查找机械");
        t.findByName("机械");

        t.update2();
        t.list();
    }

}
