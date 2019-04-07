package db.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import db.model.Department;
import db.model.Role;
import db.model.User;

public class TestUser {
	EntityManagerFactory factory;

	void buildManageFactory() {
		// 1.获得Factory
		factory = Persistence.createEntityManagerFactory("demo");
	}

	void close() {
		factory.close();
	}

	void step() {
// 1.获得Factory
		factory = Persistence.createEntityManagerFactory("demo");
// 2.获取Manager
		EntityManager manager = factory.createEntityManager();
// 3.获得事务，
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
// 4.执行sql
		manager.persist(new Department("01", "工学院"));
// 5.提交事务，关闭资源
		transaction.commit();
		manager.close();
	}

	void init() {
		System.out.println("初始化");
		// 2.获取Manager
		EntityManager manager = factory.createEntityManager();
		// 3.获得事务，
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		// 4.执行sql
		manager.persist(new Department("01", "工学院"));
		manager.persist(new Department("02", "理学院"));

		Role ra = new Role("Admin", "系统管理员");
		manager.persist(ra);
		Role rt = new Role("Teacher", "教师");
		manager.persist(rt);

		User u = new User("zs", "张三", "01");
		ra.getUsers().add(u);
		rt.getUsers().add(u);
//		u.getRoles().add(ra);
//		u.getRoles().add(rt);
		manager.persist(u);

		User u2 = new User("ls", "李四", "01");
		rt.getUsers().add(u2);
//		u2.getRoles().add(rt);
		manager.persist(u2);

		User u3 = new User("wu", "王五", "02");
		rt.getUsers().add(u3);
//		u3.getRoles().add(rt);
		manager.persist(u3);

		// 5.提交事务，关闭资源
		transaction.commit();
		manager.close();
	}

	void list() {
		// 2.获取Manager
		EntityManager manager = factory.createEntityManager();

		queryAndPrint(manager, "Department");
		queryAndPrint(manager, "Role");
		queryAndPrint(manager, "User");

		// 关闭资源
		manager.close();
	}

	void queryAndPrint(EntityManager manager, String entityName) {
		System.out.println(entityName);
		System.out.println();
		List list = manager.createQuery("select e from " + entityName + " e").getResultList();
		for (Object o : list) {
			System.out.println(o);
			System.out.println();
		}
	}

	void findDp() {
		System.out.println("显示工学院用户");
		// 2.获取Manager
		EntityManager manager = factory.createEntityManager();
		// 4.执行sql
		Department d = manager.find(Department.class, "01");
		System.out.println("d=" + d);
		List<User> us = d.getUsers();
		System.out.println("us=" + us);
		// 5.提交事务，关闭资源
		manager.close();
	}

	void findRole() {
		System.out.println("显示Admin角色");
		// 2.获取Manager
		EntityManager manager = factory.createEntityManager();
		// 4.执行sql
		Role r = manager.find(Role.class, "Admin");
		System.out.println("d=" + r);
		List<User> us = r.getUsers();
		System.out.println("us=" + us);
		// 5.提交事务，关闭资源
		manager.close();
	}

	void modifyWu() {
		System.out.println("修改王五信息");
		// 把王五的部门改为工学院，角色改为系统管理员
		// 2.获取Manager
		EntityManager manager = factory.createEntityManager();
		// 3.获得事务，
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		// 4.执行sql
		User u = manager.find(User.class, "wu");
		u.setDepartmentId("01");

		Role ra = manager.find(Role.class, "Admin");
		u.getRoles().clear();
		u.getRoles().add(ra);

		manager.persist(u);
		// 5.提交事务，关闭资源
		transaction.commit();
		manager.close();
	}

	void delete() {
		System.out.println("删除张三");
		// 2.获取Manager
		EntityManager manager = factory.createEntityManager();
		// 3.获得事务，
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		// 4.执行sql
		User u = manager.find(User.class, "zs");
		manager.remove(u);

		// 5.提交事务，关闭资源
		transaction.commit();
		manager.close();
	}

	void deleteDep(String id) {
		System.out.println("删除工学院");
		// 2.获取Manager
		EntityManager manager = factory.createEntityManager();
		// 3.获得事务，
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		// 4.执行sql
		Department u = manager.find(Department.class, id);
		manager.remove(u);

		// 5.提交事务，关闭资源
		transaction.commit();
		manager.close();
	}

	void deleteAll() {
		// 2.获取Manager
		EntityManager manager = factory.createEntityManager();
		// 3.获得事务，
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		// 删除部门
		Query dd = manager.createQuery("delete from Department");
		dd.executeUpdate();
		// 删除角色
		Query dr = manager.createQuery("delete from Role");
		dr.executeUpdate();
		// 提交事务，关闭资源
		transaction.commit();
		manager.close();
	}

	void queryUser() {
		EntityManager em = factory.createEntityManager();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> from = cq.from(User.class);
		CriteriaQuery<User> select = cq.select(from);
		TypedQuery<User> q = em.createQuery(cq);
		List<User> allitems = q.getResultList();

		System.out.println(allitems);
		System.out.println();
	}

	public static void main(String[] args) {
		
		TestUser t = new TestUser();
		t.buildManageFactory();
		t.deleteAll();

		// 初始化
		t.init();
		t.queryUser();
//		t.list();
//		t.findDp();
//		t.findRole();
//
//		// 修改王五
//		t.modifyWu();
//		t.list();
//		t.findDp();
//		t.findRole();

		// 删除张三
//		t.delete();
//		t.deleteDep("01");
//		t.list();
//		t.findDp();
//		t.findRole();

		t.close();
	}
}
