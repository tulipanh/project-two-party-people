package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Coordinates {

	@Column
	private double lattitude;
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
		this.lattitude = lattitude;
		this.longitude = longitude;
		this.coordinateId = id;
	}
	public Coordinates(double lattitude, double longitude) {
		super();
		this.lattitude = lattitude;
		this.longitude = longitude;
	}
	public double getLattitude() {
		return lattitude;
	}
	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
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
		temp = Double.doubleToLongBits(lattitude);
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
		if (Double.doubleToLongBits(lattitude) != Double.doubleToLongBits(other.lattitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Coordinates [lattitude=" + lattitude + ", longitude=" + longitude + ", id=" + coordinateId + "]";
	}
}