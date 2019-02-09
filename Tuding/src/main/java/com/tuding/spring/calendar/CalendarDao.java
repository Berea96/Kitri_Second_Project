package com.tuding.spring.calendar;

import java.util.ArrayList;

public interface CalendarDao {
	void insert(Calendar c);
	ArrayList<Calendar> selectAll(String roomname);
	void update(Calendar c);
	void delete(int calnum);
}