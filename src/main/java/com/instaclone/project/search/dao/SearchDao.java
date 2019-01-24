package com.instaclone.project.search.dao;

import java.util.List;

import com.instaclone.project.search.dto.SearchDto;
import com.instaclone.project.search.dto.SearchFollowDto;

public interface SearchDao {
	public List<SearchDto> searchFollow(SearchDto dto);
	public List<SearchDto> searchLike(SearchDto dto);
	public List<SearchDto> searchTag(SearchDto dto);
	public String selectIsFollow(SearchFollowDto dto);
	public void insertFollow(SearchFollowDto dto);
	public void deleteFollow(SearchFollowDto dto);
}
