package com.tuding.spring.room;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component("roomService")
public class RoomServiceImpl implements RoomService {

	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	private RoomDao dao;
	
	@Override
	public void createRoom(Room r) {
		dao = sqlSession.getMapper(RoomDao.class);
		dao.insert(r);
	}

	@Override
	public ArrayList<Room> getByMember(String userid) {
		dao = sqlSession.getMapper(RoomDao.class);
		return dao.selectByMember(userid);
	}

	@Override
	public void editRoom(Room r) {
		dao = sqlSession.getMapper(RoomDao.class);
		dao.update(r);
	}

	@Override
	public void delRoom(String roomname) {
		dao = sqlSession.getMapper(RoomDao.class);
		dao.delete(roomname);
	}
}
