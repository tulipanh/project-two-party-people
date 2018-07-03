package com.revature.controllers;

import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.revature.models.Party;
import com.revature.models.PartyPerson;

@RestController
public class PartyController {
	
	
	@Autowired
	DAOParty daoPartyImpl;
	
	@ModelAttribute
	public void setVaryResponseHeader(HttpServletResponse response) {
	    response.setHeader("Access-Control-Allow-Origin", "*");
	}   
	
	@GetMapping("/party/{id}")
	public Party partyById(@PathVariable("id") int id) {
		Party party = daoPartyImpl.getPartyById(id);
		return party;
	}
	
	@GetMapping("/party/{id}/attendees")
	public Set <PartyPerson> partyAttendeesById(@PathVariable("id") int id) {
		Set<PartyPerson> people = daoPartyImpl.getAttendeesById(id);
		return people;
	}
	
	@GetMapping("/local-parties")
	public Set<Party> parties(@ModelAttribute("minLat") Double minLat,
			@ModelAttribute("minLong") Double minLong,
			@ModelAttribute("maxLat") Double maxLat,
			@ModelAttribute("maxLong") Double maxLong){
		return daoPartyImpl.getPartyListWithinCoordinates(minLat,minLong,maxLat,maxLong);
	}
	
	@PostMapping("party")
	public void createParty(@RequestBody Party party) {
		daoPartyImpl.insertParty(party);
	}
	
	@PutMapping("party")
	public void updateParty(@RequestBody Party party) {
		daoPartyImpl.updateParty(party);
	}
	@DeleteMapping("party")
	public void deleteParty(@RequestBody Party party) {
		daoPartyImpl.deleteParty(party);
	}
	@PatchMapping("party")
	public Party patchParty(@RequestBody Party party) {
		return null;
	}
	
	
}
