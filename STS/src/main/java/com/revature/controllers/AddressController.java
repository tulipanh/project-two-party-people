package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.dao.DAOAddress;
import com.revature.models.Address;

@Controller
public class AddressController {

	@Autowired
	DAOAddress daoAddress;
	
	@GetMapping("/address/{id}")
	@ResponseBody
	public Address addressById(@PathVariable("id") int id) {
		Address address= daoAddress.getAddressById(id);
		return address;
	}
}
