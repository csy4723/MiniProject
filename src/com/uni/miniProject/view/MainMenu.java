package com.uni.miniProject.view;

import java.util.Scanner;

import com.uni.miniProject.controller.CampingController;
import com.uni.miniProject.controller.MemberController;
import com.uni.miniProject.controller.ReviewController;
import com.uni.miniProject.controller.TransferController;
import com.uni.miniProject.controller.UserController;

public class MainMenu {
	public static String ID;

	MemberController mc = new MemberController();
	CampingController cc = new CampingController();
	TransferController tc = new TransferController();
	UserController uc = new UserController();
	ReviewController rc = new ReviewController();
	Scanner sc = new Scanner(System.in);

	public MainMenu() {
	}

	public void mainMenu() {

		
		
		cc.campRead(); // run에서 이걸 먼저 실행하고 main 실행하면 camp이 자꾸 초기화 된다 왜인지 모를 
		rc.ReviewRead();
	

		
		while (true) {
			System.out.println("1. 로그인 하시겠습니까? ");
			System.out.println("2. 회원 가입 하시겠습니까? ");
			System.out.print("메뉴 입력 : ");
			int openMenu = sc.nextInt();
			sc.nextLine();

			boolean out = false; // 해당 while문 나가기 위한 장치 -서영

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
				ReviewBoard();
				break;
			case 9:
				rc.ReviewSave();
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
	
	public void transferBoard() {
		while (true) {

			System.out.println("===양도 게시판===");
			System.out.println("1. 양도하기");
			System.out.println("2. 양도 글 조회");
			System.out.println("3. 글 삭제, 수정");
			System.out.println("9. 이전 메뉴로");
			int transferMenu = sc.nextInt();
			sc.nextLine();
			
			switch (transferMenu) {
			
			case 1:
				tc.postTransfer();
				break;
			case 2:
				tc.transferList();
				break;
			case 3:
				tc.myTransfer();
				break;
			case 9:
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요");
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
				cc.campSearch();
				break;
			case 2 : 
				cc.campSort();
				break;
			case 9 : 
				return;
			default :
				System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
				break;
			}
		}
	}
	
	public void ReviewBoard() {
		while (true) {
			System.out.println("===리뷰 게시판===");
			System.out.println("1. 리뷰 글 쓰기");
			System.out.println("2. 리뷰 글 검색");
			System.out.println("3. 리뷰 글 정렬");
			System.out.println("4. 내 글 조회");
			System.out.println("9. 이전 메뉴로");
			System.out.println("메뉴 선택 : ");
			int Menu = sc.nextInt();
			sc.nextLine();
			
			switch (Menu) {
			case 1:
				rc.ReviewWrite();
				break;
			case 2:
				rc.ReviewSearch();
				break;
			case 3:
				rc.ReviewSort();
				break;
			case 4:
				rc.MyReview();
				break;
			case 9:
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
				break;
			}
		}
	}
}
