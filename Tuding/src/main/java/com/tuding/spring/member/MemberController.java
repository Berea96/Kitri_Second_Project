package com.tuding.spring.member;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tuding.spring.sha256.SHA256;
import com.tuding.spring.sha256.SHAHash256;

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
	public String joinMember(Member m, Model model) {
		
		System.out.println(m.getId());
		System.out.println(m.getPwd());
		System.out.println(m.getEmail());
		System.out.println(m.getNickname());
		m.setTemp_pw("0");
		m.setW_date(null);
		service.joinMember(m);
		
		model.addAttribute("member", m);
		
		return "mail/mailSendForm";
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
				if(member.getChecked() == 0) {
					result = "{'result':'unChecked'}";
				}
				else {
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
	
	@RequestMapping("/findPass")
	public String findPassMember(Member m) {
		
		String result = "";
		String tempPass = new TempPass().getTempPass();
		
		Member getMember = service.getMember(m.getId());
		if(getMember != null) {
			m.setPwd(tempPass);
			service.editMember(m);
			String host = "http://127.0.0.1:80/InfantCareCenter/";
			String from = "balrog960712@gmail.com";
			String to = getMember.getEmail(); // 유저의 가입 이메일 가져오기.
			
			result = "{'result':'success'}";
		}
		else {
			result = "{'result':'fail'}";
		}
		
		return result;
	}
	
	@RequestMapping("/mailCheck")
	public String mailCheckMember(@RequestParam("code")String code,
								  Model model) {
		
		ArrayList<Member> memberList = service.getAll();
		
		String id = "";
		
		model.addAttribute("result", "fail");
		for(Member m : memberList) {
			String mEmail = new SHA256().getSHA256(m.getEmail());
			
			if(code.equals(mEmail)) {
				id = m.getId();
				service.checkedEmail(id);
				model.addAttribute("result", "success");
			}
		}
		
		return "mail/result";
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
