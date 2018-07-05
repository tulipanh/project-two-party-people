package com.revature.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Coordinates;

@Repository
@Transactional
public class DAOCoordinatesImpl implements DAOCoordinates{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public int insertCoordinates(Coordinates coordinates) {
		Session session = sessionFactory.getCurrentSession();
		return (int) session.save(coordinates);
	}

	@Override
	public void updateCoordinates(Coordinates coordinates) {
		Session session = sessionFactory.getCurrentSession();
		session.update(coordinates);
			
	}

	@Override
	public void deleteCoordinates(Coordinates coordinates) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(coordinates);
	}

	@Override
	public Coordinates getCoordinatesById(int coordId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Coordinates.class);
		criteria.add(Restrictions.eq("coordinateId", coordId));
		List<Coordinates> coordList = criteria.list();
		if(coordList.size() > 0) {
			//since coordId is primary key, there can only be 0 or 1 items in this list
			return coordList.get(0);
		}else {
			return null;
		}
	}
}
