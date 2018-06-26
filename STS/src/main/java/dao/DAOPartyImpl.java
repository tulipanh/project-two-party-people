package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.models.Coordinates;
import com.revature.models.Party;
import com.revature.util.HibernateUtil;

public class DAOPartyImpl implements DAOParty {

	@Override
	public int insertParty(Party party) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		int pk = (int) session.save(party);
		tx.commit();
		session.close();
		return pk;
	}

	@Override
	public void updateParty(Party party) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.update(party);
		tx.commit();
		session.close();
	}

	@Override
	public void deleteParty(Party party) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.delete(party);
		tx.commit();
		session.close();
	}

	@Override
	public Party getPartyById(int partyId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Party.class);
		criteria.add(Restrictions.eq("partyId", partyId));
		List<Party> partyList = criteria.list();
		if(partyList.size() > 0) {
			//since partyId is primary key, there can only be 0 or 1 items in this list
			session.close();
			return partyList.get(0);
		}else {
			session.close();
			return null;
		}
	}

	@Override
	public List<Party> getPartyWithinRadius(Coordinates coordinates, double radius) {
		Session session = HibernateUtil.getSession();
		List<Party> partyList = new ArrayList<>();
		String sql = "SELECT P.PARTYNAME,A.CITY,C.LONGITUDE,C.LATITUDE  FROM ADDRESS A\n" + 
				"JOIN PARTY P\n" + 
				"ON P.ADDRESSID = A.ADDRESSID\n" + 
				"JOIN COORDINATES C\n" + 
				"ON A.COORDINATEID = C.COORDINATEID\n" + 
				"WHERE 3963*ACOS((sin(C.LATTITUDE/ 57.3) * SIN(?/ 57.3))  + \n" + 
				"(COS(C.LATTITUDE / 57.3) * COS(?/ 57.3) *COS(C.Longitude/ 57.3 - ?/57.3 ))) < ?";
		Query query = session.createSQLQuery(sql);
		query.setDouble(0, coordinates.getLattitude());
		query.setDouble(1, coordinates.getLattitude());
		query.setDouble(2, coordinates.getLongitude());
		query.setDouble(3, radius);
		partyList = query.list();
		session.close();
		return partyList;
	}

}
