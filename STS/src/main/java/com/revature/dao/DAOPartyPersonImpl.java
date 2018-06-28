package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.revature.models.PartyPerson;
import com.revature.util.HibernateUtil;

@Repository
public class DAOPartyPersonImpl implements DAOPartyPerson {

	@Override
	public int insertPerson(PartyPerson person) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		int pk = (int) session.save(person);
		tx.commit();
		session.close();
		return pk;
	}

	@Override
	public void updatePerson(PartyPerson person) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.update(person);
		tx.commit();
		session.close();
	}

	@Override
	public void deletePerson(PartyPerson person) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.delete(person);
		tx.commit();
		session.close();
	}

	@Override
	public PartyPerson getPersonById(int personId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(PartyPerson.class);
		criteria.add(Restrictions.eq("personId", personId));
		List<PartyPerson> personList = criteria.list();
		if(personList.size() > 0) {
			//since personId is primary key, there can only be 0 or 1 items in this list
			session.close();
			return personList.get(0);
		}else {
			session.close();
			return null;
		}
	}

}
