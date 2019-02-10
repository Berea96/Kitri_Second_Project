package com.tuding.spring.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping("/mail/sendForm")
	public String mailSendForm(Mail m) {

		return "mail/mailSendForm";
	}

	@RequestMapping("/sending")
	public String mailSending(Mail m) {

		String setfrom = "아이디@gmail.com";         
		String tomail  = m.getTomamil();    // 받는 사람 이메일
		String title   = m.getTitle();   // 제목
		String content = m.getContent();    // 내용
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper 
						= new MimeMessageHelper(message, true, "UTF-8");
			
			messageHelper.setFrom(setfrom);
			messageHelper.setTo(tomail);
			messageHelper.setSubject(title);
			messageHelper.setText(content);
			
			mailSender.send(message);
		} catch(Exception e) {
			System.out.println(e);
		}

		return "redirect:/member/home";
	}
}
