package com.revature.dao;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.revature.models.Coordinates;
import com.revature.models.Party;

@Repository
public interface DAOParty {

	public int insertParty(Party party);
	public void updateParty(Party party);
	public void deleteParty(Party party);
	public Party getPartyById(int partyId);
	public List<Party> getPartyWithinRadius(Coordinates coordinates,double radius);
	public Party getPartyLocationById(int partyId);
	public List<Party> getPartyListWithinCoordinates(Coordinates coordinatesMin,Coordinates coordinatesMax);
}
