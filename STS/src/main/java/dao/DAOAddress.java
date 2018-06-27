package dao;

import com.revature.models.Address;

interface DAOAddress {

	public int insertAddress(Address address);
	public void updateAddress(Address address);
	public void deleteAddress(Address address);
	public Address getAddressById(int addressId);
}
