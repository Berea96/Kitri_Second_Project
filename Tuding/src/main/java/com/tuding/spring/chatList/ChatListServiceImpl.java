package com.tuding.spring.chatList;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component("chatlistService")
public class ChatListServiceImpl implements ChatListService {

	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	private ChatListDao dao;
	
	@Override
	public ChatList getByRoomname(String member_id) {
		dao = sqlSession.getMapper(ChatListDao.class);
		return dao.selectByRoomname(member_id);
	}
}
