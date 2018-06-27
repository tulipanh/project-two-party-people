package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * Class for longitude and latitude of an address
 * Necessary because google maps requires longitude and latitude
 * Could not use this class and instead make calls to the Geocaching Google API, 
 * but we decided we would rather make extra calls to the database than extra HTTP requests
 */
@Entity
@Table
public class Coordinates {

	@Column
	private double latitude;
	@Column
	private double longitude;
	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="coordinateSequence")
	@SequenceGenerator(allocationSize=1,name="coordinateSequence",sequenceName="SQ_COORDINATE_PK")
	private int coordinateId;
	public Coordinates() {
		super();
	}
	public Coordinates(double lattitude, double longitude, int id) {
		super();
		this.latitude = lattitude;
		this.longitude = longitude;
		this.coordinateId = id;
	}
	public Coordinates(double lattitude, double longitude) {
		super();
		this.latitude = lattitude;
		this.longitude = longitude;
	}
	public double getLattitude() {
		return latitude;
	}
	public void setLattitude(double lattitude) {
		this.latitude = lattitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public int getId() {
		return coordinateId;
	}
	public void setId(int id) {
		this.coordinateId = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coordinateId;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (coordinateId != other.coordinateId)
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Coordinates [latitude=" + latitude + ", longitude=" + longitude + ", id=" + coordinateId + "]";
	}
}