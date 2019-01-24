package com.instaclone.project.response.dto;

// 응답 객체
public class ResponseDto {
	private String result;
	private Object data;
	private String message;
	private boolean isLogined;
	private String token;
	
	public ResponseDto() {
		
	}

	public ResponseDto(String result, Object data, String message, boolean isLogined, String token) {
		super();
		this.result = result;
		this.data = data;
		this.message = message;
		this.isLogined = isLogined;
		this.token = token;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isLogined() {
		return isLogined;
	}

	public void setLogined(boolean isLogined) {
		this.isLogined = isLogined;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}





	
}
