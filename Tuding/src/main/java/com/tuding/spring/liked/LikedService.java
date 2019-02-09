package com.tuding.spring.liked;

import java.util.ArrayList;

public interface LikedService {
	void addLiked(Liked l);
	ArrayList<Liked> getByBoardNum(Liked l);
	ArrayList<Liked> getByMember(String id);
	void delLiked(Liked l);
}
