package com.revature.dao;

import org.springframework.stereotype.Repository;

import com.revature.models.Tag;

@Repository
public interface DAOTag {

	public int insertTag(Tag tag);
	public void updateTag(Tag tag);
	public void deleteTag(Tag tag);
	public Tag getTagById(int tagId);
}
