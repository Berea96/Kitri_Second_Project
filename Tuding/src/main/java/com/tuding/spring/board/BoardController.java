package com.tuding.spring.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

//board Controller
@Controller
@RequestMapping("/board")
public class BoardController {

	//setter DI
	@Resource(name="boardService")
	private BoardService service;

	//board home
	@RequestMapping("/home")
	public ModelAndView goHome(@RequestParam("roomname")String roomname,
			ModelAndView mav) {

		mav.addObject("roomname", roomname);
		mav.setViewName("board/boardList");

		return mav;
	}

	//board Write Method
	@RequestMapping("/write")
	public String writeBoard(Board board) {

		service.writeBoard(board);

		return "redirect:/board/home?roomname=" + board.getRoomname();
	}

	//board Detail Method
	@ResponseBody
	@RequestMapping("/detail")
	public ModelAndView getByNum(@RequestParam("boardNum")int num,
						   ModelAndView mav) {
		
		Board board = service.getByNum(num);
		System.out.println(board.toString());
		
		mav.setViewName("json/boardDetail");
		mav.addObject("board", board);

		return mav;
	}

	//board listByWriter Method
	@RequestMapping("/listByWriter")
	public ModelAndView getByWriter(@RequestParam("roomname")String roomname,
									@RequestParam("writer")String writer,
									ModelAndView mav) {
		
		ArrayList<Board> boardList = service.getByWriter(roomname, writer);
		
		mav.setViewName("json/boardJson");
		mav.addObject("boardList", boardList);

		return mav;
	}

	//board listByCategory Method
	@RequestMapping("/listByCategory")
	public ModelAndView getByCategory(@RequestParam("roomname")String roomname,
									  @RequestParam("category")String category,
									  ModelAndView mav) {
		
		ArrayList<Board> boardList = service.getByCategory(roomname, category);
		
		mav.setViewName("json/boardJson");
		mav.addObject("boardList", boardList);
		
		return mav;
	}

	//board listAll Method
	@RequestMapping("/listAll")
	public ModelAndView getAll(@RequestParam("page")int page,
						   	   @RequestParam("roomname")String roomname,
							   HttpServletRequest req,
							   ModelAndView mav) {

		int maxBoardList = 10;
		int pageEnd = maxBoardList * page;
		int pageStart = pageEnd - 9;

		HttpSession session = req.getSession();

		session.setAttribute("backPage", page);
		int lastPage = (Integer)session.getAttribute("backPage");
		pageEnd = maxBoardList * lastPage;
		pageStart = pageEnd - 9;

		Map<String ,String> room = new HashMap<String, String>();
		Map<String ,Integer> pageStartAndEnd = new HashMap<String, Integer>();
		room.put("roomname", roomname);
		pageStartAndEnd.put("start", pageStart - 1);
		pageStartAndEnd.put("end", maxBoardList);

		ArrayList<Board> boardList = service.getAll(room, pageStartAndEnd);

		mav.setViewName("json/boardJson");
		mav.addObject("boardList", boardList);

		return mav;
	}

	//board ListCount Method
	@ResponseBody
	@RequestMapping("listCount")
	public String getListCount(@RequestParam("roomname")String roomname,
			HttpServletRequest req) {

		int maxCount = service.getListCount(roomname);
		HttpSession session = req.getSession(false);
		int lastPage = (Integer)session.getAttribute("backPage") == null ? 1: (Integer)session.getAttribute("backPage");
		
		System.out.println(lastPage);

		String returnValue = "{'maxCount':'" + maxCount + "', " +
				" 'currentPage' : '" + lastPage + "'}";

		return returnValue;
	}

	//board edit Method
	@ResponseBody
	@RequestMapping("/edit")
	public String editBoard(Board b) {

		service.editBoard(b);
		
		return "";
	}

	//board delete Method
	@ResponseBody
	@RequestMapping("/del")
	public String delBoard(@RequestParam("num")int num) {
		
		service.delBoard(num);

		return "";
	}
}
