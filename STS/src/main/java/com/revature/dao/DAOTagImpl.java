package com.revature.dao;

import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Tag;

@Repository
@Transactional
public class DAOTagImpl implements DAOTag {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public int insertTag(Tag tag) {
		Session session = sessionFactory.getCurrentSession();
		return (int) session.save(tag);
	}

	@Override
	public void updateTag(Tag tag) {
		Session session = sessionFactory.getCurrentSession();	
		session.update(tag);
	}

	@Override
	public void deleteTag(Tag tag) {
		Session session = sessionFactory.getCurrentSession();
		
		session.delete(tag);
	}

	@Override
	public Tag getTagById(int tagId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Tag.class);
		criteria.add(Restrictions.eq("tagId", tagId));
		List<Tag> tagList = criteria.list();
		if(tagList.size() > 0) {
			//since tagId is primary key, there can only be 0 or 1 items in this list		
			return tagList.get(0);
		}else {
			return null;
		}
	}
}
