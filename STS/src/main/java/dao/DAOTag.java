package dao;

import com.revature.models.Tag;

interface DAOTag {

	public int insertTag(Tag tag);
	public void updateTag(Tag tag);
	public void deleteTag(Tag tag);
	public Tag getTagById(int tagId);
}
