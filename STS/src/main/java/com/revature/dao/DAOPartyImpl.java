package com.revature.dao;

import java.math.BigDecimal;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.revature.models.Party;
import com.revature.models.PartyPerson;
import com.revature.models.Tag;

@Repository
@Transactional
public class DAOPartyImpl implements DAOParty {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public int insertParty(Party party) {
		Session session = sessionFactory.getCurrentSession();
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
		//get the information from the party
		Criteria criteria = session.createCriteria(Party.class);
		criteria.createAlias("creator", "pp");
		criteria.add(Restrictions.eq("partyId", partyId));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("partyId"),"partyId")
				.add(Projections.property("partyName"),"partyName")
				.add(Projections.property("address"),"address")
				.add(Projections.property("partyDate"),"partyDate")
				.add(Projections.property("description"),"description")
				.add(Projections.property("pictureUrl"),"pictureUrl")
				.add(Projections.property("pp.personId"),"personId")
				).setResultTransformer(new AliasToBeanResultTransformer(Party.class));
		List<Party> partyList = criteria.list();
		if(partyList.size() > 0) {
			//since partyId is primary key, there can only be 0 or 1 items in this list
			Party party = partyList.get(0);
			party.setAttendees(getAttendeesById(partyId));
			party.setTagList(getTagsByPartyId(partyId));
			if(party.getCreator().getPersonId() != null) {
				party.setCreator(getCreatorByPersonId(party.getCreator().getPersonId()));
			}
			return party;
		}else {
			return null;
		}
	}
	
	@Override
	public Set<Tag> getTagsByPartyId(int partyId){
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT TAGID, TAGNAME, PARTYID FROM TAG WHERE PARTYID = ?";
		Query query = session.createSQLQuery(sql);
		query.setInteger(0, partyId);
		List<Object[]> tagLists = query.list();
		Set<Tag> tags = new HashSet<>();
		for(Object[] obj: tagLists) {
			Tag tag = new Tag();
			tag.setTagId( Integer.parseInt(obj[0].toString()));
			tag.setTagName(Integer.parseInt(obj[1].toString()));
			tags.add(tag);
		}
		return tags;
	}
	
	@Override
	public Set<Party> getPartiesAttending(int personId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Party.class);
		criteria.createAlias("attendees", "people");
		criteria.add(Restrictions.eq("people.personId", personId));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("partyId"),"partyId")
				.add(Projections.property("partyName"),"partyName")
				.add(Projections.property("address"),"address")
				.add(Projections.property("partyDate"),"partyDate")
				).setResultTransformer(Transformers.aliasToBean(Party.class));
		return new HashSet<Party>(criteria.list());
	}
	
	@Override
	public Set<Party> getPartiesCreated(int personId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Party.class);
		criteria.createAlias("creator", "person");
		criteria.add(Restrictions.eq("person.personId", personId));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("partyId"),"partyId")
				.add(Projections.property("partyName"),"partyName")
				.add(Projections.property("partyDate"),"partyDate")
				).setResultTransformer(Transformers.aliasToBean(Party.class));
		return new HashSet<Party>(criteria.list());
	}
	
	
	@Override
	public Set<PartyPerson> getAttendeesById(int partyId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PartyPerson.class);
		criteria.createAlias("eventsRSVP", "events");
		criteria.add(Restrictions.eq("events.partyId", partyId));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("personId"),"personId")
				.add(Projections.property("username"),"username")
				).setResultTransformer(Transformers.aliasToBean(PartyPerson.class));
		return new HashSet<PartyPerson>(criteria.list());
	}


	@Override
	public Set<Party> getPartyWithinRadius(double radius, double latitude, double longitude) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT P.PARTYID FROM PARTY P\n" + 
				"JOIN ADDRESS A\n" + 
				"ON P.ADDRESSID = A.ADDRESSID\n" + 
				"JOIN COORDINATES C\n" + 
				"ON A.COORDINATEID = C.COORDINATEID\n" + 
				"WHERE 3963*ACOS((sin(C.LATITUDE/ 57.3) * SIN(?/ 57.3))  + \n" + 
				"(COS(C.LATITUDE / 57.3) * COS(?/ 57.3) *COS(C.Longitude/ 57.3 - ?/57.3 ))) < ?";
		Query query = session.createSQLQuery(sql);
		query.setDouble(0, latitude);
		query.setDouble(1, latitude);
		query.setDouble(2,longitude);
		query.setDouble(3, radius);
		Set<BigDecimal> partyIdList = new HashSet<BigDecimal>(query.list());
		Set<Party> parties = new HashSet<>();
		for(BigDecimal id: partyIdList) {
			parties.add(getPartyByIdSmall(Integer.parseInt(id.toString())));
		}
		return parties;
	}
	
	public Set<Party> getPartyListWithinCoordinates(double minLat, double minLong, double maxLat, double maxLong){
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
				.add(Projections.property("address"),"address")
				.add(Projections.property("pictureUrl"),"pictureUrl")
				.add(Projections.property("partyDate"),"partyDate")		
				).setResultTransformer(Transformers.aliasToBean(Party.class));
		Set<Party> parties = new HashSet<Party>(criteria.list());
		
		for(Party party:parties) {
			party.setTagList(getTagsByPartyId(party.getPartyId()));
		}
		return parties;
		
	}

	@Override
	public PartyPerson getCreatorByPersonId(int personId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PartyPerson.class);
		criteria.add(Restrictions.eq("personId", personId));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("personId"),"personId")
				.add(Projections.property("username"),"username")
				.add(Projections.property("email"),"email")
				).setResultTransformer(Transformers.aliasToBean(PartyPerson.class));
		List<PartyPerson> personList = criteria.list();
		
		if(personList.size() > 0) {
			//since partyId is primary key, there can only be 0 or 1 items in this list
			return personList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Party getPartyByIdSmall(int partyId) {
		Session session = sessionFactory.getCurrentSession();
		//get the information from the party
		Criteria criteria = session.createCriteria(Party.class);
		criteria.add(Restrictions.eq("partyId", partyId));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("partyId"),"partyId")
				.add(Projections.property("partyName"),"partyName")
				.add(Projections.property("address"),"address")
				.add(Projections.property("pictureUrl"),"pictureUrl")
				.add(Projections.property("partyDate"),"partyDate")
				).setResultTransformer(new AliasToBeanResultTransformer(Party.class));
		List<Party> partyList = criteria.list();
		if(partyList.size() > 0) {
			//since partyId is primary key, there can only be 0 or 1 items in this list
			Party party = partyList.get(0);
			party.setTagList(getTagsByPartyId(partyId));
			return party;
		}else {
			return null;
		}
	}
}
