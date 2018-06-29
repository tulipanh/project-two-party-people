package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.dao.DAOCoordinates;
import com.revature.dao.DAOParty;
import com.revature.models.Coordinates;
import com.revature.models.Party;

@Controller
public class CoordinatesController {

	@Autowired
	DAOCoordinates daoCoord;
	
	@GetMapping("/coordinates/{id}")
	@ResponseBody
	public Coordinates coordinatesById(@PathVariable("id") int id) {
		Coordinates coordinates = daoCoord.getCoordinatesById(id);
		return coordinates;
	}
}
