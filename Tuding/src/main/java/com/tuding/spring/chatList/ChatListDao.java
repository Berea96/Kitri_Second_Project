package com.tuding.spring.chatList;

public interface ChatListDao {
	ChatList selectByRoomname(String member_id);
}
