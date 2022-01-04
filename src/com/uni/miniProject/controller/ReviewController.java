package com.uni.miniProject.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.uni.miniProject.model.vo.ReviewWrite;
import com.uni.miniProject.view.MainMenu;

public class ReviewController {

	Scanner sc = new Scanner(System.in);
	ReviewWrite rw = new ReviewWrite();
	ArrayList<ReviewWrite> rWrite = new ArrayList<ReviewWrite>();

	public ReviewController() {
		// TODO Auto-generated constructor stub
	}

	public void Review() {

	}

	public void ReviewPrint() { // 출력문
		for (int i = 0; i < rWrite.size(); i++) {
			System.out.println(rWrite.get(i).toString());
		}
	}

	public String ReviewDate() { // 현재시간 출력
		Calendar date = Calendar.getInstance();
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH) + 1;
		int day = date.get(Calendar.DATE);
		int hour = date.get(Calendar.HOUR);
		int minute = date.get(Calendar.MINUTE);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 MM분");
		return sdf.format(date.getTime());
	}

	
	public void ReviewWrite() {
		int count = 1;

		System.out.println("===리뷰 글 쓰기===");

		System.out.println("제목 : ");
		String title = sc.nextLine();

		System.out.println("내용 : ");
		String content = sc.nextLine();

		for (int i = 0; i < rWrite.size(); i++) {
			count++;
		}

		rw = new ReviewWrite(count, title, content, ReviewDate(), MainMenu.ID);

		rWrite.add(rw); // 받은 배열을 ArrayList로 담아주기

		System.out.println("리뷰 작성이 완료되었습니다.");
		ReviewPrint();
	}

	
	public void ReviewSearch() {
		ReviewWrite rww = new ReviewWrite();
		
		System.out.println("[등록된 리뷰 목록]");
		ReviewPrint();

		while (true) {
			
			System.out.println("===리뷰 게시글 검색===");
			System.out.println("검색할 제목 입력 : ");
			String head = sc.nextLine();

			System.out.println("===검색 결과==="); // contain 써보기 (썻더니 -1나옴)
			
			for(int i = 0; i < rWrite.size(); i++) {
				if(rWrite.get(i).getContent().equals(head)) {
					System.out.println(rWrite.get(i).toString());
				}else if(!rWrite.get(i).getContent().equals(head)){
					System.out.println("검색어와 일치한 게시글이 없습니다. 다시 검색해주세요.");
				}
			}
			
			System.out.println("계속 검색하시겠습니까?(y/n) : ");
			char ch = sc.nextLine().toLowerCase().charAt(0);
			
			if (ch == 'y') {
				continue;
			}else if (ch == 'n') {
				System.out.println("이전 메뉴로 돌아갑니다.");
				break;
			} else {
				System.out.println("정확히 입력해주세요.");
			}
		}
	}

	public void ReviewSort() {
		while (true) {
			System.out.println("===리뷰 글 정렬===");
			System.out.println("1. 제목 오름차순 정렬");
			System.out.println("2. 제목 내림차순 정렬");
			System.out.println("3. 아이디 오름차순 정렬");
			System.out.println("4. 아이디 내림차순 정렬");
			System.out.println("9. 이전 메뉴로");
			System.out.println("메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();

			if (menu == 1) {

			} else if (menu == 2) {

			} else if (menu == 3) {

			} else if (menu == 4) {

			} else if (menu == 9) {
				return;
			} else {
				System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
				break;
			}
		}

	}

	public void MyReview() {
		System.out.println("===내 글 조회===");
		// 내 글 출력

		System.out.println("1. 내 글 수정");
		System.out.println("2. 내 글 삭제");
		
		System.out.println("메뉴 선택 : ");
		int menu = sc.nextInt();
		sc.nextLine();

		
	}
}
