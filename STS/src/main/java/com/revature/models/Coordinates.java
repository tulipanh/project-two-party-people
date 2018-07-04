package com.revature.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * Class for longitude and latitude of an address
 * Necessary because google maps requires longitude and latitude
 * Could not use this class and instead make calls to the Geocaching Google API, 
 * but we decided we would rather make extra calls to the database than extra HTTP requests
 */
@Entity
@Table
@DynamicUpdate
public class Coordinates implements Comparable<Coordinates>{

	@Column
	private Double latitude;
	@Column
	private Double longitude;
	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="coordinateSequence")
	@SequenceGenerator(allocationSize=1,name="coordinateSequence",sequenceName="SQ_COORDINATE_PK")
	private Integer coordinateId;
	
	public Coordinates() {
		super();
	}
	
	public Coordinates(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Integer getCoordinateId() {
		return coordinateId;
	}
	public void setCoordinateId(Integer coordinateId) {
		this.coordinateId = coordinateId;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLongitude() {
		return longitude;
	}

	@Override
	public String toString() {
		return "Coordinates [latitude=" + latitude + ", longitude=" + longitude + ", coordinateId=" + coordinateId
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordinateId == null) ? 0 : coordinateId.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
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
		if (coordinateId == null) {
			if (other.coordinateId != null)
				return false;
		} else if (!coordinateId.equals(other.coordinateId))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}

	@Override
	public int compareTo(Coordinates o) {
		if(this.latitude > o.latitude && this.longitude > o.longitude) {
			return 1;
		}else if(this.latitude < o.latitude && this.longitude < o.longitude) {
			return -1;
		}else {
			return 0;
		}
		
		
	}
	
	
	
	
}