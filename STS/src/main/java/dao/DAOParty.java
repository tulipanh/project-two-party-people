package dao;

import java.util.List;
import java.util.Set;

import com.revature.models.Coordinates;
import com.revature.models.Party;

interface DAOParty {

	public int insertParty(Party party);
	public void updateParty(Party party);
	public void deleteParty(Party party);
	public Party getPartyById(int partyId);
	public List<Party> getPartyWithinRadius(Coordinates coordinates,double radius);
}
