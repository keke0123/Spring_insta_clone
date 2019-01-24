package com.instaclone.project.profile.dto;

import java.util.List;

public class ProfileBoardDto {
	// 아래 사진
	private int board_num;
	private List<String> imgs;   
	
	public ProfileBoardDto() {}

	public ProfileBoardDto(int board_num, List<String> imgs) {
		super();
		this.board_num = board_num;
		this.imgs = imgs;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}

	
	
}
