package com.tuding.spring.room;

import java.util.ArrayList;

public interface RoomDao {
	void insert(Room r);
	ArrayList<Room> selectByMember(String userid);
	void update(Room r);
	void delete(String roomname);
}
