package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.models.Party;
import com.revature.util.HibernateUtil;

public class DAOPartyImpl implements DAOParty {

	@Override
	public int insertParty(Party party) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		int pk = (int) session.save(party);
		tx.commit();
		session.close();
		return pk;
	}

	@Override
	public void updateParty(Party party) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.update(party);
		tx.commit();
		session.close();
	}

	@Override
	public void deleteParty(Party party) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.delete(party);
		tx.commit();
		session.close();
	}

	@Override
	public Party getPartyById(int partyId) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Party.class);
		criteria.add(Restrictions.eq("partyId", partyId));
		List<Party> partyList = criteria.list();
		if(partyList.size() > 0) {
			//since partyId is primary key, there can only be 0 or 1 items in this list
			session.close();
			return partyList.get(0);
		}else {
			session.close();
			return null;
		}
	}

}
