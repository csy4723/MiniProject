package com.uni.miniProject.model.vo;

import com.uni.miniProject.controller.CampingController;

public class Member {

	private String userId;
	private String userPwd;
	private String name;
	private int age;
	private char gender;
	private String email;
	private int point; // 매개변수 생성자에 안들어가 있음 => 회원가입할때 정보 안 받음
	
	

	CampInfo ci = new CampInfo();
	CampingController cc = new CampingController();

	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String userId, String userPwd, String name, int age, char gender, String email) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String information() {
		int count = 0;
		
		
		for(CampInfo c : cc.camp){//cc의 camp를 받아와서 인덱스별로 비교해가벼 reservId가 userId와 같은지 보고 count++
			if(userId.equals(c.getReservId())) {
				count++;
			}
		}
		
		return "아이디 :" +userId +"\n패스워드 : "+userPwd +"\n"+name+" 회원님의 정보\n 나이 : "+age+"\n성별 : "+gender+
				"\n e-mail: "+email+"\n현재 포인트 : "+point+"\n현재 예약은  "+count+"건 입니다." ;// 예약상태 보여주기
				
	}

}
