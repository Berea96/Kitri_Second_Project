package com.tuding.spring.member;

import java.sql.Date;

public class Member {
	private String id;
	private String pwd;
	private String temp_pw;
	private String email;
	private int checked;
	private Date w_date;
	private String nickname;
	
	public Member() {
	}
	
	public Member(String id, String pwd, String temp_pw, String email, int checked, Date w_date, String nickname) {
		this.id = id;
		this.pwd = pwd;
		this.temp_pw = temp_pw;
		this.email = email;
		this.checked = checked;
		this.w_date = w_date;
		this.nickname = nickname;
	}

	public final String getId() {
		return id;
	}
	public final void setId(String id) {
		this.id = id;
	}
	
	public final String getPwd() {
		return pwd;
	}
	public final void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public final String getTemp_pw() {
		return temp_pw;
	}
	public final void setTemp_pw(String temp_pw) {
		this.temp_pw = temp_pw;
	}
	
	public final String getEmail() {
		return email;
	}
	public final void setEmail(String email) {
		this.email = email;
	}
	
	public final int getChecked() {
		return checked;
	}
	public final void setChecked(int checked) {
		this.checked = checked;
	}
	
	public final Date getW_date() {
		return w_date;
	}
	public final void setW_date(Date w_date) {
		this.w_date = w_date;
	}
	
	public final String getNickname() {
		return nickname;
	}
	public final void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", pwd=" + pwd + ", temp_pw=" + temp_pw + ", email=" + email + ", checked="
				+ checked + ", w_date=" + w_date + ", nickname=" + nickname + "]";
	}
}