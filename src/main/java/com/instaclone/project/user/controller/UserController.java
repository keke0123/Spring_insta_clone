package com.instaclone.project.user.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.instaclone.project.response.dto.ResponseDto;
import com.instaclone.project.user.dto.UserDto;
import com.instaclone.project.user.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@CrossOrigin(origins="*")
	// signup 요청에 대한 응답
	@RequestMapping(value="signup", method=RequestMethod.POST)
	@ResponseBody
	public ResponseDto signUp(@RequestBody HashMap<String, String> map) {
		System.out.println("signUp");
		System.out.println("id : "+map.get("id"));
		System.out.println("password :"+map.get("password"));
		System.out.println("email :"+map.get("email"));
		
		String id = map.get("id");
		String password = map.get("password");
		String email = map.get("email");
		
		UserDto dto = new UserDto();
		dto.setId(id);
		dto.setPassword(password);
		dto.setEmail(email);
		
		ResponseDto responseDto = service.saveUser(dto);
		
		return responseDto;
	}
	
	@CrossOrigin(origins="*")
	// 존재하는 아이디인지 체크
	@RequestMapping(value="checkid", method=RequestMethod.GET)
	@ResponseBody
	public ResponseDto checkId(@ModelAttribute UserDto dto) {
		System.out.println(dto.getId());
		// 사용 가능한 아이디 인지 확인하고 return
		ResponseDto responseDto = service.isCanUseId(dto);
		return responseDto;
	}
	
	@CrossOrigin(origins="*")
	// login 처리
	@RequestMapping(value="login", method=RequestMethod.POST)
	@ResponseBody
	public ResponseDto logIn(@RequestBody HashMap<String, String> map) {
		System.out.println("logIn");
		System.out.println("id : "+map.get("id"));
		System.out.println("password : "+map.get("password"));
		
		String id = map.get("id");
		String password = map.get("password");
		
		UserDto dto = new UserDto();
		dto.setId(id);
		dto.setPassword(password);
		
		ResponseDto responseDto = service.isCorrectUser(dto);
		return responseDto;
	}
	
	@CrossOrigin(origins="*")
	// 비밀번호 찾기 / 가입한 메일 주소 입력하면 그곳으로 변경 가능 주소 전송
	@RequestMapping(value="findpassword", method=RequestMethod.GET)
	@ResponseBody
	public ResponseDto findPassword(@ModelAttribute UserDto dto) {
		System.out.println("email : "+dto.getEmail());
		
		ResponseDto responseDto = service.sendMailForPassword(dto);
		return responseDto;
	}	
	@CrossOrigin(origins="*")
	// 비밀번호 변경 페이지
	@RequestMapping(value="{passNum}/changepassword", method=RequestMethod.GET)
	public ModelAndView changePasswordForm(@PathVariable int passNum) {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("user/changePasswordForm");
		return mView;
	}
	// 비밀번호 변경
	@RequestMapping(value="{passNum}/changepassword", method=RequestMethod.POST)
	public ModelAndView changePassword(@PathVariable int passNum, @RequestParam String password, 
			ModelAndView mView) {
		//비밀번호 변경에는 패턴 체크 안했음
		//비밀번호 변경
		System.out.println("passNum : "+passNum);
		System.out.println("password : "+password);
		mView=service.passwordChange(passNum, password, mView);	
		mView.setViewName("user/changePassword");
		return mView;
	}
}
