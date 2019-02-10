package com.tuding.spring.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuding.spring.member.TempPass;
import com.tuding.spring.sha256.SHA256;

@Controller
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private JavaMailSender mailSender;
	
	public void mailSend(Mail m) {

		System.out.println(m.getTomail());

		String setfrom = "balrog960712@gmail.com";         
		String tomail  = m.getTomail();    // 받는 사람 이메일
		String title   = m.getTitle();   // 제목
		StringBuffer content = new StringBuffer();

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper 
						= new MimeMessageHelper(message, true, "UTF-8");
			
			messageHelper.setFrom(setfrom);
			messageHelper.setTo(tomail);
			messageHelper.setSubject(title);
			if(title.contains("인증")) {
				content.append("다음 링크에 접속하여 인증을 진행하세요.")
						.append("<a href='http://127.0.0.1/spring/member/mailCheck?code=")
						.append(new SHA256().getSHA256(tomail))
						.append("' target='_blenk'>인증 하기</a>");
			}
			else {
				String tempPass = new TempPass().getTempPass();
				content.append("임시비밀번호입니다.")
						.append(tempPass)
						.append("로그인 후 비밀번호를 변경해주세요.");
			}
			messageHelper.setText(content.toString(), true);
			mailSender.send(message);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/sending")
	public String mailSending(Mail m) {
		
		mailSend(m);

		return "mail/mailSended";
	}
	
	@RequestMapping("findPass")
	public String findPassMail(Mail m) {
		
		mailSend(m);
		
		return "mail/findPassMailSended";
	}
}
