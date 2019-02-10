package com.tuding.spring.mail;

public class Mail {
	private String tomamil;
	private String title;
	private String content;
	
	public Mail() {
	}
	
	public Mail(String tomamil, String title, String content) {
		this.tomamil = tomamil;
		this.title = title;
		this.content = content;
	}
	
	public final String getTomamil() {
		return tomamil;
	}
	public final void setTomamil(String tomamil) {
		this.tomamil = tomamil;
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
		return "Mail [tomamil=" + tomamil + ", title=" + title + ", content=" + content + "]";
	}
}
