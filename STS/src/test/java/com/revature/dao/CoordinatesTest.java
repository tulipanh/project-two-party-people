package com.revature.dao;

import static org.junit.Assert.*;
import org.junit.Test;

import com.revature.models.Coordinates;

import dao.DAOCoordinatesImpl;

public class CoordinatesTest {
	
	private static DAOCoordinatesImpl daoCoordImpl = new DAOCoordinatesImpl();

	@Test
	public void saveDeleteReadNewCoordinate() {
		Coordinates coordinates = new Coordinates(5.2,-7.1);
		int pk = daoCoordImpl.insertCoordinates(coordinates);
		double checkFirstCoor = daoCoordImpl.getCoordinatesById(pk).getLongitude();
		daoCoordImpl.deleteCoordinates(coordinates);
		assertEquals(-7.1, checkFirstCoor,.001);
		assertEquals(null, daoCoordImpl.getCoordinatesById(pk));
	}
}
