package com.instaclone.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.instaclone.project.response.dto.ResponseDto;
import com.instaclone.project.user.service.UserService;

@Controller
public class TestController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value="test", method=RequestMethod.GET)
	@ResponseBody
	public boolean getTest(HttpServletRequest request, ModelAndView mView, @RequestParam String test) {
		
		System.out.println("get");
		System.out.println(test);
		
		return true;
	}
	@RequestMapping(value="test", method=RequestMethod.POST)
	@ResponseBody
	public boolean postTest(HttpServletRequest request, ModelAndView mView, @RequestParam String test) {
		
		System.out.println("post");
		System.out.println(test);
		
		return true;
	}
	@RequestMapping(value="mailTest")
	@ResponseBody
	public boolean mailTest(HttpServletRequest request) {
		System.out.println("mail 발송 시작");
		service.sendMail();
		System.out.println("mail 발송 성공");
		return true;
	}
	// restapi
	@RequestMapping(value="{id}/restapi")
	@ResponseBody
	public boolean restapiTest(HttpServletRequest request, @PathVariable String id) {
		System.out.println("id : "+id);
		return true;
	}

	@CrossOrigin(origins="*")
	@RequestMapping(value="loginTest1")
	@ResponseBody
	public ResponseDto authLoginTest1(HttpServletRequest request) {
		System.out.println("main 페이지");
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("어쩌구 저쩌구...");
		return responseDto;
	}
	@CrossOrigin(origins="*")
	@RequestMapping(value="loginTest2")
	@ResponseBody
	public ResponseDto loginTest2(HttpServletRequest request, HttpSession session) {
		session.setAttribute("id", request.getParameter("id"));
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("session 저장 시도");
		return responseDto;
	}
	@CrossOrigin(origins="*")
	@RequestMapping(value="loginTest3")
	@ResponseBody
	public ResponseDto loginTest3(HttpServletRequest request, HttpSession session) {
		String id = (String)session.getAttribute("id");
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("session 에 저장된 id : "+id);
		return responseDto;
	}
}
