package com.tuding.spring.board;

import java.util.ArrayList;
import java.util.Map;

//board Dao interface
public interface BoardDao {
	//board insert
	void insert(Board b);
	//board selectByNum
	Board selectByNum(int num);
	//board updateRead
	void updateRead(int num);
	//board selectByWriter
	ArrayList<Board> selectByWriter(String roomname, String writer);
	//board selectByCategory
	ArrayList<Board> selectByCategory(String roomname, String category);
	//board selectAll
	ArrayList<Board> selectAll(Map<String, String> roomname, Map<String, Integer> pages);
	//board selectListCount
	int selectListCount(String roomname);
	//board updateLike
	void updateLike(int num, int count);
	//board update
	void update(Board b);
	//board delete
	void delete(int num);
}
