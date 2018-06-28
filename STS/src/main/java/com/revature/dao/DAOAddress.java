package com.revature.dao;

import org.springframework.stereotype.Repository;

import com.revature.models.Address;

@Repository
public interface DAOAddress {

	public int insertAddress(Address address);
	public void updateAddress(Address address);
	public void deleteAddress(Address address);
	public Address getAddressById(int addressId);
}
