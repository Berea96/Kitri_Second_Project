package com.tuding.spring.liked;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/liked")
public class LikedController {

	@Resource(name="likedService")
	private LikedService service;
	
	@ResponseBody
	@RequestMapping("/add")
	public String addLiked(Liked l) {
		
		service.addLiked(l);
		
		return "";
	}
	
	@RequestMapping("/likeBoardNum")
	public void getByBoardNum(Liked l) {
		ArrayList<Liked> likedList = service.getByBoardNum(l);
	}
	
	@RequestMapping("/listByMember")
	public void getByMember(@RequestParam("id")String id) {
		ArrayList<Liked> likedList = service.getByMember(id);
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public String delLiked(Liked l) {
		service.delLiked(l);
		
		return "";
	}
}
