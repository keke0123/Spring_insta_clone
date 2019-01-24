package com.instaclone.project.main.service;

import com.instaclone.project.main.dto.MainDto;
import com.instaclone.project.response.dto.ResponseDto;

public interface MainService {
	public ResponseDto boardData(MainDto dto);
	public ResponseDto likeSystem(MainDto dto);
	public ResponseDto commentData(MainDto dto);
	public ResponseDto setCommentData(MainDto dto);
}
