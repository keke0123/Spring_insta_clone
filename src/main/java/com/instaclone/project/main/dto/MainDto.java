package com.instaclone.project.main.dto;

import java.util.List;

public class MainDto {
	// clinet 에서 가져올 내용
	private String id;
	private int rNum;
	// board 내용
	private int board_num;
	private String thumbnail;
	private String writer;
	private String regdate;
	private String content;
	private String isLike;
	private int count_like;
	private int count_comment;
	// for 문을 돌면서 받아올 내용
	private List<String> imgs;
	private List<String> tags;
	
	// comment
	private int comment_num;
	private int comment_group_num; // comment_num 과 같을때 부모 댓글
	private String comment_content;
	private String comment_writer;
	private String target;

	public MainDto() {}

	public MainDto(String id, int rNum, int board_num, String thumbnail, String writer, String regdate, String content,
			String isLike, int count_like, int count_comment, List<String> imgs, List<String> tags, int comment_num,
			int comment_group_num, String comment_content, String comment_writer, String target) {
		super();
		this.id = id;
		this.rNum = rNum;
		this.board_num = board_num;
		this.thumbnail = thumbnail;
		this.writer = writer;
		this.regdate = regdate;
		this.content = content;
		this.isLike = isLike;
		this.count_like = count_like;
		this.count_comment = count_comment;
		this.imgs = imgs;
		this.tags = tags;
		this.comment_num = comment_num;
		this.comment_group_num = comment_group_num;
		this.comment_content = comment_content;
		this.comment_writer = comment_writer;
		this.target = target;
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

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsLike() {
		return isLike;
	}

	public void setIsLike(String isLike) {
		this.isLike = isLike;
	}

	public int getCount_like() {
		return count_like;
	}

	public void setCount_like(int count_like) {
		this.count_like = count_like;
	}

	public int getCount_comment() {
		return count_comment;
	}

	public void setCount_comment(int count_comment) {
		this.count_comment = count_comment;
	}

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public int getComment_num() {
		return comment_num;
	}

	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}

	public int getComment_group_num() {
		return comment_group_num;
	}

	public void setComment_group_num(int comment_group_num) {
		this.comment_group_num = comment_group_num;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public String getComment_writer() {
		return comment_writer;
	}

	public void setComment_writer(String comment_writer) {
		this.comment_writer = comment_writer;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	
	
	
	
}
