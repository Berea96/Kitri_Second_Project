package com.tuding.spring.room;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/room")
public class RoomController {

	@Resource(name="roomService")
	private RoomService service;
	
	@RequestMapping("/create")
	public void createRoom(Room r) {
		service.createRoom(r);
	}
	
	@RequestMapping("/getByMember")
	public ModelAndView getByMember(@RequestParam("id")String userid,
									ModelAndView mav) {
		
		System.out.println(userid);
		
		ArrayList<Room> list = service.getByMember(userid);
		
		mav.setViewName("json/roomJson");
		mav.addObject("list", list);
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/edit")
	public String editRoom(Room r) {
		service.editRoom(r);
		
		return "{'result':'true'}";
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String delRoom(@RequestParam("roomname")String roomname) {
		service.delRoom(roomname);
		
		return "{'result':'true'}";
	}
}
