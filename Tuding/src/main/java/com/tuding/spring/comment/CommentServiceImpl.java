package com.tuding.spring.comment;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component("commentService")
public class CommentServiceImpl implements CommentService {
	
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	private CommentDao dao;
	
	@Override
	public void writeComment(Comment c) {
		dao = sqlSession.getMapper(CommentDao.class);
		dao.insert(c);
	}

	@Override
	public ArrayList<Comment> getByBoardNum(int board_num) {
		dao = sqlSession.getMapper(CommentDao.class);
		return dao.selectByBoardNum(board_num);
	}

	@Override
	public ArrayList<Comment> getByWriter(String writer) {
		dao = sqlSession.getMapper(CommentDao.class);
		return dao.selectByWriter(writer);
	}

	@Override
	public void editComment(Comment c) {
		dao = sqlSession.getMapper(CommentDao.class);
		dao.update(c);
	}

	@Override
	public void delComment(int num) {
		dao = sqlSession.getMapper(CommentDao.class);
		dao.delete(num);
	}
}
