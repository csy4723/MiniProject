package com.uni.miniProject.model.vo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Write {// 글 기본 토대가 되는 클래스, 이거 그대로 쓰셔도 좋고 딴 클래스 만드셔서 상속해서 오버라이딩 해도 좋고.. -서영
	private String title;
	private String content; 
	private Calendar date;
	private String userId;
	 
	public Write() {
		// TODO Auto-generated constructor stub
	}
 
	public Write(String title, String content, Calendar date, String userId) {
		super();
		this.title = title;
		this.content = content;
		this.date = date;
		this.userId = userId;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sf.format(date.getTime());
		return "Write [제목 : " + title + ", 내용 : " + content + ", 작성 날짜 : " + today + ", 작성자 :" + userId + "]";
	}
	
	public String information() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sf.format(date.getTime());
		return "제목: " + title + " ||내용: " + content + " ||날짜: " + today + " ||작성자: " + userId;
	}
	
	public String toStrFile() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sf.format(date.getTime());
		return title + "," + content + "," + today + "," + userId ;
	}

}
