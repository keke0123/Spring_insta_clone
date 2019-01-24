package com.instaclone.project.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instaclone.project.main.dao.MainDao;
import com.instaclone.project.main.dto.MainDto;
import com.instaclone.project.response.dto.ResponseDto;

@Service
public class MainServiceImpl implements MainService{
	@Autowired
	private MainDao dao;

	@Override
	public ResponseDto boardData(MainDto dto) {
		List<MainDto> list = null;
		String id = dto.getId(); 
		// MainDto 객체 / id, rowNum
		// id 로 follow 친구들의 board 정보를 가져온다.
		// board_num / writer / content / regdate / rnum
		list = dao.selectBoard(dto);
		// board_num 을 바탕으로 나머지 정보를 가져온다.
		// thumbnail / isLike / count_like / count_comment
		// imgs / tags
		//System.out.println("for 문을 돌면서 나머지 정보를 가져옵니다.");
		for(MainDto temp : list) {
			temp.setId(id);
			//System.out.println("나머지 정보를 받아옵니다.");
			// thumbnail, isLike, count_like, count_comment 를 받아서 세팅
			MainDto tempDto=dao.selectBoardOther(temp);
			if(tempDto!=null) {
				temp.setThumbnail(tempDto.getThumbnail());
				temp.setIsLike(tempDto.getIsLike());
				temp.setCount_like(tempDto.getCount_like());
				temp.setCount_comment(tempDto.getCount_comment());
			}
			//System.out.println("이미지 정보를 받아옵니다.");
			// imgs 를 받아서 넣는다.
			List<String> imgs = dao.selectImgList(temp);
			temp.setImgs(imgs);
			// tags 를 받아서 넣는다.
			//System.out.println("tag 정보를 받아옵니다.");
			List<String> tags = dao.selectTagList(temp);
			temp.setTags(tags);
		}
		ResponseDto responseDto = new ResponseDto();
		responseDto.setResult("success");
		responseDto.setData(list);
		System.out.println("응답합니다.");
		// ResponseDto 객체에 담아서 응답한다.
		return responseDto;
	}

	@Override
	public ResponseDto likeSystem(MainDto dto) {
		// 응답객체 생성
		ResponseDto responseDto = new ResponseDto();
		// id 값과 board_num 을 바탕으로 db 에서 게시물에 좋아요가 있는지를 판별해온다.
		String isLike = dao.selectIsLike(dto);
		if(isLike == null) {
			dao.insertLike(dto);
			responseDto.setResult("up");
		}else {
			dao.deleteLike(dto);
			responseDto.setResult("down");
		}
		return responseDto;
	}

	@Override
	public ResponseDto commentData(MainDto dto) {
		ResponseDto responseDto = new ResponseDto();
		// board_num 값을 가지고 comment data 를 가져온다.
		List<MainDto> list = dao.selectComment(dto);
		responseDto.setData(list);
		responseDto.setResult("success");
		return responseDto;
	}

	@Override
	public ResponseDto setCommentData(MainDto dto) {
		System.out.println(dto.getId());
		System.out.println(dto.getBoard_num());
		// 아래는 새로 생성해서 넣어주기
		//System.out.println(dto.getComment_num());
		// comment_group_num 이 0 이면 본문에 대한 댓글
		System.out.println(dto.getComment_group_num());
		System.out.println(dto.getComment_content());
		System.out.println(dto.getTarget());
		
		dto.setComment_writer(dto.getId());
		// sequence 값을 받아와서 
		// 본문의 댓글이면 group 값과 comment 값에 seq 를 넣어주고
		// group 의 댓글이면 comment 값에만 seq 를 넣어준다.
		int seq=dao.getSequence();
		if(dto.getComment_group_num()==0) {
			dto.setComment_group_num(seq);
			dto.setComment_num(seq);
		}else {
			dto.setComment_num(seq);
		}
		// dto 값을 바탕으로 comment 작성
		dao.insertComment(dto);
		
		ResponseDto responseDto = new ResponseDto();
		responseDto.setResult("success");
		
		return responseDto;
	}
}
