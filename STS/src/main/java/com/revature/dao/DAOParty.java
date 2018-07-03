package com.revature.dao;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.revature.models.Coordinates;
import com.revature.models.Party;
import com.revature.models.PartyPerson;
import com.revature.models.Tag;

@Repository
public interface DAOParty {

	public int insertParty(Party party);
	public void updateParty(Party party);
	public void deleteParty(Party party);
	public Party getPartyById(int partyId);
	public Set<Party> getPartyWithinRadius(Coordinates coordinates,double radius);
	public Set<Party> getPartyListWithinCoordinates(double minLat, double minLong, double maxLat, double maxLong);
	public Set<Party> getPartiesAttending(int personId);
	public Set<Party> getPartiesCreated(int personId);
	public Set<PartyPerson> getAttendeesById(int partyId);
	public Set<Tag> getTagsByPartyId(int partyId);
}
