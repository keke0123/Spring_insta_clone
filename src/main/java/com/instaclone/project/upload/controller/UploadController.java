package com.instaclone.project.upload.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instaclone.project.response.dto.ResponseDto;
import com.instaclone.project.upload.dto.UploadDataDto;
import com.instaclone.project.upload.dto.UploadDto;
import com.instaclone.project.upload.service.UploadService;
import com.instaclone.project.user.dto.UserLoginDto;
import com.instaclone.project.user.service.UserService;

@Controller
public class UploadController {
	@Autowired
	private UploadService service;
	@Autowired
	private UserService userService;
	
	// base64 이미지 test
	@RequestMapping(value="upload", method=RequestMethod.POST)
	@ResponseBody
	public ResponseDto postImgBase64(HttpServletRequest request, @ModelAttribute UploadDto dto) {
		// String / dto.imgBase64 
		System.out.println("imgBase64 : "+dto.getImgBase64());
		
		ResponseDto responseDto = service.saveImgBase64(request, dto);
		
		System.out.println("response : "+responseDto.getResult());
		
		
		return null;
	}
	
	@CrossOrigin(origins="*")
	// upload data
	@RequestMapping(value="uploaddata", method=RequestMethod.POST)
	@ResponseBody
	public ResponseDto authUploadData(@RequestBody HashMap<String, Object> map,
			HttpServletRequest request) {
		// 응답 객체 생성
		ResponseDto responseDto = null;
		//System.out.println("유효한 사용자 입니다.");
		// data 를 담을 dataDto 객체 생성
		UploadDataDto dataDto = new UploadDataDto();
		// 유효한 사용자면 사용자 정보를 dataDto 객체의 writer 에 넣는다.
		dataDto.setWriter(request.getParameter("id"));
		// body 에 내용을 받아서 dataDto 객체에 넣는다.
		ArrayList<String> uploadImgs = (ArrayList<String>)map.get("uploadImgs");
		ArrayList<String> tags = (ArrayList<String>)map.get("tags");
		String content = (String)map.get("content");
		dataDto.setUploadImgs(uploadImgs);
		dataDto.setTags(tags);
		dataDto.setContent(content);
		System.out.println("데이타를 저장합니다.");
		// service 에 dto 객체 전송해서 data 저장후 responseDto 객체 리턴받음
		responseDto = service.saveData(dataDto, request);
		return responseDto;
	}
	
}
