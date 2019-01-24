package com.instaclone.project.main.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.instaclone.project.main.dto.MainDto;

@Repository
public class MainDaoImpl implements MainDao{
	@Autowired
	private SqlSession session;

	@Override
	public List<MainDto> selectBoard(MainDto dto) {
		return session.selectList("main.selectBoard", dto);
	}

	@Override
	public MainDto selectBoardOther(MainDto dto) {
		return session.selectOne("main.selectBoardOther", dto);
	}

	@Override
	public List<String> selectImgList(MainDto dto) {
		return session.selectList("main.selectImgList", dto);
	}

	@Override
	public List<String> selectTagList(MainDto dto) {
		return session.selectList("main.selectTagList", dto);
	}
	// like 기능 관련
	@Override
	public String selectIsLike(MainDto dto) {
		return session.selectOne("main.selectIsLike", dto);
	}
	@Override
	public void insertLike(MainDto dto) {
		session.insert("main.insertLike", dto);
	}
	@Override
	public void deleteLike(MainDto dto) {
		session.delete("main.deleteLike", dto);
	}
	// comment 데이타 얻어오기
	@Override
	public List<MainDto> selectComment(MainDto dto) {
		return session.selectList("main.selectComment", dto);
	}
	// comment에 넣을 seq 생성해서 가져오기
	@Override
	public int getSequence() {
		return session.selectOne("main.getSequence");
	}

	@Override
	public void insertComment(MainDto dto) {
		session.insert("main.insertComment", dto);
	}
}
