package com.instaclone.project.upload.dto;

import java.util.ArrayList;

public class UploadDataDto {
	// img upload 용
	private int board_num;
	private String writer;
	// 전달받는 데이타
	private ArrayList<String> uploadImgs;
	private ArrayList<String> tags;
	private String content;
	// 전달받은 내용을 바탕으로 가공된 데이타
	private ArrayList<String> savePaths;
		
	public UploadDataDto() {}

	public UploadDataDto(int board_num, String writer, ArrayList<String> uploadImgs, ArrayList<String> tags,
			String content, ArrayList<String> savePaths) {
		super();
		this.board_num = board_num;
		this.writer = writer;
		this.uploadImgs = uploadImgs;
		this.tags = tags;
		this.content = content;
		this.savePaths = savePaths;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public ArrayList<String> getUploadImgs() {
		return uploadImgs;
	}

	public void setUploadImgs(ArrayList<String> uploadImgs) {
		this.uploadImgs = uploadImgs;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArrayList<String> getSavePaths() {
		return savePaths;
	}

	public void setSavePaths(ArrayList<String> savePaths) {
		this.savePaths = savePaths;
	}


	
	
	
}
