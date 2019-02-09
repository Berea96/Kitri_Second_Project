package com.tuding.spring.chatList;

public class ChatList {
	private String roomname;
	private String member_id;
	private String chief;
	
	public ChatList() {
	}
	
	public ChatList(String roomname, String member_id, String chief) {
		this.roomname = roomname;
		this.member_id = member_id;
		this.chief = chief;
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
	
	public final String getChief() {
		return chief;
	}
	public final void setChief(String chief) {
		this.chief = chief;
	}
	
	@Override
	public String toString() {
		return "ChatList [roomname=" + roomname + ", member_id=" + member_id + ", chief=" + chief + "]";
	}
}
