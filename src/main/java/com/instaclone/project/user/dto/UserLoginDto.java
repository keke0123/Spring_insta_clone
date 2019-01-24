package com.instaclone.project.user.dto;

public class UserLoginDto {
	private String id;
	private String token;
	private int ableTime;
	
	public UserLoginDto() {}

	public UserLoginDto(String id, String token, int ableTime) {
		super();
		this.id = id;
		this.token = token;
		this.ableTime = ableTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getAbleTime() {
		return ableTime;
	}

	public void setAbleTime(int ableTime) {
		this.ableTime = ableTime;
	}
	
	
}
