package com.instaclone.project.search.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instaclone.project.profile.dto.ProfileDto;
import com.instaclone.project.profile.service.ProfileService;
import com.instaclone.project.response.dto.ResponseDto;
import com.instaclone.project.search.dto.SearchDto;
import com.instaclone.project.search.dto.SearchFollowDto;
import com.instaclone.project.search.service.SearchService;

@Controller
public class SearchController {
	@Autowired
	private SearchService service;
	@Autowired
	private ProfileService profileService;
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="search", method=RequestMethod.GET)
	@ResponseBody
	public ResponseDto searching(@ModelAttribute SearchDto dto){
		// dto 객체에 전달 받은 값
		System.out.println(dto.getSearch());
		System.out.println(dto.getSearchMethod());
		// 검색어, 검색종류 [팔로우(갯수, id), 태그순(검색어랑 비슷한 태그가 많은순, board), 좋아요(갯수, board)]
		ResponseDto responseDto = service.searchSystem(dto);
		return responseDto;
	}
	@CrossOrigin(origins="*")
	@RequestMapping(value="searchid", method=RequestMethod.GET)
	@ResponseBody
	public ResponseDto searchId(HttpServletRequest request){
		// search_id 의 개인 페이지를 연다.
		String id = request.getParameter("id");
		int rNum = Integer.parseInt(request.getParameter("rNum"));
		//System.out.println(id);
		//System.out.println(rNum);
		//
		ProfileDto dto = new ProfileDto();
		dto.setId(id);
		dto.setrNum(rNum);
		// service 에서 id 값을 바탕으로 data 를 가져온다.
		ResponseDto responseDto = profileService.profileData(dto);
		return responseDto;
	}
	@CrossOrigin(origins="*")
	@RequestMapping(value="searchisfollow", method=RequestMethod.GET)
	@ResponseBody
	public ResponseDto searchIsFollow(HttpServletRequest request){
		// search_id 의 개인 페이지를 연다.
		String id = request.getParameter("id");
		String myId = request.getParameter("myId");
		SearchFollowDto dto = new SearchFollowDto();
		dto.setId(id);
		dto.setMyId(myId);
		
		ResponseDto responseDto = service.isFollow(dto);
		return responseDto;
	}
	@CrossOrigin(origins="*")
	@RequestMapping(value="searchfollowsystem", method=RequestMethod.GET)
	@ResponseBody
	public ResponseDto searchFollowSystem(HttpServletRequest request){
		//System.out.println("followSystem");
		// search_id 의 개인 페이지를 연다.
		String id = request.getParameter("id");
		String myId = request.getParameter("myId");
		SearchFollowDto dto = new SearchFollowDto();
		dto.setId(id);
		dto.setMyId(myId);
		
		ResponseDto responseDto = service.followSystem(dto);
		return responseDto;
	}
}
