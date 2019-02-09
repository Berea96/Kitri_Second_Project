package com.tuding.spring.comment;

import java.util.ArrayList;

public interface CommentService {
	void writeComment(Comment c);
	ArrayList<Comment> getByBoardNum(int board_num);
	ArrayList<Comment> getByWriter(String writer);
	void editComment(Comment c);
	void delComment(int num);
}
