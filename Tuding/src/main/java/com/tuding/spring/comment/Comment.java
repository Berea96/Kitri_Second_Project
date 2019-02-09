package com.tuding.spring.comment;

import java.sql.Date;

public class Comment {
	private int board_num;
	private String writer;
	private int num;
	private String content;
	private Date w_date;
	
	public Comment() {
	}
	
	public Comment(int board_num, String writer, int num, String content, Date w_date) {
		this.board_num = board_num;
		this.writer = writer;
		this.num = num;
		this.content = content;
		this.w_date = w_date;
	}
	
	public final int getBoard_num() {
		return board_num;
	}
	public final void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	
	public final String getWriter() {
		return writer;
	}
	public final void setWriter(String writer) {
		this.writer = writer;
	}
	
	public final int getNum() {
		return num;
	}
	public final void setNum(int num) {
		this.num = num;
	}
	
	public final String getContent() {
		return content;
	}
	public final void setContent(String content) {
		this.content = content;
	}
	
	public final Date getW_date() {
		return w_date;
	}
	public final void setW_date(Date w_date) {
		this.w_date = w_date;
	}
	
	@Override
	public String toString() {
		return "Comment [board_num=" + board_num + ", writer=" + writer + ", num=" + num + ", content=" + content
				+ ", w_date=" + w_date + "]";
	}
}
