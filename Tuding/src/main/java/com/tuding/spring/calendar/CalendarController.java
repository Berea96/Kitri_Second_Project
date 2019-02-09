package com.tuding.spring.calendar;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin(origins="*")
@Controller
@RequestMapping("/calendar")
public class CalendarController {
	
	@Resource(name="calendarService")
	private CalendarService service;
	
	@ResponseBody
	@RequestMapping("/addEvent")
	public String addEvent(Calendar c) {
		System.out.println("도착");
		System.out.println(c.getCaldate1());
		System.out.println(c.getCaldate2());
		System.out.println(c.getCalevent());
		System.out.println(c.getMember_id());
		System.out.println(c.getRoomname());
		service.addEvent(c);
		return c.getCalnum() + "";
	}
	
	@RequestMapping("/removeEvent")
	public void removeEvent(@RequestParam("calnum")int calnum) {
		service.delEvent(calnum);
	}
	
	@RequestMapping("/modifyEvent")
	public void modifyEvent(Calendar c) {
		service.editEvent(c);
	}
	
	@RequestMapping("/getAllEvent")
	public ModelAndView getAllEvent(@RequestParam("roomname")String roomname,
									ModelAndView mav) {
		
		ArrayList<Calendar> list = service.getAll(roomname);
		
		mav.addObject("list", list);
		mav.setViewName("json/calendarJson");
		
		return mav;
	}
}
