package com.revature.models;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Test;

import com.revature.util.HibernateUtil;

public class CoordinatesTest {

	@Test
	public void hibernateTest() {
		Session session = HibernateUtil.getSession();
		session.close();
	}

}
