package com.revature.controllers;

import javax.mail.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.dao.DAOPartyPerson;
import com.revature.models.Party;
import com.revature.models.PartyPerson;

@Controller
public class PartyPersonController {

	@Autowired
	DAOPartyPerson daoPartyPerson;
	
	@GetMapping("login")
	@ResponseBody
	public PartyPerson tryLogin(@ModelAttribute("username") String username,
			@ModelAttribute("password") String password) {
		return daoPartyPerson.login(username, password);
	}
	
	@PostMapping("updateUser")
	@ResponseBody
	public void updateUser(@ModelAttribute("user") PartyPerson person) {
		daoPartyPerson.updatePerson(person);
	}
	
	@GetMapping("/user/{id}")
	@ResponseBody
	public PartyPerson userById(@PathVariable("id") int id) {
		PartyPerson person= daoPartyPerson.getPersonById(id);
		return person;
	}
	
	
	
}
