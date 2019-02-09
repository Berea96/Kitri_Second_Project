package com.tuding.spring.chatList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tuding.spring.member.Member;

@Controller
@RequestMapping("/show")
public class ChatListController {
	
	@Resource(name="chatlistService")
	private ChatListService service;
	
	@RequestMapping("/calendar")
	public ModelAndView showCalendar(HttpServletRequest req, 
									 ModelAndView mav) {
		
		HttpSession session = req.getSession(false);
		
		String member_id = ((Member)session.getAttribute("loginInfo")).getId();
		
		ChatList c = service.getByRoomname(member_id);
		
		mav.setViewName("calendar/calendar");
		
		mav.addObject("roomname", c.getRoomname());
		mav.addObject("username", c.getMember_id());
		mav.addObject("chief", c.getChief());
		
		return mav;
	}
}
