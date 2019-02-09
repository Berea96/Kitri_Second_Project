package com.tuding.spring.comment;

import java.util.ArrayList;

public interface CommentDao {
	void insert(Comment c);
	ArrayList<Comment> selectByBoardNum(int board_num);
	ArrayList<Comment> selectByWriter(String writer);
	void update(Comment c);
	void delete(int num);
}
