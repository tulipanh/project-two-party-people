package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.dao.DAOParty;
import com.revature.models.Party;

@Controller
public class PartyController {
	
	@Autowired
	DAOParty daoPartyImpl;
	
	@GetMapping("/partyLocation/{id}")
	@ResponseBody
	public Party partyById(@PathVariable("id") int id) {
		Party party = daoPartyImpl.getPartyLocationById(id);
		return party;
	}
	
	@GetMapping("/parties")
	@ResponseBody
	public List<Party> parties(){
		return daoPartyImpl.getPartyList();
	}
	
}
