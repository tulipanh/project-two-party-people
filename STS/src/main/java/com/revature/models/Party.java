package com.revature.models;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Class for the party object.  Links to the person who created the party (creator),
 * the address of the party (and the address links to the coordinates),
 * has a list of attendees in a many-to-many relationship with PartyPerson,
 * a list of tags that apply to the event,
 * and a link to the URL of the picture for the event
 */
@Entity
@Table
public class Party {
	
	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="partySequence")
	@SequenceGenerator(allocationSize=1,name="partySequence",sequenceName="SQ_party_PK")
	private Integer partyId;
	@ManyToOne(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="personId")
	private PartyPerson creator;
	@OneToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="addressId")
	private Address address;
	@Column
	private String partyName;
	@Column
	private Timestamp partyDate;
	@Column
	private Double cost;
	
	@ManyToMany(cascade= {CascadeType.ALL},fetch= FetchType.LAZY )
	@JoinTable(
			name="PARTY_RSVP",
			joinColumns= {@JoinColumn(name="personId")},
			inverseJoinColumns= {@JoinColumn(name="partyId")}
			)
	private Set<PartyPerson> attendees;
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="partyId")
	private Set<Tag> tagList;
	@Column
	private String pictureUrl;
	
	
	public Party() {
		super();
	}


	public Party(Integer partyId, PartyPerson creator, Address address, String partyName, Timestamp partyDate,
			Set<PartyPerson> attendees, Set<Tag> tagList, String pictureUrl) {
		super();
		this.partyId = partyId;
		this.creator = creator;
		this.address = address;
		this.partyName = partyName;
		this.partyDate = partyDate;
		this.attendees = attendees;
		this.tagList = tagList;
		this.pictureUrl = pictureUrl;
	}


	public Integer getPartyId() {
		return partyId;
	}


	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}


	public PartyPerson getCreator() {
		return creator;
	}


	public void setCreator(PartyPerson creator) {
		this.creator = creator;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public String getPartyName() {
		return partyName;
	}


	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}


	public Timestamp getPartyDate() {
		return partyDate;
	}


	public void setPartyDate(Timestamp partyDate) {
		this.partyDate = partyDate;
	}


	public Set<PartyPerson> getAttendees() {
		return attendees;
	}


	public void setAttendees(Set<PartyPerson> attendees) {
		this.attendees = attendees;
	}


	public Set<Tag> getTagList() {
		return tagList;
	}


	public void setTagList(Set<Tag> tagList) {
		this.tagList = tagList;
	}


	public String getPictureUrl() {
		return pictureUrl;
	}


	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}


	@Override
	public String toString() {
		return "Party [partyId=" + partyId + ", creator=" + creator + ", address=" + address + ", partyName="
				+ partyName + ", partyDate=" + partyDate + ", attendees=" + attendees + ", tagList=" + tagList
				+ ", pictureUrl=" + pictureUrl + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((attendees == null) ? 0 : attendees.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((partyDate == null) ? 0 : partyDate.hashCode());
		result = prime * result + ((partyId == null) ? 0 : partyId.hashCode());
		result = prime * result + ((partyName == null) ? 0 : partyName.hashCode());
		result = prime * result + ((pictureUrl == null) ? 0 : pictureUrl.hashCode());
		result = prime * result + ((tagList == null) ? 0 : tagList.hashCode());
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
		Party other = (Party) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (attendees == null) {
			if (other.attendees != null)
				return false;
		} else if (!attendees.equals(other.attendees))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (partyDate == null) {
			if (other.partyDate != null)
				return false;
		} else if (!partyDate.equals(other.partyDate))
			return false;
		if (partyId == null) {
			if (other.partyId != null)
				return false;
		} else if (!partyId.equals(other.partyId))
			return false;
		if (partyName == null) {
			if (other.partyName != null)
				return false;
		} else if (!partyName.equals(other.partyName))
			return false;
		if (pictureUrl == null) {
			if (other.pictureUrl != null)
				return false;
		} else if (!pictureUrl.equals(other.pictureUrl))
			return false;
		if (tagList == null) {
			if (other.tagList != null)
				return false;
		} else if (!tagList.equals(other.tagList))
			return false;
		return true;
	}
	
	
}
