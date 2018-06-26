package com.revature.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.models.Tag;

import dao.DAOTagImpl;


public class TagTest {

	static DAOTagImpl daoTagImpl = new DAOTagImpl();
	
	@Test
	public void saveDeleteReadNewAddress() {
		Tag tag = new Tag();
		tag.setTagName(3);
		int pk = daoTagImpl.insertTag(tag);
		int tagNumber = daoTagImpl.getTagById(pk).getTagName();
		daoTagImpl.deleteTag(tag);
		assertEquals(3, tagNumber);
		assertEquals(null, daoTagImpl.getTagById(pk));
	}
	
}
