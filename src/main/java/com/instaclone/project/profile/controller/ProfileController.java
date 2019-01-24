package com.instaclone.project.profile.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instaclone.project.profile.dto.ProfileDto;
import com.instaclone.project.profile.service.ProfileService;
import com.instaclone.project.response.dto.ResponseDto;

@Controller
public class ProfileController {
	@Autowired
	private ProfileService service;
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="profile", method=RequestMethod.GET)
	@ResponseBody
	public ResponseDto profilePage(HttpServletRequest request) {
		String id = request.getParameter("id");
		int rNum = Integer.parseInt(request.getParameter("rNum"));
		System.out.println(id);
		// 
		ProfileDto dto = new ProfileDto();
		dto.setId(id);
		dto.setrNum(rNum);
		// service 에서 id 값을 바탕으로 data 를 가져온다.
		ResponseDto responseDto = service.profileData(dto);
		return responseDto;
	}
}
