package db.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class TestCollege {
	SessionFactory factory;

	public void buildSessionFactory() {
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();
		Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		factory = metadata.buildSessionFactory();
	}

	public void insert() {
		College[] cs = { new College("01", "计科"), new College("02", "通信"), new College("03", "电器"), };
		Session session = factory.openSession();
		session.beginTransaction();
		for (College c : cs) {
			session.save(c);
		}
		session.getTransaction().commit();
		session.close();
	}

	public void list() {
		try (Session session = factory.openSession()) {
			List<College> list = session.createQuery("from College").list();
			for (College c : list) {
				System.out.println(c);
			}
		}
	}

	public void update(College c) {
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			session.update(c);
			session.getTransaction().commit();
		}
	}
	
	public void update() {
try (Session session = factory.openSession()) {
	College c = session.find(College.class, "01");
	c.setName("计算机科学与技术");
	session.beginTransaction();
	session.flush();
	session.getTransaction().commit();
}
	}

	public void delete(String id) {
		College c = new College(id, null);
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			session.delete(c);
			session.getTransaction().commit();
		}
	}

	public void deleteAll() {
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			String hql = "DELETE FROM College";
			Query<College> query = session.createQuery(hql);
			query.executeUpdate();
			session.getTransaction().commit();
		}
	}

	public static void main(String[] args) {
		TestCollege tc = new TestCollege();
		tc.buildSessionFactory();

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
		
		System.out.println("状态示例：修改计科的名称");
		tc.update();
		tc.list();
		
		tc.factory.close();
	}
}
