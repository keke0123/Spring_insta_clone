package com.instaclone.project.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instaclone.project.profile.dao.ProfileDao;
import com.instaclone.project.profile.dto.ProfileBoardDto;
import com.instaclone.project.profile.dto.ProfileDto;
import com.instaclone.project.response.dto.ResponseDto;

@Service
public class ProfileServiceImpl implements ProfileService{
	@Autowired
	private ProfileDao dao;

	@Override
	public ResponseDto profileData(ProfileDto dto) {
		String id = dto.getId();
		int rNum = dto.getrNum();
		// id 값으로 thumbnail, count_board, 
		// count_follow, count_follower 값을 가져온다.
		dto = dao.selectProfile(dto);
		dto.setId(id);
		dto.setrNum(rNum);
		// id 값으로 board_num 을 받아온다. rNum 으로 갯수제한
		List<ProfileBoardDto>  boardDto = dao.selectBoard(dto);
		// board_num 을 가지고 imgs 를 받아온다.
		for(ProfileBoardDto temp : boardDto) {
			List<String> imgs=dao.selectImg(temp);
			temp.setImgs(imgs);
		}
		dto.setBoardData(boardDto);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setData(dto);
		responseDto.setResult("success");
		return responseDto;
	}
	
	
}
