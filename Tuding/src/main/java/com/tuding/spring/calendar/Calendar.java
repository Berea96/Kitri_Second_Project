package com.tuding.spring.calendar;

public class Calendar {
	private int calnum;
	private String roomname;
	private String member_id;
	private String caldate1;
	private String caldate2;
	private String calevent;
	private int calcode;
	
	public Calendar() {
	}
	
	public Calendar(int calnum, String roomname, String member_id,
					String caldate1, String caldate2, String calevent,
					int calcode) {
		this.calnum = calnum;
		this.roomname = roomname;
		this.member_id = member_id;
		this.caldate1 = caldate1;
		this.caldate2 = caldate2;
		this.calevent = calevent;
		this.calcode = calcode;
	}
	
	public final int getCalnum() {
		return calnum;
	}
	public final void setCalnum(int calnum) {
		this.calnum = calnum;
	}
	
	public final String getRoomname() {
		return roomname;
	}
	public final void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	
	public final String getMember_id() {
		return member_id;
	}
	public final void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	public final String getCaldate1() {
		return caldate1;
	}
	public final void setCaldate1(String caldate1) {
		this.caldate1 = caldate1;
	}
	
	public final String getCaldate2() {
		return caldate2;
	}
	public final void setCaldate2(String caldate2) {
		this.caldate2 = caldate2;
	}
	
	public final String getCalevent() {
		return calevent;
	}
	public final void setCalevent(String calevent) {
		this.calevent = calevent;
	}
	
	public final int getCalcode() {
		return calcode;
	}
	public final void setCalcode(int calcode) {
		this.calcode = calcode;
	}
	
	@Override
	public String toString() {
		return "Calendar [calnum=" + calnum + ", roomname=" + roomname + ", member_id=" + member_id + ", calevent="
				+ calevent + ", calcode=" + calcode + "]";
	}
}
