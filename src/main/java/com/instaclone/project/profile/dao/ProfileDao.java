package com.instaclone.project.profile.dao;

import java.util.List;

import com.instaclone.project.profile.dto.ProfileBoardDto;
import com.instaclone.project.profile.dto.ProfileDto;

public interface ProfileDao {
	public ProfileDto selectProfile(ProfileDto dto);
	public List<ProfileBoardDto> selectBoard(ProfileDto dto);
	public List<String> selectImg(ProfileBoardDto dto);
}
