package com.instaclone.project.search.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.instaclone.project.search.dto.SearchDto;
import com.instaclone.project.search.dto.SearchFollowDto;

@Repository
public class SearchDaoImpl implements SearchDao{
	@Autowired
	private SqlSession session;

	@Override
	public List<SearchDto> searchFollow(SearchDto dto) {
		return session.selectList("search.selectFollow", dto);
		
	}

	@Override
	public List<SearchDto> searchLike(SearchDto dto) {
		return session.selectList("search.selectLike", dto);
	}

	@Override
	public List<SearchDto> searchTag(SearchDto dto) {
		return session.selectList("search.selectTag", dto);
	}

	@Override
	public String selectIsFollow(SearchFollowDto dto) {
		return session.selectOne("search.selectIsFollow", dto);
	}

	@Override
	public void insertFollow(SearchFollowDto dto) {
		session.insert("search.insertFollow", dto);
	}

	@Override
	public void deleteFollow(SearchFollowDto dto) {
		session.delete("search.deleteFollow", dto);
	}
	
}
