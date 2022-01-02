package com.uni.miniProject.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.uni.miniProject.model.vo.Write;
import com.uni.miniProject.view.MainMenu;

public class MemberController {
	Scanner sc = new Scanner(System.in);
	Write wr = new Write();
	ArrayList<Write> notic = new ArrayList<Write>();// 공지사항 담을 리스트
	CampingController cc = new CampingController();
	{
		notic.add(new Write("화재 조심", "겨울이라 건조하니 화재조심합시다", cc.setCalendar(2021, 0, 12), "admin"));
		notic.add(new Write("소음 자제", "공공장소에서는 조용히 합시다", cc.setCalendar(2021, 3, 24), "admin"));
		notic.add(new Write("거리 두기", "코로나 조심, 또 조심", cc.setCalendar(2021, 6, 02), "admin"));
		notic.add(new Write("할인 이벤트", "내년까지 대폭 할인합니다.", cc.setCalendar(2021, 12, 10), "admin"));
	}
	
	
	public MemberController() {
		// TODO Auto-generated constructor stub
	}

	public void noticList() { //공지사항 조회 메소드

		int index = 0;
		for (Write w : notic) {
			System.out.println("[글번호:"+(index+1) +"]"+w);
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
		index--;
		
		System.out.println(notic.get(index));
		System.out.println("정말 삭제하시겠습니까? (y/n)");
		String yn = sc.nextLine();
		if(yn.equalsIgnoreCase("Y")) {
		notic.remove(index);
		}else {
			System.out.println("삭제를 취소합니다.");
		}
		
	}

	public void everyWriting() {// 전체 글 조회할 메소드
		// TODO Auto-generated method stub
		
	}

	
}
