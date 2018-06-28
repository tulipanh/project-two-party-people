package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.revature.models.Tag;
import com.revature.util.HibernateUtil;

@Repository
public class DAOTagImpl implements DAOTag {

	@Override
	public int insertTag(Tag tag) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		int pk = (int) session.save(tag);
		tx.commit();
		session.close();
		return pk;
	}

	@Override
	public void updateTag(Tag tag) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.update(tag);
		tx.commit();
		session.close();
	}

	@Override
	public void deleteTag(Tag tag) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.delete(tag);
		tx.commit();
		session.close();
	}

	@Override
	public Tag getTagById(int tagId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Tag.class);
		criteria.add(Restrictions.eq("tagId", tagId));
		List<Tag> tagList = criteria.list();
		if(tagList.size() > 0) {
			//since tagId is primary key, there can only be 0 or 1 items in this list
			session.close();
			return tagList.get(0);
		}else {
			session.close();
			return null;
		}
	}
}
