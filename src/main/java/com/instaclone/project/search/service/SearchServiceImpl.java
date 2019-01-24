package com.instaclone.project.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instaclone.project.response.dto.ResponseDto;
import com.instaclone.project.search.dao.SearchDao;
import com.instaclone.project.search.dto.SearchDto;
import com.instaclone.project.search.dto.SearchFollowDto;

@Service
public class SearchServiceImpl implements SearchService{
	@Autowired
	private SearchDao dao;

	// 검색 방법에 따라 검색 sql 변경
	@Override
	public ResponseDto searchSystem(SearchDto dto) {
		// 리스폰스 객체 생성
		ResponseDto responseDto = new ResponseDto();
		
		// 검색조건은 follow, like, tag
		if(dto.getSearchMethod().equals("follow")) {
			// follow 테이블 / follow, follower
			responseDto.setData(dao.searchFollow(dto));
			responseDto.setMessage("검색에 성공하였습니다.");
			responseDto.setResult("success");
		}else if(dto.getSearchMethod().equals("like")) {
			// like 테이블 / board_num, id
			responseDto.setData(dao.searchLike(dto));
			responseDto.setMessage("검색에 성공하였습니다.");
			responseDto.setResult("success");
		}else if(dto.getSearchMethod().equals("tag")) {
			// tag 테이블 / board_num, tag
			responseDto.setData(dao.searchTag(dto));
			responseDto.setMessage("검색에 성공하였습니다.");
			responseDto.setResult("success");
		}else {
			System.out.println("존재하지 않는 검색조건 입니다.");
			responseDto.setMessage("존재하지 않는 검색조건입니다.");
			responseDto.setResult("fail");
		}
		return responseDto;
	}

	@Override
	public ResponseDto isFollow(SearchFollowDto dto) {
		ResponseDto responseDto = new ResponseDto();
		String isFollow = dao.selectIsFollow(dto);
		//System.out.println(isFollow);
		if(isFollow != null) {
			responseDto.setResult("followed");
		}else {
			responseDto.setResult("not");
		}
		return responseDto;
	}

	@Override
	public ResponseDto followSystem(SearchFollowDto dto) {
		ResponseDto responseDto = new ResponseDto();
		String isFollow = dao.selectIsFollow(dto);
		if(isFollow != null) {
			dao.deleteFollow(dto);
			responseDto.setResult("deleted");
		}else {
			dao.insertFollow(dto);
			responseDto.setResult("inserted");
		}
		return responseDto;
	}
	
}
