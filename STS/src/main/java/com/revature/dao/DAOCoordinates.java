package com.revature.dao;

import org.springframework.stereotype.Repository;

import com.revature.models.Coordinates;

@Repository
public interface DAOCoordinates {

	public int insertCoordinates(Coordinates coordinates);
	public void updateCoordinates(Coordinates coordinates);
	public void deleteCoordinates(Coordinates coordinates);
	public Coordinates getCoordinatesById(int coordId);
}
