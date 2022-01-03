package com.uni.miniProject.controller;

import java.util.Scanner;

public class ReviewController {
	Scanner sc = new Scanner(System.in);

	public void reviewWrite() {
		
	}

	public void reviewSearch() {
		System.out.println("[등록된 리뷰 목록]");
		
		System.out.println("===리뷰 게시글 검색===");		
		System.out.println("검색할 제목 입력 : ");
		String head = sc.nextLine();

		
		System.out.println("===검색 결과===");
		
		/*for(int i = 0; i < camp.size(); i++) {
			if(camp.get(i).getCampArea().equals(keyword)) {
				System.out.println(camp.get(i).information());
			}
		}*/
		System.out.println("계속 검색하시겠습니까?(y/n)(n을 누르면 돌아갑니다) : ");
		char ch = sc.nextLine().toUpperCase().charAt(0);
	}
	
	public void reviewSort() {
		
	}
	
	public void myReview() {
		
	}
}
