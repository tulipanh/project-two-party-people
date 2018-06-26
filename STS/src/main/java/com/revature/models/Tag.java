package com.revature.models;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private int tagName;
	@Column
	private String pathToImage;
	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tagSequence")
	@SequenceGenerator(allocationSize=1,name="tagSequence",sequenceName="SQ_tag_PK")
	private int tagId;
	
	public Tag() {
		super();
	}
	public Tag(int tagName, String pathToImage, int tagId) {
		super();
		this.tagName = tagName;
		this.pathToImage = pathToImage;
		this.tagId = tagId;
	}
	public int getTagName() {
		return tagName;
	}
	public void setTagName(int tagName) {
		this.tagName = tagName;
	}
	public String getPathToImage() {
		return pathToImage;
	}
	public void setPathToImage(String pathToImage) {
		this.pathToImage = pathToImage;
	}
	
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pathToImage == null) ? 0 : pathToImage.hashCode());
		result = prime * result + tagId;
		result = prime * result + tagName;
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
		if (pathToImage == null) {
			if (other.pathToImage != null)
				return false;
		} else if (!pathToImage.equals(other.pathToImage))
			return false;
		if (tagId != other.tagId)
			return false;
		if (tagName != other.tagName)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Tag [tagName=" + tagName + ", pathToImage=" + pathToImage + ", tagId=" + tagId + "]";
	}
	
}
