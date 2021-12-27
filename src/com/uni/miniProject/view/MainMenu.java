package com.uni.miniProject.view;

import java.util.Scanner;

import com.uni.miniProject.controller.CampingController;

public class MainMenu {
	
	CampingController cc = new CampingController();
	Scanner sc = new Scanner(System.in);
	
	public MainMenu() {
		// TODO Auto-generated constructor stub
	}
	
	public void mainMenu() {
		
		System.out.println("1. 로그인 하시겠습니까? ");
		System.out.println("2. 회원 가입 하시겠습니가? ");
		System.out.print("메뉴 입력 : ");
		int openMenu = sc.nextInt();
		
		
		
		System.out.println("====메인 메뉴===");
		System.out.println("1. 마이 페이지");
		System.out.println("2. 캠핑 예약");
		System.out.println("3. 양도게시판");
		System.out.println("4. 자유게시판");
		System.out.println("5. 리뷰게시판");
		System.out.println("9. 프로그램 종료");
		System.out.print("메뉴 입력 : ");
		int menu = sc.nextInt();
		
	}
	

}
