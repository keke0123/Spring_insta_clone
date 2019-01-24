package com.instaclone.project.upload.dao;

import com.instaclone.project.upload.dto.UploadDataDto;
import com.instaclone.project.upload.dto.UploadDto;

public interface UploadDao {
	public int getSequence();
	public void insertImgBase64(UploadDto dto);
	public int getBoardSequence();
	public void insertBoardData(UploadDataDto dto);
	public void insertImgData(UploadDto dto);
	public void insertTagData(UploadDto dto);
}
