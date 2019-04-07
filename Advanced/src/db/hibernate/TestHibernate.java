package db.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class TestHibernate {
	SessionFactory factory;

	public void buildSessionFactory() {
		// 读取配置文件方式1，hibernate4.3之前
//		Configuration cfg = new Configuration().configure();
//		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
//				.build();
		// 创建会话工厂对象SessionFactory
//		SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);

		// 读取配置文件方式2，hibernate4.3之后
		// 创建服务注册对象
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();
		Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		factory = metadata.buildSessionFactory();
	}

	public void close() {
		factory.close();
	}
}
