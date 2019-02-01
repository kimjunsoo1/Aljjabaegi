package com.junshae.security.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.junshae.security.board.bean.BoardDTO;


@Repository	// @Companent와 동일
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 저장
	public int boardWrite(BoardDTO boardDTO) {		
		return sqlSession.insert("mybatis.boardMapper.boardWrite", boardDTO);
	}
	// 목록보기
	public List<BoardDTO> boardList(int startNum, int endNum) {
		System.out.println("sqlSession = " + sqlSession);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		return sqlSession.selectList("mybatis.boardMapper.boardList", map);		
	}
	// 상세보기
	public BoardDTO boardView(int seq) {		
		return sqlSession.selectOne("mybatis.boardMapper.boardView", seq);
	}
	// 조회수 증가
	public void updateHit(int seq) {
		sqlSession.update("mybatis.boardMapper.updateHit", seq);	
	}
	// 전체 목록수 
	public int getTotalA() {		
		return sqlSession.selectOne("mybatis.boardMapper.getTotalA");
	}
	// 삭제
	public int boardDelete(int seq) {		
		return sqlSession.delete("mybatis.boardMapper.boardDelete", seq);
	}
}







