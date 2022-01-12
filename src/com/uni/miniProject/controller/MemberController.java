package com.uni.miniProject.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.uni.miniProject.model.vo.CampInfo;
import com.uni.miniProject.model.vo.Write;
import com.uni.miniProject.view.MainMenu;

public class MemberController {
	Scanner sc = new Scanner(System.in);
	Write wr = new Write();
	ArrayList<Write> notic = new ArrayList<Write>();// 공지사항 담을 리스트
	CampingController cc = new CampingController();
	TransferController tc = new TransferController();
	FreeBoardController fc = new FreeBoardController();
	ReviewController rc = new ReviewController();

	{
		notic.add(new Write("화재 조심", "겨울이라 건조하니 화재조심합시다", cc.setCalendar(2021, 0, 12), "admin"));
		notic.add(new Write("소음 자제", "공공장소에서는 조용히 합시다", cc.setCalendar(2021, 3, 24), "admin"));
		notic.add(new Write("거리 두기", "코로나 조심, 또 조심", cc.setCalendar(2021, 6, 02), "admin"));
		notic.add(new Write("할인 이벤트", "내년까지 대폭 할인합니다.", cc.setCalendar(2021, 12, 10), "admin"));
	}

	public MemberController() {
		// TODO Auto-generated constructor stub
	}

	public void noticList() { // 공지사항 조회 메소드

		int index = 0;
		for (Write w : notic) {
			System.out.println("[글번호:" + (index + 1) + "]" + w);
			index++;
		}

	}

	public void postNotice() { // 공지사항 등록 메소드
		System.out.println("제목 : ");
		String title = sc.nextLine();

		System.out.println("내용 : ");
		String content = sc.nextLine();

		Calendar cal = Calendar.getInstance();

		wr = new Write(title, content, cal, MainMenu.ID);

		notic.add(wr);
		System.out.println("성공적으로 등록되었습니다.");

	}

	public void deleteNotice() {
		System.out.println("삭제할 공지사항의 글번호를 입력하시오 : ");
		int index = sc.nextInt();
		sc.nextLine();
		index--;

		System.out.println(notic.get(index));
		System.out.println("정말 삭제하시겠습니까? (y/n)");
		String yn = sc.nextLine();
		if (yn.equalsIgnoreCase("Y")) {
			notic.remove(index);
		} else {
			System.out.println("삭제를 취소합니다.");
		}

	}

	public void everyWrite() {

		System.out.println("===양도글===");
		int i = 0;
		for (CampInfo c : TransferController.transferCamp) {
			System.out.println((i+1) + ". " + c.information());
			i++;
		}

		System.out.println();
		int j = 0;
		System.out.println("===자유게시판===");
		for (Write w : FreeBoardController.freeBoard) {
			System.out.println((j+1) + ". " + w.information());
			j++;
		}

		System.out.println();
		System.out.println("===리뷰게시판===");
		int k = 0;
		for (Write w : ReviewController.rWrite) {
			System.out.println((k+1) + ". " + w.information());
			k++;
		}

		System.out.println();

		System.out.println("글을 삭제하시겠습니까?(y/n)");
		String yn = sc.nextLine();

		if (yn.equalsIgnoreCase("Y")) {

			System.out.println("삭제할 게시판을 고르세요");
			System.out.println("1. 양도글");
			System.out.println("2. 자유게시판");
			System.out.println("3. 리뷰게시판");
			System.out.println("번호 입력 : ");
			int num = sc.nextInt();
			System.out.println("삭제할 글 번호를 눌러주세요");
			int mun = sc.nextInt();
			System.out.println();

			switch (num) {
			case 1:
				if (mun < TransferController.transferCamp.size()) {
					--mun;
					System.out.println(TransferController.transferCamp.get(mun).information());
					TransferController.transferCamp.remove(--mun);
					System.out.println("삭제가 완료되었습니다.");
				} else {
					System.out.println("글번호를 잘못 입력했습니다.");
					System.out.println("삭제를 취소합니다.");
					System.out.println();
				}

				break;
			case 2:
				if (mun < FreeBoardController.freeBoard.size()) {
					--mun;
					System.out.println(FreeBoardController.freeBoard.get(mun).information());
					FreeBoardController.freeBoard.remove(mun);
					System.out.println("삭제가 완료되었습니다.");
				} else {
					System.out.println("글번호를 잘못 입력했습니다.");
					System.out.println("삭제를 취소합니다.");
					System.out.println();
				}

				break;
			case 3:
				if (mun < ReviewController.rWrite.size()) {
					--mun;
					
					System.out.println(ReviewController.rWrite.get(mun).information());
					ReviewController.rWrite.remove(mun);
					System.out.println("삭제가 완료되었습니다.");
				} else {
					System.out.println("글번호를 잘못 입력했습니다.");
					System.out.println("삭제를 취소합니다.");
					System.out.println();
				}

				break;
			default:
				System.out.println("잘못 눌렀습니다.");
				System.out.println("이전 메뉴로 돌아갑니다.");
				System.out.println();

			}

		}

	}

	public void eDelete() {

		// 지우고 싶은 글의 리스트 이름과 글번호 or 제목을 대조해서
		// 삭제

	}

}
