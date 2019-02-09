package com.tuding.spring.room;

import java.util.ArrayList;

public interface RoomService {
	void createRoom(Room r);
	ArrayList<Room> getByMember(String userid);
	void editRoom(Room r);
	void delRoom(String roomname);
}
