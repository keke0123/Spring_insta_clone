package com.instaclone.project.upload.service;

import javax.servlet.http.HttpServletRequest;

import com.instaclone.project.response.dto.ResponseDto;
import com.instaclone.project.upload.dto.UploadDataDto;
import com.instaclone.project.upload.dto.UploadDto;

public interface UploadService {
	public ResponseDto saveImgBase64(HttpServletRequest request, UploadDto dto);
	public ResponseDto saveData(UploadDataDto dto, HttpServletRequest request);
	public UploadDataDto saveImg(UploadDataDto dto, HttpServletRequest request);
}
