package com.tuding.spring.liked;

public class Liked {
	private int num;
	private String id;
	private int board_num;
	
	public Liked() {
	}
	
	public Liked(int num, String id, int board_num) {
		this.num = num;
		this.id = id;
		this.board_num = board_num;
	}
	
	public final int getNum() {
		return num;
	}
	public final void setNum(int num) {
		this.num = num;
	}
	
	public final String getId() {
		return id;
	}
	public final void setId(String id) {
		this.id = id;
	}
	
	public final int getBoard_num() {
		return board_num;
	}
	public final void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	
	@Override
	public String toString() {
		return "Liked [num=" + num + ", id=" + id + ", board_num=" + board_num + "]";
	}
}
