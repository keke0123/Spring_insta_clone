package com.instaclone.project.user.dto;

public class UserPasswordDto {
	private String email;
	private int ableTime;
	private int passNum;
	
	public UserPasswordDto() {}

	public UserPasswordDto(String email, int ableTime, int passNum) {
		super();
		this.email = email;
		this.ableTime = ableTime;
		this.passNum = passNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAbleTime() {
		return ableTime;
	}

	public void setAbleTime(int ableTime) {
		this.ableTime = ableTime;
	}

	public int getPassNum() {
		return passNum;
	}

	public void setPassNum(int passNum) {
		this.passNum = passNum;
	}
	
	
}
