package com.uni.miniProject.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.uni.miniProject.model.vo.CampInfo;
import com.uni.miniProject.model.vo.UserInfo;

public class UserController {

	Scanner sc = new Scanner(System.in);
	UserInfo u = new UserInfo();
	ArrayList<UserInfo> user = new ArrayList<UserInfo>();// 유저정보 담들것

	public UserController() {
		// TODO Auto-generated constructor stub
	}

	public UserInfo signUp() { // 회원가입
		System.out.println("회원 가입");
		System.out.println("아이디와 비밀번호를 입력하세요 ");
		System.out.println("아이디 입력 : ");
		String id = sc.nextLine();
		System.out.println("비밀번호 입력 : ");
		String pw = sc.nextLine();

		if (idCheck(id)) {
			System.out.println("사용중인 ID입니다.");
			return null;
		} else {
			System.out.println(id + "님 환영합니다.");
			u = new UserInfo(id, pw);
			user.add(u);// 회원목록 리스트에 담기
			
			return u;
		}

		
		
		
		 
	}

	private boolean idCheck(String id) { // 중복된 아이디 있는지 체크
		boolean check = true;
		u = FindbyID(id);
		if (u == null)
			check = false;

		return check;
	}

	private UserInfo FindbyID(String id) { // 리스트에서 아이디 찾기
		for (UserInfo u : user) {
			if (u.getId().equals(id)) {
				return u;
			}
		}
		return null;
	}

	public UserInfo logIn() { // 로그인

		System.out.println("아이디와 비밀번호를 입력해주세요");
		System.out.println("ID 입력 : ");
		String id = sc.nextLine();

		System.out.println("PW 입력 : ");
		String pw = sc.nextLine();

		u = FindbyID(id);
		if (u == null) {
			System.out.println("등록되지않은 아이디입니다");
			
			return null;
		} else if (u.getPw().equals(pw)) {
			System.out.println(u.getId() + " 님 로그인 되었습니다.");
			
			return u;
		} else {
			System.out.println("아이디 혹은 비밀번호를 다시 확인해주세요");
			return null;
		}
		
	}

	public void userDelete() {// 회원 탈퇴

		System.out.println("탈퇴할 아이디를 입력해주세요");
		String id = sc.nextLine();

		for (int i = 0; i < user.size(); i++) {

			if (id.equals(user.get(i).getId())) {
				user.remove(i);
				return;
			}
		}
		System.out.println("회원정보가 없습니다.");

	}
	
	
	
	

}
