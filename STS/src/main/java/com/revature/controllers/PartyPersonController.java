package com.revature.controllers;

import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dao.DAOParty;
import com.revature.dao.DAOPartyPerson;
import com.revature.emails.SendEmail;
import com.revature.models.Party;
import com.revature.models.PartyPerson;
import com.revature.util.HashPassword;

/**
 * RESTful server for users, with the base as /user for post, put, and delete
 * user/login takes a username and password in the URL
 * user/{id} get returns a user
 * user/{id}/parties gets all parties that a user has RSVP'd to
 * user/{id}/parties/owner gets all parties a user has created
 */
@CrossOrigin(origins="*")
@RestController
public class PartyPersonController {
	
	@ModelAttribute
	public void setVaryResponseHeader(HttpServletResponse response) {
	    response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, PATCH");
	}  

	@Autowired
	DAOPartyPerson daoPartyPerson;
	
	@Autowired
	DAOParty DAOParty;
	
	@Autowired
	SendEmail sendEmail;
	
	@GetMapping("/user/login")
	public PartyPerson tryLogin(@ModelAttribute("username") String username,
			@ModelAttribute("password") String password) {
		return daoPartyPerson.login(username, password);
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
	@GetMapping("/party/{id}/owner")
	public Set<Party> partyCreatedByUserId(@PathVariable("id") int id) {
		return DAOParty.getPartiesCreated(id);
	}
	
	@PostMapping("/user")
	public PartyPerson createUser(@RequestBody PartyPerson person) {
		person.setPassword(HashPassword.hash(person.getPassword()));
		System.out.println(person);
		if(daoPartyPerson.insertPerson(person) > 0) {
			if(person.getEmail() != null) {
				sendEmail.sendWelcomeEmail(person);
			}
			person.setPassword(null);
			return person;
		}else {
			return null;
		}
	}
	
	@PutMapping("/user")
	public PartyPerson updateUser(@RequestBody PartyPerson person) {
		if(daoPartyPerson.updatePerson(person) > 0) {
			person.setPassword(null);
			return person;
		}else {
			return null;
		}
	}
	
	@DeleteMapping("/user")
	public void deleteUser(@RequestBody PartyPerson person) {
		daoPartyPerson.deletePerson(person);
	}
	
	@PatchMapping("/user")
	public PartyPerson patchParty(@RequestBody Party party) {
		return null;
	}
}
