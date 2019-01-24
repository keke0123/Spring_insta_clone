package com.instaclone.project.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instaclone.project.main.dto.MainDto;
import com.instaclone.project.main.service.MainService;
import com.instaclone.project.response.dto.ResponseDto;

@Controller
public class MainController {
	@Autowired
	private MainService service;
	
	// main 페이지 접속시
	@CrossOrigin(origins="*")
	@RequestMapping(value="mainPage", method=RequestMethod.GET)
	@ResponseBody
	public ResponseDto mainPage(HttpServletRequest request) {
		// parameter 로 날아온 id 값과 rNum 값을 추출해서 mainDto 객체에 담는다.
		String id = request.getParameter("id");
		int rNum = Integer.parseInt(request.getParameter("rNum"));
		//System.out.println(id);
		//System.out.println(rNum);
		MainDto dto = new MainDto();
		dto.setId(id);
		dto.setrNum(rNum);
		// mainDto 객체에 담긴 값을 바탕으로 데이타를 받아온다.
		ResponseDto responseDto = service.boardData(dto);
		return responseDto;
	}
	@CrossOrigin(origins="*")
	@RequestMapping(value="likebtn", method=RequestMethod.GET)
	@ResponseBody
	public ResponseDto likeBtn(HttpServletRequest request) {
		// 파라미터로 날아온 id 값을 dto 객체에 넣는다.
		String id = request.getParameter("id");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		MainDto dto = new MainDto();
		dto.setId(id);
		dto.setBoard_num(board_num);

		// mainDto 객체에 담긴 값을 바탕으로 likeSystem 에서 더할치 뺄지를 결정한다.
		ResponseDto responseDto = service.likeSystem(dto);
		return responseDto;
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="getcomment", method=RequestMethod.GET)
	@ResponseBody
	public ResponseDto getComment(HttpServletRequest request) {
		// 파라미터로 날아온 id 값을 dto 객체에 넣는다.
		String id = request.getParameter("id");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		MainDto dto = new MainDto();
		dto.setBoard_num(board_num);
		dto.setId(id);
		// mainDto 객체에 담긴 내용을 바탕으로 comment list 를 가져온다.
		ResponseDto responseDto = service.commentData(dto);
		return responseDto;
	}
	@CrossOrigin(origins="*")
	@RequestMapping(value="setcomment", method=RequestMethod.GET)
	@ResponseBody
	public ResponseDto getComment(@ModelAttribute MainDto dto) {
		System.out.println(dto.getId());
		System.out.println(dto.getBoard_num());
		// 아래는 새로 생성해서 넣어주기
		//System.out.println(dto.getComment_num());
		// comment_group_num 이 0 이면 본문에 대한 댓글
		System.out.println(dto.getComment_group_num());
		System.out.println(dto.getComment_content());
		System.out.println(dto.getTarget());
		
		// mainDto 객체에 담긴 값을 바탕으로 comment 테이블에 저장
		ResponseDto responseDto = service.setCommentData(dto);
		return responseDto;
	}

}
