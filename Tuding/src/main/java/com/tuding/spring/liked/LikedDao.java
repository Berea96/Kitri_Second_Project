package com.tuding.spring.liked;

import java.util.ArrayList;

public interface LikedDao {
	void insert(Liked l);
	ArrayList<Liked> selectByBoardNum(Liked l);
	ArrayList<Liked> selectByMember(String id);
	void delete(Liked l);
}
