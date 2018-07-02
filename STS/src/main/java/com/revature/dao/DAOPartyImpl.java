package com.revature.dao;

import java.sql.Timestamp;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Coordinates;
import com.revature.models.Party;
import com.revature.models.PartyPerson;

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
	public List<Party> getPartiesAttending(int personId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Party.class);
		criteria.createAlias("attendees", "people");
		criteria.add(Restrictions.eq("people.personId", personId));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("partyId"),"partyId")
				.add(Projections.property("partyName"),"partyName")
				.add(Projections.property("tagList"),"tagList")
				.add(Projections.property("address"),"address")
				.add(Projections.property("partyDate"),"partyDate")
				).setResultTransformer(Transformers.aliasToBean(Party.class));
		return criteria.list();
	}
	
	@Override
	public List<Party> getPartiesCreated(int personId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Party.class);
		criteria.createAlias("attendees", "people");
		criteria.add(Restrictions.eq("people.personId", personId));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("partyId"),"partyId")
				.add(Projections.property("partyName"),"partyName")
				.add(Projections.property("tagList"),"tagList")
				.add(Projections.property("address"),"address")
				.add(Projections.property("partyDate"),"partyDate")
				).setResultTransformer(Transformers.aliasToBean(Party.class));
		return criteria.list();
	}
	
	@Override
	public List<PartyPerson> getAttendeesById(int partyId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PartyPerson.class);
		criteria.createAlias("eventsRSVP", "events");
		criteria.add(Restrictions.eq("events.partyId", partyId));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("personId"),"personId")
				.add(Projections.property("username"),"username")
				).setResultTransformer(Transformers.aliasToBean(PartyPerson.class));
		return criteria.list();
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
	
	public List<Party> getPartyListWithinCoordinates(double minLat, double minLong, double maxLat, double maxLong){
		Session session = sessionFactory.getCurrentSession();
		session.setFlushMode(FlushMode.MANUAL);

		Criteria criteria = session.createCriteria(Party.class);
		criteria.createAlias("address", "addressAlias");
		criteria.createAlias("addressAlias.coordinates", "coordinatesAlias");
		criteria.add(Restrictions.between("coordinatesAlias.latitude", minLat, maxLat));
		criteria.add(Restrictions.between("coordinatesAlias.longitude", minLong, maxLong));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("partyId"),"partyId")
				.add(Projections.property("partyName"),"partyName")
				.add(Projections.property("tagList"),"tagList")
				.add(Projections.property("address"),"address")
				.add(Projections.property("partyDate"),"partyDate")		
				).setResultTransformer(Transformers.aliasToBean(Party.class));
		return criteria.list();
	}

}
