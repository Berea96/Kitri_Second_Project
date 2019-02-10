package com.tuding.spring.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuding.spring.sha256.SHA256;

@Controller
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping("/sending")
	public String mailSending(Mail m) {
		
		System.out.println(m.getTomail());

		String setfrom = "balrog960712@gmail.com";         
		String tomail  = m.getTomail();    // 받는 사람 이메일
		String title   = m.getTitle();   // 제목
		String content = "다음 링크에 접속하여 인증을 진행하세요." +
			"<a href='http://127.0.0.1/spring/member/mailCheck?code=" + new SHA256().getSHA256(tomail) + "'>인증하기</a>";    // 내용
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper 
						= new MimeMessageHelper(message, true, "UTF-8");
			
			messageHelper.setFrom(setfrom);
			messageHelper.setTo(tomail);
			messageHelper.setSubject(title);
			messageHelper.setText(new StringBuffer().append("다음 링크에 접속하여 인증을 진행하세요.")
							.append("<a href='http://127.0.0.1/spring/member/mailCheck?code=")
							.append(new SHA256().getSHA256(tomail))
							.append("' target='_blenk'>인증 하기</a>").toString(), true);
			
			mailSender.send(message);
		} catch(Exception e) {
			System.out.println(e);
		}

		return "mail/mailSended";
	}
}
