package com.instaclone.project.user.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.instaclone.project.response.dto.ResponseDto;
import com.instaclone.project.user.dto.UserDto;
import com.instaclone.project.user.dto.UserLoginDto;

public interface UserService {
	public String generateToken(int n);
	public ResponseDto saveUser(UserDto dto);
	public ResponseDto isCorrectUser(UserDto dto);
	public void sendMail();
	public ResponseDto isCanUseId(UserDto dto);
	public ResponseDto sendMailForPassword(UserDto dto);
	public ModelAndView passwordChange(int passNum, String password, ModelAndView mView);
	public boolean isLogined(UserLoginDto dto);
}
