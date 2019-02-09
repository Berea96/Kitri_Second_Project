package com.tuding.spring.board;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

//Bean name
@Component("boardService")
public class BoardServiceImpl implements BoardService {
	
	//setter DI
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	private BoardDao dao;

	@Override
	//board writeBoard
	public void writeBoard(Board b) {
		dao = sqlSession.getMapper(BoardDao.class);
		dao.insert(b);
	}

	@Override
	//board getByNum
	public Board getByNum(int num) {
		dao = sqlSession.getMapper(BoardDao.class);
		dao.updateRead(num);
		return dao.selectByNum(num);
	}

	@Override
	//board getByWriter
	public ArrayList<Board> getByWriter(String roomname, String writer) {
		return dao.selectByWriter(roomname, writer);
	}

	@Override
	//board getByCategory
	public ArrayList<Board> getByCategory(String roomname, String category) {
		dao = sqlSession.getMapper(BoardDao.class);
		return dao.selectByCategory(roomname, category);
	}

	@Override
	//board getAll
	public ArrayList<Board> getAll(Map<String, String> roomname, Map<String, Integer> pages) {
		dao = sqlSession.getMapper(BoardDao.class);
		return dao.selectAll(roomname, pages);
	}
	
	@Override
	//board ListCount
	public int getListCount(String roomname) {
		dao = sqlSession.getMapper(BoardDao.class);
		return dao.selectListCount(roomname);
	}

	@Override
	//board likeBoard
	public void likeBoard(int num, int count) {
		dao = sqlSession.getMapper(BoardDao.class);
		dao.updateLike(num, count);
	}

	@Override
	//board editBoard
	public void editBoard(Board b) {
		dao = sqlSession.getMapper(BoardDao.class);
		dao.update(b);
	}

	@Override
	//board delBoard
	public void delBoard(int num) {
		dao = sqlSession.getMapper(BoardDao.class);
		dao.delete(num);
	}
}
