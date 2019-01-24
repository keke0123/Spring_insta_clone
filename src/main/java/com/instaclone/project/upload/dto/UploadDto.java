package com.instaclone.project.upload.dto;

import java.util.ArrayList;

public class UploadDto {
	// test 용
	private int num;
	private String imgBase64;
	//private String savePath;
	// img 저장용
	private int board_num;
	private String savePath;
	// tag 저장용
	private String tag;
	
	public UploadDto(){}

	public UploadDto(int num, String imgBase64, int board_num, String savePath, String tag) {
		super();
		this.num = num;
		this.imgBase64 = imgBase64;
		this.board_num = board_num;
		this.savePath = savePath;
		this.tag = tag;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getImgBase64() {
		return imgBase64;
	}

	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	
	
}
