package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.models.Coordinates;
import com.revature.util.HibernateUtil;

public class DAOCoordinatesImpl implements DAOCoordinates{

	@Override
	public int insertCoordinates(Coordinates coordinates) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		int pk = (int) session.save(coordinates);
		tx.commit();
		session.close();
		return pk;
	}

	@Override
	public void updateCoordinates(Coordinates coordinates) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.update(coordinates);
		tx.commit();
		session.close();
	}

	@Override
	public void deleteCoordinates(Coordinates coordinates) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.delete(coordinates);
		tx.commit();
		session.close();
	}

	@Override
	public Coordinates getCoordinatesById(int coordId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Coordinates.class);
		criteria.add(Restrictions.eq("coordinateId", coordId));
		List<Coordinates> coordList = criteria.list();
		if(coordList.size() > 0) {
			//since coordId is primary key, there can only be 0 or 1 items in this list
			session.close();
			return coordList.get(0);
		}else {
			session.close();
			return null;
		}
	}
}
