package db.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import db.model.College;

public class TestJpaCollege {
	EntityManagerFactory factory;

	void step() {
// 得到 EntityManagerFactory
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("demo");
// 获取 EntityManager
		EntityManager manager = factory.createEntityManager();
// 获得事务
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
// 执行CRUD
// manager.remove();
// 提交事务，关闭资源
		transaction.commit();
		manager.close();
	}

	public void buildEntityManagerFactory() {
		factory = Persistence.createEntityManagerFactory("demo");
	}

	public void insert() {
		College[] cs = { new College("01", "计科"), new College("02", "通信"), new College("03", "电器"), };
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		for (College c : cs) {
			manager.persist(c);
		}
		transaction.commit();
		manager.close();
	}

	public void list() {
		EntityManager manager = factory.createEntityManager();
		List<College> list = manager.createQuery("select e from College e").getResultList();
		for (College c : list) {
			System.out.println(c);
		}
		manager.close();
	}

	public void update(College c) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(c);
		transaction.commit();
		manager.close();
	}

	public void delete(String id) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		College c = manager.find(College.class, id);
		manager.remove(c);
		transaction.commit();
		manager.close();
	}

	public void deleteAll() {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Query query = manager.createQuery("delete from College");
		int result = query.executeUpdate(); // 影响的记录数
		transaction.commit();
		manager.close();
	}

	public static void main(String[] args) {
		TestJpaCollege tc = new TestJpaCollege();
		tc.buildEntityManagerFactory();

		tc.deleteAll();

		System.out.println("插入3个部门");
		tc.insert();
		tc.list();

		System.out.println("\n修改电器名称");
		College c = new College("03", "电气");
		tc.update(c);
		tc.list();

		System.out.println("\n删除机械");
		tc.delete("02");
		tc.list();

		tc.factory.close();
	}
}
