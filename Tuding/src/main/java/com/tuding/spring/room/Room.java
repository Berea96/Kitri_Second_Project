package com.tuding.spring.room;

import java.sql.Date;

public class Room {
	private String roomname;
	private String roompass;
	private String userid;
	private Date indata;
	
	public Room() {
	}
	
	public Room(String roomname, String roompass, String userid, Date indata) {
		this.roomname = roomname;
		this.roompass = roompass;
		this.userid = userid;
		this.indata = indata;
	}

	public final String getRoomname() {
		return roomname;
	}
	public final void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public final String getRoompass() {
		return roompass;
	}
	public final void setRoompass(String roompass) {
		this.roompass = roompass;
	}

	public final String getUserid() {
		return userid;
	}
	public final void setUserid(String userid) {
		this.userid = userid;
	}

	public final Date getIndata() {
		return indata;
	}
	public final void setIndata(Date indata) {
		this.indata = indata;
	}

	@Override
	public String toString() {
		return "Room [roomname=" + roomname + ", roompass=" + roompass + ", userid=" + userid + ", indata=" + indata
				+ "]";
	}
}
