package com.uni.miniProject.view;

import java.util.Scanner;

import com.uni.miniProject.controller.CampingController;
import com.uni.miniProject.controller.FreeBoardController;
import com.uni.miniProject.controller.MemberController;
import com.uni.miniProject.controller.ReviewController;
import com.uni.miniProject.controller.TransferController;

import com.uni.miniProject.controller.UserController;

import com.uni.miniProject.model.vo.CampInfo;
import com.uni.miniProject.model.vo.Member;
import com.uni.miniProject.model.vo.Write;

public class MainMenu {
	public static String ID;

	Member m = new Member();
	MemberController mc = new MemberController();
	CampingController cc = new CampingController();
	TransferController tc = new TransferController();
	FreeBoardController fc = new FreeBoardController();
	UserController uc = new UserController();
	ReviewController rc = new ReviewController();
	Scanner sc = new Scanner(System.in);

	public MainMenu() {
		// TODO Auto-generated constructor stub
	}

	public void mainMenu() {

		boolean out = false;
		while (!out) {
			cc.campRead(); // run에서 이걸 먼저 실행하고 main 실행하면 camp이 자꾸 초기화 된다 왜인지 모를
			uc.userReadFile();
			tc.tCampRead();
			fc.freeBoardRead();
			rc.ReviewRead();
			
			System.out.println("1. 로그인 하시겠습니까? ");
			System.out.println("2. 회원 가입 하시겠습니까? ");
			System.out.print("메뉴 입력 : ");
			int openMenu = sc.nextInt();
			sc.nextLine();

			switch (openMenu) {
	         case 1:
	            

	            ID = uc.logIn();
	            out = ID == ""? false : true;
	            break;
	         case 2:
	            ID = uc.signUp();
	            out = true;
	            break;
	         default:
	            System.out.println("잘못 입력하셨습니다.다시 입력하세요");
	            continue;
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
				myPage();
				break;
			case 2:
				campSearch();
				break;
			case 3:
				transferBoard();
				break;
			case 4:
				freeBoard();
				break;
			case 5:
				ReviewBoard();
				break;
			case 9:

				cc.campWrite();
				tc.tCampWrite();
				fc.freeBoardWrite();
				uc.userSaveFile();
				rc.ReviewSave();
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
				mc.everyWrite();
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
				} else {
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
				} else {
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

	public void myPage() {

		while (true) {
			System.out.println("====마이 페이지 ====");
			System.out.println("1. 개인정보 조회");
			System.out.println("2. 개인정보 수정");
			System.out.println("3. 내 예약 조회");
			System.out.println("4. 포인트 충전");
			System.out.println("5. 회원 탈퇴");
			System.out.println("9. 이전메뉴로");
			System.out.println("메뉴번호를 입력해 주세요 : ");
			int mypagemenu = sc.nextInt();
			sc.nextLine();

			switch (mypagemenu) {
			case 1: // 개인정보 조회
				uc.serchUser();
				break;
			case 2: // 개인정보 수정
				uc.updateUser();
				break;	
			case 3: // 내 예약 조회
				cc.reserveCheck();
				break;
			case 4: // 포인트 충전
				uc.pointCharge();
				break;
			case 5: // 회원 탈퇴
				uc.userDelete();
				uc.userSaveFile();
				System.exit(0);
				break;
			case 9: // 이전메뉴로
				return;
			default:
				System.out.println("다시 입력해주세요");
				break;
			}

		}
	}

	public void transferBoard() {
		while (true) {

			System.out.println("===양도 게시판===");
			System.out.println("1. 양도 하기");
			System.out.println("2. 양도 받기");
			System.out.println("3. 내 양도 조회(삭제,수정)");
			System.out.println("9. 이전 메뉴로");
			int transferMenu = sc.nextInt();
			sc.nextLine();

			switch (transferMenu) {

			case 1:
				tc.postTransfer();
				break;
			case 2:
				tc.getTransfer();
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
	
	public void freeBoard() {
		while (true) {

			System.out.println("===자유 게시판===");
			System.out.println("1. 검색하기");
			System.out.println("2. 정렬하기");
			System.out.println("3. 내 글 조회");
			System.out.println("4. 글 작성");
			System.out.println("9. 이전 메뉴로");
			fc.allFree();
			int freeMenu = sc.nextInt();
			sc.nextLine();
			
			switch (freeMenu) {

			case 1:
				fc.searchFree();
				break;
			case 2:
				fc.freeSort();
				break;
			case 3:
				fc.myFree();
				break;
			case 4:
				fc.postFree();
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

			case 1:
				cc.campSearch();
				break;
			case 2:
				cc.campSort();
				break;

			case 9:
				return;

			default:
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
