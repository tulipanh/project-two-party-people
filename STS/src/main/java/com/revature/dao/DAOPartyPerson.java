package com.revature.dao;


import org.springframework.stereotype.Repository;

import com.revature.models.PartyPerson;

@Repository
public interface DAOPartyPerson {

	public int insertPerson(PartyPerson person);
	public void updatePerson(PartyPerson person);
	public void deletePerson(PartyPerson person);
	public PartyPerson getPersonById(int personId);
	
}
