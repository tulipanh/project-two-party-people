package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.models.Address;
import com.revature.util.HibernateUtil;

public class DAOAddressImpl implements DAOAddress {

	@Override
	public int insertAddress(Address address) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		int pk = (int) session.save(address);
		tx.commit();
		session.close();
		return pk;
	}

	@Override
	public void updateAddress(Address address) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.update(address);
		tx.commit();
		session.close();
	}

	@Override
	public void deleteAddress(Address address) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.delete(address);
		tx.commit();
		session.close();
	}

	@Override
	public Address getAddressById(int addressId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Address.class);
		criteria.add(Restrictions.eq("addressId", addressId));
		List<Address> addressList = criteria.list();
		if(addressList.size() > 0) {
			//since addressId is primary key, there can only be 0 or 1 items in this list
			session.close();
			return addressList.get(0);
		}else {
			session.close();
			return null;
		}
	}
}
