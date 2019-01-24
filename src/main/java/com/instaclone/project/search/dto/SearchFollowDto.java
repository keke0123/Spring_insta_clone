package com.instaclone.project.search.dto;

public class SearchFollowDto {
	private String id;
	private String myId;
	
	public SearchFollowDto() {}

	public SearchFollowDto(String id, String myId) {
		super();
		this.id = id;
		this.myId = myId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMyId() {
		return myId;
	}

	public void setMyId(String myId) {
		this.myId = myId;
	}
	
	
}
