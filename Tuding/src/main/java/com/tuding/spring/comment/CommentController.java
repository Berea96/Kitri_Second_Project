package com.tuding.spring.comment;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Resource(name="commentService")
	private CommentService service;
	
	@ResponseBody
	@RequestMapping("/write")
	public String writeComment(Comment c) {
		service.writeComment(c);
		
		return "";
	}
	
	@RequestMapping("/listByBoardNum")
	public ModelAndView getByBoardNum(@RequestParam("num")int board_num,
									  ModelAndView mav) {
		
		ArrayList<Comment> commentList = service.getByBoardNum(board_num);
		
		mav.addObject("commentList", commentList);
		mav.setViewName("json/commentJson");
		
		return mav;
	}
	
	@RequestMapping("/listByWriter")
	public ModelAndView getByWriter(@RequestParam("writer")String writer,
									ModelAndView mav) {
		
		ArrayList<Comment> commentList = service.getByWriter(writer);
		
		mav.addObject("commentList", commentList);
		mav.setViewName("json/commentJson");
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/edit")
	public String editComment(Comment c) {
		
		service.editComment(c);
		
		return "";
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public String delCommnet(@RequestParam("num")int num) {
		service.delComment(num);
		
		return "";
	}
}
