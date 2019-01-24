package com.instaclone.project.profile.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.instaclone.project.profile.dto.ProfileBoardDto;
import com.instaclone.project.profile.dto.ProfileDto;

@Repository
public class ProfileDaoImpl implements ProfileDao {
	@Autowired
	private SqlSession session;

	@Override
	public ProfileDto selectProfile(ProfileDto dto) {
		return session.selectOne("profile.selectProfile", dto);
	}

	@Override
	public List<ProfileBoardDto> selectBoard(ProfileDto dto) {
		return session.selectList("profile.selectBoard", dto);
	}

	@Override
	public List<String> selectImg(ProfileBoardDto dto) {
		return session.selectList("profile.selectImg", dto);
	}
}
