package com.revature.controllers;

import java.util.List;
import java.util.Set;

import javax.mail.Part;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dao.DAOParty;
import com.revature.dao.DAOPartyPerson;
import com.revature.models.Party;
import com.revature.models.PartyPerson;

@RestController
public class PartyPersonController {
	
	@ModelAttribute
	public void setVaryResponseHeader(HttpServletResponse response) {
	    response.setHeader("Access-Control-Allow-Origin", "*");
	}  

	@Autowired
	DAOPartyPerson daoPartyPerson;
	
	@Autowired
	DAOParty DAOParty;
	
	@GetMapping("login")
	public PartyPerson tryLogin(@ModelAttribute("username") String username,
			@ModelAttribute("password") String password) {
		return daoPartyPerson.login(username, password);
	}
	
	@PutMapping("user")
	public void updateUser(@RequestBody PartyPerson person) {
		daoPartyPerson.updatePerson(person);
	}
	
	@DeleteMapping("user")
	public void deleteUser(@RequestBody PartyPerson person) {
		daoPartyPerson.deletePerson(person);
	}
	
	@GetMapping("/user/{id}")
	public PartyPerson userById(@PathVariable("id") int id) {
		PartyPerson person = daoPartyPerson.getPersonById(id);
		return person;
	}
	
	//gets all the parties a user is going to
	@GetMapping("/user/{id}/parties")
	public Set<Party> partyByUserId(@PathVariable("id") int id) {
		return DAOParty.getPartiesAttending(id);
	}
	
	//gets all parties a person created
	@GetMapping("/user/{id}/owner")
	public Set<Party> partyCreatedByUserId(@PathVariable("id") int id) {
		return DAOParty.getPartiesCreated(id);
	}
	
	@PostMapping("user")
	public void createUser(@RequestBody PartyPerson person) {
		daoPartyPerson.insertPerson(person);
	}
	@PatchMapping("user")
	public PartyPerson patchParty(@RequestBody Party party) {
		return null;
	}
}
