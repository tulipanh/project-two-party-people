package com.revature.controllers;

import java.util.List;

import javax.mail.Part;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.dao.DAOParty;
import com.revature.dao.DAOPartyPerson;
import com.revature.models.Party;
import com.revature.models.PartyPerson;

@Controller
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
	@ResponseBody
	public PartyPerson tryLogin(@ModelAttribute("username") String username,
			@ModelAttribute("password") String password) {
		return daoPartyPerson.login(username, password);
	}
	
	@PostMapping("update-user")
	@ResponseBody
	public void updateUser(@RequestBody PartyPerson person) {
		daoPartyPerson.updatePerson(person);
	}
	
	@GetMapping("/user/{id}")
	@ResponseBody
	public PartyPerson userById(@PathVariable("id") int id) {
		PartyPerson person = daoPartyPerson.getPersonById(id);
		return person;
	}
	
	//gets all the parties a user is going to
	@GetMapping("/user/{id}/parties")
	@ResponseBody
	public List<Party> partyByUserId(@PathVariable("id") int id) {
		return DAOParty.getPartiesAttending(id);
	}
	
	//gets all parties a person created
	@GetMapping("/user/{id}/owner")
	@ResponseBody
	public List<Party> partyCreatedByUserId(@PathVariable("id") int id) {
		return DAOParty.getPartiesCreated(id);
	}
	
	@PostMapping("user-create")
	@ResponseBody
	public void createUser(@RequestBody PartyPerson person) {
		daoPartyPerson.insertPerson(person);
	}
}
