package com.instaclone.project.upload.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.instaclone.project.upload.dto.UploadDataDto;
import com.instaclone.project.upload.dto.UploadDto;

@Repository
public class UploadDaoImpl implements UploadDao{
	@Autowired
	private SqlSession session;
	
	@Override
	public void insertImgBase64(UploadDto dto) {
		session.insert("upload.insertImgBase64", dto);
	}

	@Override
	public int getSequence() {
		int num=0;
		num=session.selectOne("upload.getSequence");
		return num;
	}

	@Override
	public int getBoardSequence() {
		return session.selectOne("upload.getBoardSequence");
		 
	}

	@Override
	public void insertBoardData(UploadDataDto dto) {
		session.insert("upload.insertBoardData", dto);
		
	}
	// image path 를 저장한다.
	@Override
	public void insertImgData(UploadDto dto) {
		session.insert("upload.insertImgData", dto);
		
	}
	// tag 내용을 저장한다.
	@Override
	public void insertTagData(UploadDto dto) {
		session.insert("upload.insertTagData", dto);
		
	}

}
