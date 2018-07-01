package com.revature.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.revature.dao.DAOPartyImpl;
import com.revature.models.Coordinates;
import com.revature.models.Party;

public class partyTest {

static DAOPartyImpl daoPartyImpl = new DAOPartyImpl();
	
	@Test
	public void saveDeleteReadNewParty() {
//		Party party = new Party();
//		party.setPartyName("Sportsball");
//		int pk = daoPartyImpl.insertParty(party);
//		String partyName = daoPartyImpl.getPartyById(pk).getPartyName();
//		daoPartyImpl.deleteParty(party);
//		assertEquals("Sportsball", partyName);
//		assertEquals(null, daoPartyImpl.getPartyById(pk));
	}
	
	//test party in Ado Ekiti, Nigeria
	//commented because this relies on a specific entry being there, 
	//but a new event with a long=7.3, lat=5.1 is meant for this JUnit test
//	@Test
//	public void coordinatesWithinRadiusParty() {
//		Coordinates coordinates = new Coordinates();
//		coordinates.setLongitude(7.2);
//		coordinates.setLattitude(5.1);
//		List<Party> partyList = daoPartyImpl.getPartyWithinRadius(coordinates, 50);
//		assertTrue(partyList.size() > 0);
//	}
//	public void coordinatesOutsideRadiusParty() {
//		Coordinates coordinates = new Coordinates();
//		coordinates.setLongitude(7.2);
//		coordinates.setLattitude(5.1);
//		List<Party> partyList = daoPartyImpl.getPartyWithinRadius(coordinates, 10);
//		assertTrue(partyList.size() == 0);
//	}

}
