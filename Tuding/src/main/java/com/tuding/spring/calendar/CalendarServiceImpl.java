package com.tuding.spring.calendar;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component("calendarService")
public class CalendarServiceImpl implements CalendarService {
	
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	private CalendarDao dao;

	@Override
	public void addEvent(Calendar c) {
		dao = sqlSession.getMapper(CalendarDao.class);
		dao.insert(c);
	}

	@Override
	public ArrayList<Calendar> getAll(String roomname) {
		dao = sqlSession.getMapper(CalendarDao.class);
		return dao.selectAll(roomname);
	}

	@Override
	public void editEvent(Calendar c) {
		dao = sqlSession.getMapper(CalendarDao.class);
		dao.update(c);
	}

	@Override
	public void delEvent(int calnum) {
		dao = sqlSession.getMapper(CalendarDao.class);
		dao.delete(calnum);
	}
}
