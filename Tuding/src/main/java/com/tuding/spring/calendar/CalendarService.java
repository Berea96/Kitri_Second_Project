package com.tuding.spring.calendar;

import java.util.ArrayList;

public interface CalendarService {
	void addEvent(Calendar c);
	ArrayList<Calendar> getAll(String roomname);
	void editEvent(Calendar c);
	void delEvent(int calnum);
}
