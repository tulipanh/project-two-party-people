package com.revature.dao;


import org.springframework.stereotype.Repository;

import com.revature.models.PartyPerson;

@Repository
public interface DAOPartyPerson {

	public int insertPerson(PartyPerson person);
	public int updatePerson(PartyPerson person);
	public void deletePerson(PartyPerson person);
	public PartyPerson getPersonById(int personId);
	public PartyPerson login(String username, String password);
	public boolean uniqueUsername(String username);
	public boolean uniqueEmail(String email);
	public PartyPerson getUpdateFieldsById(int personId);
}
