package com.uni.miniProject.model.vo;

public class Member {
	
	private String userId;
	private String userPwd;
	private String name;
	private int age;
	private char gender;
	private String email;
	private int point;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}
 
	public Member(String userId, String userPwd, String name, int age, char gender, String email) {//point 없음 가입할때 입력받는 정보가 아님
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
	}
	public Member(String userId, String userPwd, String name, int age, char gender, String email, int point) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.point = point;
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

	public String info() {
		return "아이디 :" +userId +"\n패스워드 : "+userPwd +"\n"+name+" 회원님의 정보\n 나이 : "+age+"\n성별 : "+gender+
				"\n e-mail: "+email;
	}
	public String information() {
		return "아이디 :" +userId +"\n패스워드 : "+userPwd +"\n"+name+" 회원님의 정보\n 나이 : "+age+"\n성별 : "+gender+
				"\n e-mail: "+email+"\n현재 포인트 : "+point+"\n현재 예약상태 : " ;// 예약상태 보여주기
	}

	public void updateMember(Member m, int menu, String update) {
		// 메뉴번호가 1일경우 비밀번호 수정
		if(menu ==1 ) {
			m.setUserPwd(update); // 해당 전달된 회원 객체의 비밀번호를 update문자열로 변경
		}else if(menu ==2) {
			m.setName(update);// 해당 전달된 회원 객체의 이름을 update문자열로 변경
		}
		else if(menu ==3) {
			m.setAge(Integer.valueOf(update));// 해당 전달된 회원 객체의 나이를 update문자열로 변경
		}
		else if(menu ==4) {
			m.setEmail(update);// 해당 전달된 회원 객체의 이메일을 update문자열로 변경
		}
		
	}

}
