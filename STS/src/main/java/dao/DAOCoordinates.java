package dao;

import com.revature.models.Coordinates;

interface DAOCoordinates {

	public int insertCoordinates(Coordinates coordinates);
	public void updateCoordinates(Coordinates coordinates);
	public void deleteCoordinates(Coordinates coordinates);
	public Coordinates getCoordinatesById(int coordId);
}
