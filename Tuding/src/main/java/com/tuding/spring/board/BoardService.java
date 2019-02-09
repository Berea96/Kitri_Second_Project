package com.tuding.spring.board;

import java.util.ArrayList;
import java.util.Map;

//board Service interface
public interface BoardService {
	//board writeBoard
	void writeBoard(Board b);
	//board getByNum
	Board getByNum(int num);
	//board getByWriter
	ArrayList<Board> getByWriter(String roomname, String writer);
	//board getByCategory
	ArrayList<Board> getByCategory(String roomname, String category);
	//board getALll
	ArrayList<Board> getAll(Map<String, String> roomname, Map<String, Integer> pages);
	//board getCListCount
	int getListCount(String roomname);
	//board likeBoard
	void likeBoard(int num, int count);
	//board editBoard
	void editBoard(Board b);
	//board delBoard
	void delBoard(int num);
}
