package com.instaclone.project.search.service;

import com.instaclone.project.response.dto.ResponseDto;
import com.instaclone.project.search.dto.SearchDto;
import com.instaclone.project.search.dto.SearchFollowDto;

public interface SearchService {
	public ResponseDto searchSystem(SearchDto dto);
	public ResponseDto isFollow(SearchFollowDto dto);
	public ResponseDto followSystem(SearchFollowDto dto);
}
