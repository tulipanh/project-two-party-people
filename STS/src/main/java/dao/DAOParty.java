package dao;

import com.revature.models.Party;

interface DAOParty {

	public int insertParty(Party party);
	public void updateParty(Party party);
	public void deleteParty(Party party);
	public Party getPartyById(int partyId);
}
