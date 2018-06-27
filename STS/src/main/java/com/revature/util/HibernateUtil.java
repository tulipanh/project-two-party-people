package com.revature.util;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static SessionFactory getSessionFactory(String filename) {
		if(HibernateUtil.sessionFactory == null){
			Configuration configuration = new Configuration().configure(filename);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			HibernateUtil.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		return HibernateUtil.sessionFactory;
	}
	
	public static Session getSession() {
		return getSessionFactory("hibernate.cfg.xml").openSession();
	}
}
