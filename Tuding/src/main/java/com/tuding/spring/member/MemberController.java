package com.tuding.spring.member;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Resource(name="memberService")
	private MemberService service;
	
	@RequestMapping("/home")
	public String goHome() {
		return "main/index";
	}
	
	@RequestMapping("/goNode")
	public String goNode() {
		return "member/toNode";
	}
	
	@RequestMapping("/join")
	public String joinMember(Member m) {
		
		System.out.println(m.getId());
		System.out.println(m.getPwd());
		System.out.println(m.getEmail());
		System.out.println(m.getNickname());
		m.setTemp_pw("0");
		m.setW_date(null);
		service.joinMember(m);
		
		return "redirect:/member/home";
	}
	
	@RequestMapping("/info")
	public String infoMember(HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		
		String result = "member/myPage";

		if(session == null) return "redirect:/member/home";
		
		Member member = (Member)session.getAttribute("loginInfo");
		
		if(member == null) result = "redirect:/member/home";
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/checkId")
	public String checkId(@RequestParam("id")String id) {
	
		String result = "";
		
		System.out.println(id);
		
		Member member = service.getMember(id);
		
		if(member == null) result = "{'result':'no'}";
		else result = "{'result':'yes'}";
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/login")
	public String loginMember(@RequestParam("loginId")String id,
						@RequestParam("loginPw")String pw,
						HttpServletRequest req) {
		
		Member member =service.getMember(id);
		HttpSession session = null;
		
		String result = "";
		
		if(member == null) {
			result = "{'result':'fail'}";
		}
		else {
			if(member.getPwd().equals(pw)) {
				
				session = req.getSession();
				SHAHash256 hash = new SHAHash256();
				
				try {
					session.setAttribute("hash", hash.sha256(member.getPwd()+member.getEmail()));
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				
				session.setAttribute("loginInfo", member);
				result = "{'result':'success'}";
			}
			else result = "{'result':'fail'}";
		}
		
		return result;
	}
	
	@RequestMapping("/edit")
	public String editMember(Member m) {
		service.editMember(m);
		
		return "redirect:/member/info";
	}
	
	@RequestMapping("/logout")
	public String logoutMember(HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		
		session.removeAttribute("loginInfo");
		session.invalidate();
		
		return "redirect:/member/home";
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String delMember(@RequestParam("id")String id,
							HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		
		session.removeAttribute("loginInfo");
		session.invalidate();
		
		service.delMember(id);
		
		return "{'result':'true'}";
	}
}
