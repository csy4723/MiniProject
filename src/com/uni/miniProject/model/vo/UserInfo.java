package com.uni.miniProject.model.vo;

public class UserInfo {

	private String Id;
	private String Pw;
	
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}
 
	public UserInfo(String id, String pw) {
		super();
		Id = id;
		Pw = pw;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getPw() {
		return Pw;
	}

	public void setPw(String pw) {
		Pw = pw;
	}

	@Override
	public String toString() {
		return "User [Id=" + Id + ", Pw=" + Pw + "]";
	}
	
	
}
