package dao;

import com.revature.models.PartyPerson;

interface DAOPartyPerson {

	public int insertPerson(PartyPerson person);
	public void updatePerson(PartyPerson person);
	public void deletePerson(PartyPerson person);
	public PartyPerson getPersonById(int personId);
}
