package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Scrollable;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.revature.models.Coordinates;
import com.revature.models.Party;
import com.revature.util.HibernateUtil;

import javassist.convert.Transformer;

@Repository()
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
	public Party getPartyLocationById(int partyId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Party.class);
		criteria.add(Restrictions.eq("partyId", partyId));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("partyId"),"partyId")
				.add(Projections.property("partyName"),"partyName")
				.add(Projections.property("address"),"address")
				.add(Projections.property("partyDate"),"partyDate")
				//	.add(Projections.property("tagList"),"tagList")
				).setResultTransformer(Transformers.aliasToBean(Party.class));
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
				"WHERE 3963*ACOS((sin(C.LATITUDE/ 57.3) * SIN(?/ 57.3))  + \n" + 
				"(COS(C.LATITUDE / 57.3) * COS(?/ 57.3) *COS(C.Longitude/ 57.3 - ?/57.3 ))) < ?";
		Query query = session.createSQLQuery(sql);
		query.setDouble(0, coordinates.getLatitude());
		query.setDouble(1, coordinates.getLatitude());
		query.setDouble(2, coordinates.getLongitude());
		query.setDouble(3, radius);
		partyList = query.list();
		session.close();
		return partyList;
	}
	
	public List<Party> getPartyListWithinCoordinates(Coordinates coordinatesMin,Coordinates coordinatesMax){
		Session session = HibernateUtil.getSession();
		List<Party> partyList = new ArrayList<>();
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
		partyList = query.list();
		session.close();
		return partyList;
	}

}
