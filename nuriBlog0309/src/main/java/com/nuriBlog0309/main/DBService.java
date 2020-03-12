package com.nuriBlog0309.main;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import com.nuri.board.vo.BoardVO;

@Service
public class DBService extends SqlSessionDaoSupport{
	
	public void insertDB(VO vo) {
		System.out.println("===>DBService�� �̿��Ͽ� insertDB ����!!");
		getSqlSession().insert("mapper.board.insertBoard",vo);
	}
	
	public List<VO> listBoard(VO vo) {
		List<VO> getListBoard = getSqlSession().selectList("mapper.board.selectBoard", vo);
		return getListBoard;
	}
	
	public int getCount() {
		return getSqlSession().selectOne("mapper.board.getCount");
	}
	
}
