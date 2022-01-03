package com.uni.miniProject.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.uni.miniProject.controller.CampingController;
import com.uni.miniProject.controller.MemberController;
import com.uni.miniProject.controller.UserController;
import com.uni.miniProject.model.vo.CampInfo;
import com.uni.miniProject.model.vo.Write;

public class MainMenu {
	public static String ID;

	MemberController mc = new MemberController();
	CampingController cc = new CampingController();
	UserController uc = new UserController();
	Scanner sc = new Scanner(System.in);

	public MainMenu() {
		// TODO Auto-generated constructor stub
	}

	public void mainMenu() {
	//	cc.campRead(); // run에서 이걸 먼저 실행하고 main 실행하면 camp이 자꾸 초기화 된다 왜인지 모를 
		
		while (true) {
			System.out.println("1. 로그인 하시겠습니까? ");
			System.out.println("2. 회원 가입 하시겠습니까? ");
			System.out.print("메뉴 입력 : ");
			int openMenu = sc.nextInt();
			sc.nextLine();

			boolean out = false;// 해당 while문 나가기 위한 장치 -서영

			switch (openMenu) {
			case 1:
				ID = "admin";// 유저가 아이디 입력한 값을 ID에 담아주세요
				uc.login();
				out = true;
				break;
			case 2:
				uc.SignUp();
				out = true;
				break;
			default:
				System.out.println("잘못 입력하셨습니다.다시 입력하세요");
				continue;
			}
			if (out) {
				break; // out이 true면 while문 나감
			}
		}

		while (true) {

			System.out.println("====메인 메뉴===");

			if (ID.equals("admin")) {
				System.out.println("0. 관리자 페이지");
			}
			System.out.println("1. 마이 페이지");
			System.out.println("2. 캠핑 예약");
			System.out.println("3. 양도게시판");
			System.out.println("4. 자유게시판");
			System.out.println("5. 리뷰게시판");
			System.out.println("9. 프로그램 종료");
			System.out.print("메뉴 입력 : ");
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 0:
				adminPage();
				break;
			case 1:
				break;
			case 2:
				campSearch();
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 9:
				
				cc.campWrite();
				System.out.println("프로그램이 종료됩니다.");
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요");
				break;

			}

		}
	}

	private void adminPage() {
		while (true) {

			System.out.println("===관리자 페이지===");
			System.out.println("1. 전체 글 조회");
			System.out.println("2. 공지사항 조회");
			System.out.println("3. 캠핑장 목록 조회");
			System.out.println("9. 이전 메뉴로");
			int adminMenu = sc.nextInt();
			sc.nextLine();

			switch (adminMenu) {
			case 1:
				break;
			case 2: 
				mc.noticList();
				System.out.println("1. 공지 사항을 등록하시겠습니까? ");
				System.out.println("2. 공지사항을 삭제하시겠습니까? ");
				System.out.println("이전으로 돌아가시려면 아무 번호나 누르세요");
				int nMenu = sc.nextInt();
				sc.nextLine();

				if (nMenu == 1) {
					mc.postNotice();
				} else if (nMenu == 2) {
					mc.deleteNotice();
				}else {
					System.out.println("이전메뉴로 돌아갑니다.");
				}

				break;
			case 3:
				cc.campList();
				System.out.println("1. 캠핑장을 등록하시겠습니까? ");
				System.out.println("2. 캠핑장을 삭제하시겠습니까? ");
				System.out.println("이전으로 돌아가시려면 아무 번호나 누르세요");
				int cMenu = sc.nextInt();
				sc.nextLine();

				if (cMenu == 1) {
					cc.campRegister();
				} else if (cMenu == 2) {
					cc.campDelete();
				}else {
					System.out.println("이전메뉴로 돌아갑니다.");
				}

				break;
			case 9:
				System.out.println("메인 메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("잘못 입력했습니다.");
				break;

			}

		}

	}
	public void campSearch() {
		while (true) {
			System.out.println("===캠핑장 예약===");
			System.out.println("1. 캠핑장 검색");
			System.out.println("2. 캠핑장 정렬");
			System.out.println("9. 이전 메뉴로");
			
			System.out.println("메뉴 선택 : ");
			int search = sc.nextInt();
			sc.nextLine();
			
			switch (search) {
			
			case 1 : 
				System.out.println("[등록된 캠핑장 목록]");
				cc.campList();
				
				System.out.println("==캠핑장 검색==");
				System.out.println("키워드 입력 : ");
				String keyword = sc.nextLine();
				
				System.out.println("검색 결과 = ");
				
			case 2 : 
				System.out.println("===캠핑장 정렬===");
				System.out.println("1. 지역 오름차순 정렬");
				System.out.println("2. 지역 내림차순 정렬");
				System.out.println("3. 제목 오름차순 정렬");
				System.out.println("4. 제목 내림차순 정렬");
				System.out.println("5. 가격 오름차순 정렬");
				System.out.println("6. 가격 내림차순 정렬");
				System.out.println("9. 이전 메뉴로");
				System.out.println("메뉴 선택 : ");
				int campSort = sc.nextInt();
				sc.nextLine();
				
			case 9 : 
				return;
				
			default :
				System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
				break;
			}
		}
	}

}
