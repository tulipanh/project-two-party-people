package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Class for tags that a party has, like the type of event it is
 * Different tags are found in the util/Constants.java file, and are stored in this class
 * in the tagName variable
 * The icon for the tag is stored in the pathToImage String
 *
 */
@Entity
@Table
public class Tag {

	@Column
	private Integer tagName;
	@Column
	private String pathToImage;
	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tagSequence")
	@SequenceGenerator(allocationSize=1,name="tagSequence",sequenceName="SQ_tag_PK")
	private Integer tagId;
	@ManyToOne(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="partyId")
	private Party party;
	
	public Tag() {
		super();
	}
	
	public Tag(Integer tagName, String pathToImage, Integer tagId, Party party) {
		super();
		this.tagName = tagName;
		this.pathToImage = pathToImage;
		this.tagId = tagId;
		this.party = party;
	}

	

	public Integer getTagName() {
		return tagName;
	}

	public void setTagName(Integer tagName) {
		this.tagName = tagName;
	}

	public String getPathToImage() {
		return pathToImage;
	}

	public void setPathToImage(String pathToImage) {
		this.pathToImage = pathToImage;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((party == null) ? 0 : party.hashCode());
		result = prime * result + ((pathToImage == null) ? 0 : pathToImage.hashCode());
		result = prime * result + ((tagId == null) ? 0 : tagId.hashCode());
		result = prime * result + ((tagName == null) ? 0 : tagName.hashCode());
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
		Tag other = (Tag) obj;
		if (party == null) {
			if (other.party != null)
				return false;
		} else if (!party.equals(other.party))
			return false;
		if (pathToImage == null) {
			if (other.pathToImage != null)
				return false;
		} else if (!pathToImage.equals(other.pathToImage))
			return false;
		if (tagId == null) {
			if (other.tagId != null)
				return false;
		} else if (!tagId.equals(other.tagId))
			return false;
		if (tagName == null) {
			if (other.tagName != null)
				return false;
		} else if (!tagName.equals(other.tagName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tag [tagName=" + tagName + ", pathToImage=" + pathToImage + ", tagId=" + tagId + ", party=" + party
				+ "]";
	}

	
	
}
