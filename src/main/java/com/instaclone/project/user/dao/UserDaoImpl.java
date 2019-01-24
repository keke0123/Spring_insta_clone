package com.instaclone.project.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.instaclone.project.user.dto.UserDto;
import com.instaclone.project.user.dto.UserLoginDto;
import com.instaclone.project.user.dto.UserPasswordDto;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	private SqlSession session;

	@Override
	public void insertUserData(UserDto dto) {
		session.insert("user.insertUserData", dto);
	}

	@Override
	public String selectPassword(UserDto dto) {
		return session.selectOne("user.selectPassword", dto);
	}

	@Override
	public String selectUserId(UserDto dto) {
		return session.selectOne("user.selectUserId", dto);
	}

	@Override
	public void insertPasswordDto(UserPasswordDto dto) {
		session.insert("user.insertPasswordDto", dto);
		
	}

	@Override
	public String selectEmail(UserDto dto) {
		return session.selectOne("user.selectEmail", dto);
	}

	@Override
	public String selectAbleTime(UserPasswordDto dto) {
		return session.selectOne("user.selectAbleTime", dto);
	}

	@Override
	public void updatePassword(UserDto dto) {
		session.update("user.updatePassword", dto);
		
	}
	//login 정보 저장
	@Override
	public void insertLoginData(UserLoginDto dto) {
		session.insert("user.insertLoginData", dto);
		
	}
	// login data 유효한지 확인
	@Override
	public String selectLoginData(UserLoginDto dto) {
		return session.selectOne("user.selectLoginData", dto);
	}
}
