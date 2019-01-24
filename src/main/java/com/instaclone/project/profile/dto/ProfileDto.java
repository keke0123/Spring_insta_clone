package com.instaclone.project.profile.dto;

import java.util.ArrayList;
import java.util.List;

public class ProfileDto {
	// 
	private String id;
	private int rNum;
	// 받아올 내용
	private String thumbnail;
	private int count_board;
	private int count_follow;
	private int count_follower;
	private String isFollow;
	//
	private List<ProfileBoardDto> boardData;

	public ProfileDto() {}

	public ProfileDto(String id, int rNum, String thumbnail, int count_board, int count_follow, int count_follower,
			String isFollow, List<ProfileBoardDto> boardData) {
		super();
		this.id = id;
		this.rNum = rNum;
		this.thumbnail = thumbnail;
		this.count_board = count_board;
		this.count_follow = count_follow;
		this.count_follower = count_follower;
		this.isFollow = isFollow;
		this.boardData = boardData;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getrNum() {
		return rNum;
	}

	public void setrNum(int rNum) {
		this.rNum = rNum;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getCount_board() {
		return count_board;
	}

	public void setCount_board(int count_board) {
		this.count_board = count_board;
	}

	public int getCount_follow() {
		return count_follow;
	}

	public void setCount_follow(int count_follow) {
		this.count_follow = count_follow;
	}

	public int getCount_follower() {
		return count_follower;
	}

	public void setCount_follower(int count_follower) {
		this.count_follower = count_follower;
	}

	public String getIsFollow() {
		return isFollow;
	}

	public void setIsFollow(String isFollow) {
		this.isFollow = isFollow;
	}

	public List<ProfileBoardDto> getBoardData() {
		return boardData;
	}

	public void setBoardData(List<ProfileBoardDto> boardData) {
		this.boardData = boardData;
	}




}