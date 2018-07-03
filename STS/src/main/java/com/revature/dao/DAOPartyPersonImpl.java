package com.revature.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.PartyPerson;

@Repository
@Transactional
public class DAOPartyPersonImpl implements DAOPartyPerson {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public int insertPerson(PartyPerson person) {
		//returns the PK if the username was unique and insertion was successful, and -1 if not
		if(uniqueUsername(person.getUsername())) {
			Session session = sessionFactory.getCurrentSession();
			return (int) session.save(person);
		}else {
			return -1;
		}
	}

	@Override
	public void updatePerson(PartyPerson person) {
		if(getPersonById(person.getPersonId()).getUsername().equals(person.getUsername()) || uniqueUsername(person.getUsername()) ){
			//if either the new username is unique, or the new username is the same as this user's old username
			Session session = sessionFactory.getCurrentSession();
			session.update(person);	
		}
	}

	@Override
	public void deletePerson(PartyPerson person) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(person);
	}

	@Override
	public PartyPerson getPersonById(int personId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PartyPerson.class);
		criteria.add(Restrictions.eq("personId", personId));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("personId"),"personId")
				.add(Projections.property("username"),"username")
				.add(Projections.property("email"),"email")
				.add(Projections.property("age"),"age")
				.add(Projections.property("address"),"address")
				).setResultTransformer(Transformers.aliasToBean(PartyPerson.class));
		List<PartyPerson> personList = criteria.list();
		if(personList.size() > 0) {
			//since personId is primary key, there can only be 0 or 1 items in this list
			return personList.get(0);
		}else {
			return null;
		}
	}
	
	

	@Override
	public PartyPerson login(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PartyPerson.class);
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("password", password));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("personId"),"personId")
				.add(Projections.property("username"),"username")
				.add(Projections.property("email"),"email")
				.add(Projections.property("age"),"age")
				.add(Projections.property("address"),"address")
				).setResultTransformer(Transformers.aliasToBean(PartyPerson.class));
		List<PartyPerson> personList = criteria.list();
		if(personList.size() > 0) {
			//since personId is primary key, there can only be 0 or 1 items in this list
			return personList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public boolean uniqueUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PartyPerson.class);
		criteria.add(Restrictions.eq("username", username));
		if(criteria.list().size() > 0) {
			//There is a username, already exists
			return false;
		}else {
			//list empty, nobody has that username
			return true;
		}
	}
}
