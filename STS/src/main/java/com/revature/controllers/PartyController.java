package com.revature.controllers;

import java.math.BigDecimal;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.revature.dao.DAOParty;
import com.revature.emails.SendEmail;
import com.revature.models.Party;
import com.revature.models.PartyPerson;

/**
 * RESTful Controller for party objects.  
 * /party has post, put, and delete methods with parties in the request body.
 * party/{id} has get
 * party/attendees gets the RSVP list, 
 * party/local takes max and min lat and long and returns all parties within that rectangle
 * party/local/radius takes a lat and long and a radius and returns all parties in that radius
 */

@CrossOrigin(origins="*")
@RestController
public class PartyController {
	
	
	@Autowired
	DAOParty daoPartyImpl;
	
	@Autowired
	SendEmail sendEmail;
	
	@ModelAttribute
	public void setVaryResponseHeader(HttpServletResponse response) {
	    response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, PATCH");
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
	
	@GetMapping("/party/local")
	public Set<Party> parties(@ModelAttribute("minLat") Double minLat,
			@ModelAttribute("minLong") Double minLong,
			@ModelAttribute("maxLat") Double maxLat,
			@ModelAttribute("maxLong") Double maxLong){
		return daoPartyImpl.getPartyListWithinCoordinates(minLat,minLong,maxLat,maxLong);
	}
	
	@GetMapping("/party/local/radius")
	public List<BigDecimal> parties(@RequestParam("radius") Double radius, 
			@RequestParam("latitude") Double latitude,
			@RequestParam("longitude") Double longitude){
		return daoPartyImpl.getPartyIdsWithinRadius(radius,latitude,longitude);
	}
	
	@PostMapping("/party")
	public Party createParty(@RequestBody Party party) {
		if(daoPartyImpl.insertParty(party) > 0) {
			if(party.getCreator() != null  && party.getCreator().getEmail() != null) {
				sendEmail.sendEventCreatedEmail(party.getCreator(), party);
			}
			return party;
		}else {
			return null;
		}
	}
	
	@PutMapping("/party")
	public Party updateParty(@RequestBody Party party) {
		daoPartyImpl.updateParty(party);
		return party;
		
		
	}
	@DeleteMapping("/party")
	public void deleteParty(@RequestBody Party party) {
		daoPartyImpl.deleteParty(party);
	}
	@PatchMapping("/party")
	public Party patchParty(@RequestBody Party party) {
		return null;
	}
	
	
}
