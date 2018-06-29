package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.dao.DAOParty;
import com.revature.models.Coordinates;
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
	
	@GetMapping("/localParties")
	@ResponseBody
	public List<Party> parties(@ModelAttribute("minLat") double minLat,
			@ModelAttribute("minLong") double minLong,
			@ModelAttribute("maxLat") double maxLat,
			@ModelAttribute("maxLong") double maxLong){
		Coordinates coordinates1 = new Coordinates();
		Coordinates coordinates2 = new Coordinates();
		coordinates1.setLatitude(minLat);
		coordinates1.setLongitude(minLong);
		coordinates2.setLatitude(maxLat);
		coordinates2.setLongitude(maxLong);
		return daoPartyImpl.getPartyListWithinCoordinates(coordinates1,coordinates2);
	}
	
}
