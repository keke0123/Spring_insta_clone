package com.instaclone.project.main.dao;

import java.util.ArrayList;
import java.util.List;

import com.instaclone.project.main.dto.MainDto;

public interface MainDao {
	public List<MainDto> selectBoard(MainDto dto); 
	public MainDto selectBoardOther(MainDto dto);
	public List<String> selectImgList(MainDto dto);
	public List<String> selectTagList(MainDto dto);
	//
	public String selectIsLike(MainDto dto);
	public void insertLike(MainDto dto);
	public void deleteLike(MainDto dto);
	//
	public List<MainDto> selectComment(MainDto dto);
	//
	public int getSequence();
	public void insertComment(MainDto dto);
}
