package com.revature.dao;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.revature.models.Coordinates;
import com.revature.models.Party;
import com.revature.models.PartyPerson;

@Repository
public interface DAOParty {

	public int insertParty(Party party);
	public void updateParty(Party party);
	public void deleteParty(Party party);
	public Party getPartyById(int partyId);
	public List<Party> getPartyWithinRadius(Coordinates coordinates,double radius);
	public List<Party> getPartyListWithinCoordinates(double minLat, double minLong, double maxLat, double maxLong);
	public List<Party> getPartiesAttending(int personId);
	public List<Party> getPartiesCreated(int personId);
	public List<PartyPerson> getAttendeesById(int partyId);
}
