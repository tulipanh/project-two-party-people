package com.revature.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Address;

@Repository
@Transactional
public class DAOAddressImpl implements DAOAddress {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public int insertAddress(Address address) {
		Session session = sessionFactory.getCurrentSession();
		return (int) session.save(address);
	}

	@Override
	public void updateAddress(Address address) {
		Session session = sessionFactory.getCurrentSession();
		session.update(address);
	}

	@Override
	public void deleteAddress(Address address) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(address);
	}

	@Override
	public Address getAddressById(int addressId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Address.class);
		criteria.add(Restrictions.eq("addressId", addressId));
		List<Address> addressList = criteria.list();
		if(addressList.size() > 0) {
			//since addressId is primary key, there can only be 0 or 1 items in this list
			return addressList.get(0);
		}else {
			return null;
		}
	}
}
