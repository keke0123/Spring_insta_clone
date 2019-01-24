package com.instaclone.project.search.dto;

public class SearchDto {
	private String search;
	private String searchMethod;
	private String searchResult;
	private String thumbnail;
	
	public SearchDto() {}

	public SearchDto(String search, String searchMethod, String searchResult, String thumbnail) {
		super();
		this.search = search;
		this.searchMethod = searchMethod;
		this.searchResult = searchResult;
		this.thumbnail = thumbnail;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSearchMethod() {
		return searchMethod;
	}

	public void setSearchMethod(String searchMethod) {
		this.searchMethod = searchMethod;
	}

	public String getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(String searchResult) {
		this.searchResult = searchResult;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}


	
	
}
