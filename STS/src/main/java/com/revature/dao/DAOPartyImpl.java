package com.revature.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Coordinates;
import com.revature.models.Party;

@Repository()
@Transactional
public class DAOPartyImpl implements DAOParty {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public int insertParty(Party party) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println(session);
		return (int) session.save(party);
	}

	@Override
	public void updateParty(Party party) {
		Session session = sessionFactory.getCurrentSession();
		session.update(party);
	}

	@Override
	public void deleteParty(Party party) {
		Session session = sessionFactory.getCurrentSession();		
		session.delete(party);
	}

	@Override
	public Party getPartyById(int partyId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Party.class);
		criteria.add(Restrictions.eq("partyId", partyId));
		List<Party> partyList = criteria.list();
		if(partyList.size() > 0) {
			//since partyId is primary key, there can only be 0 or 1 items in this list
			return partyList.get(0);
		}else {
			return null;
		}
	}
	
	@Override
	public Party getPartyLocationById(int partyId) {
		Session session = sessionFactory.getCurrentSession();
//		String sql = "SELECT P.PARTYID, P.PARTYNAME, P.PARTYDATE, A.STREETNAME, A.CITY,A.STATE,A.ZIPCODE, T.TAGNAME FROM PARTY P\n" + 
//				"FULL JOIN ADDRESS A\n" + 
//				"ON A.ADDRESSID = P.ADDRESSID\n" + 
//				"FULL JOIN TAG T \n" + 
//				"ON P.PARTYID = T.PARTYID\n" + 
//				"WHERE P.PARTYID = ?";
//		Query query = session.createSQLQuery(sql);
//		query.setParameter(0,partyId);
//		ScrollableResults scrollable = query.scroll();
//		if(scrollable.first() ) {
//			Party party = new Party();
//			System.out.println(scrollable.get().toString());
//			return null;
//		}else {
//			return null;
//		}
//		
		
		Criteria criteria = session.createCriteria(Party.class);
		System.out.println((new Timestamp(500)));
		criteria.add(Restrictions.eq("partyId", partyId));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("partyId"),"partyId")
				.add(Projections.property("partyName"),"partyName")
				.add(Projections.property("tagList"),"tagList")
				.add(Projections.property("address"),"address")
				.add(Projections.property("partyDate"),"partyDate")
				).setResultTransformer(Transformers.aliasToBean(Party.class));
		List<Party> partyList = criteria.list();
		if(partyList.size() > 0) {
			//since partyId is primary key, there can only be 0 or 1 items in this list
			return partyList.get(0);
		}else {		
			return null;
		}
	}

	@Override
	public List<Party> getPartyWithinRadius(Coordinates coordinates, double radius) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT P.PARTYNAME,A.CITY,C.LONGITUDE,C.LATITUDE  FROM ADDRESS A\n" + 
				"JOIN PARTY P\n" + 
				"ON P.ADDRESSID = A.ADDRESSID\n" + 
				"JOIN COORDINATES C\n" + 
				"ON A.COORDINATEID = C.COORDINATEID\n" + 
				"WHERE 3963*ACOS((sin(C.LATITUDE/ 57.3) * SIN(?/ 57.3))  + \n" + 
				"(COS(C.LATITUDE / 57.3) * COS(?/ 57.3) *COS(C.Longitude/ 57.3 - ?/57.3 ))) < ?";
		Query query = session.createSQLQuery(sql);
		query.setDouble(0, coordinates.getLatitude());
		query.setDouble(1, coordinates.getLatitude());
		query.setDouble(2, coordinates.getLongitude());
		query.setDouble(3, radius);
		return query.list();
	}
	
	public List<Party> getPartyListWithinCoordinates(Coordinates coordinatesMin,Coordinates coordinatesMax){
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT P.PARTYID, P.PARTYNAME, P.PARTYDATE, C.LATITUDE,C.LONGITUDE  FROM PARTY P\n" + 
				"JOIN ADDRESS A\n" + 
				"ON P.ADDRESSID = A.ADDRESSID\n" + 
				"JOIN COORDINATES C\n" + 
				"ON A.COORDINATEID = C.COORDINATEID\n" + 
				"WHERE C.LATITUDE BETWEEN ? AND ?\n" + 
				"AND C.LONGITUDE BETWEEN ? AND ?";
		Query query = session.createSQLQuery(sql);
		query.setDouble(0, coordinatesMin.getLatitude());
		query.setDouble(1, coordinatesMax.getLatitude());
		query.setDouble(2, coordinatesMin.getLongitude());
		query.setDouble(3, coordinatesMax.getLongitude());
		
//		Criteria criteria = session.createCriteria(Party.class);
//		criteria.add(Restrictions.between("address.coordinates", coordinatesMin, coordinatesMax));
//		criteria.setProjection(Projections.projectionList()
//				.add(Projections.property("partyId"),"partyId")
//				.add(Projections.property("partyName"),"partyName")
//				.add(Projections.property("address"),"address")
//				.add(Projections.property("partyDate"),"partyDate")
//					.add(Projections.property("tagList"),"tagList")
//				).setResultTransformer(Transformers.aliasToBean(Party.class));
//		partyList = criteria.list();
		return query.list();
	}

}
