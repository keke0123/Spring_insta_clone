package com.instaclone.project.profile.service;

import com.instaclone.project.profile.dto.ProfileDto;
import com.instaclone.project.response.dto.ResponseDto;

public interface ProfileService {
	public ResponseDto profileData(ProfileDto dto);
}
