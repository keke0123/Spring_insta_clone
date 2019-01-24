package com.instaclone.project.user.dao;

import com.instaclone.project.user.dto.UserDto;
import com.instaclone.project.user.dto.UserLoginDto;
import com.instaclone.project.user.dto.UserPasswordDto;

public interface UserDao {
	public void insertUserData(UserDto dto);
	public String selectPassword(UserDto dto);
	public String selectUserId(UserDto dto);
	public void insertPasswordDto(UserPasswordDto dto);
	public String selectEmail(UserDto dto);
	public String selectAbleTime(UserPasswordDto dto);
	public void updatePassword(UserDto dto);
	public void insertLoginData(UserLoginDto dto);
	public String selectLoginData(UserLoginDto dto);
}
