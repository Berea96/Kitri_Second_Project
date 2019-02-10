package com.tuding.spring.mail;

public class Mail {
	private String tomail;
	private String title;
	private String content;
	
	public Mail() {
	}
	
	public Mail(String tomail, String title, String content) {
		this.tomail = tomail;
		this.title = title;
		this.content = content;
	}
	
	public final String getTomail() {
		return tomail;
	}

	public final void setTomail(String tomail) {
		this.tomail = tomail;
	}

	public final String getTitle() {
		return title;
	}
	public final void setTitle(String title) {
		this.title = title;
	}
	
	public final String getContent() {
		return content;
	}
	public final void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "Mail [tomail=" + tomail + ", title=" + title + ", content=" + content + "]";
	}
}
