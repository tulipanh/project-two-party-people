package com.revature.models;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Class for the party-goers and party-creators
 * Many-to-Many relationship for people going to parties (PARTY_RSVP),
 * has a list of parties the person is RSVP'd to,
 * a list for the events the person has created,
 * and a link to the address of the person (which in turn has a link to coordinates)
 */
@Entity
@Table
public class PartyPerson {

	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="personSequence")
	@SequenceGenerator(allocationSize=1,name="personSequence",sequenceName="SQ_PERSON_PK")
	private Integer personId;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	@ManyToMany(cascade= {CascadeType.ALL},fetch= FetchType.LAZY )
	@JoinTable(
			name="PARTY_RSVP",
			joinColumns= {@JoinColumn(name="personId")},
			inverseJoinColumns= {@JoinColumn(name="partyId")}
			)
	private Set<Party> eventsRSVP;
	@Column
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="personId")
	private Set<Party> creatorEvents;
	
	@Column
	private Integer age;
	@OneToOne(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="addressId")
	private Address address;
	
	public PartyPerson() {
		super();
	}

	public PartyPerson(Integer personId, String username, String password, String email, Set<Party> eventsRSVP,
			Set<Party> creatorEvents, Integer age, Address address) {
		super();
		this.personId = personId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.eventsRSVP = eventsRSVP;
		this.creatorEvents = creatorEvents;
		this.age = age;
		this.address = address;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Party> getEventsRSVP() {
		return eventsRSVP;
	}

	public void setEventsRSVP(Set<Party> eventsRSVP) {
		this.eventsRSVP = eventsRSVP;
	}

	public Set<Party> getCreatorEvents() {
		return creatorEvents;
	}

	public void setCreatorEvents(Set<Party> creatorEvents) {
		this.creatorEvents = creatorEvents;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((creatorEvents == null) ? 0 : creatorEvents.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((eventsRSVP == null) ? 0 : eventsRSVP.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((personId == null) ? 0 : personId.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		PartyPerson other = (PartyPerson) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (creatorEvents == null) {
			if (other.creatorEvents != null)
				return false;
		} else if (!creatorEvents.equals(other.creatorEvents))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (eventsRSVP == null) {
			if (other.eventsRSVP != null)
				return false;
		} else if (!eventsRSVP.equals(other.eventsRSVP))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (personId == null) {
			if (other.personId != null)
				return false;
		} else if (!personId.equals(other.personId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PartyPerson [personId=" + personId + ", username=" + username + ", password=" + password + ", email="
				+ email + ", eventsRSVP=" + eventsRSVP + ", creatorEvents=" + creatorEvents + ", age=" + age
				+ ", address=" + address + "]";
	}
	
}
