package com.uni.miniProject.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.uni.miniProject.model.vo.Write;
import com.uni.miniProject.view.MainMenu;

public class TransferController {

	Scanner sc = new Scanner(System.in);
	Write wr = new Write();
	ArrayList<Write> transfer = new ArrayList<Write>();// 양도 글들을 담을 리스트

	public TransferController() {
	}

	public void transferList() {

		int index = 0;
		for (Write w : transfer) {
			System.out.println("[글번호:" + (index + 1) + "]" + w);
			index++;
		}
		
		System.out.println("양도받을 글번호를 입력하세요 : ");
		int num = sc.nextInt();
		num += -1;
		
		
	}

	public void postTransfer() { // 양도글 등록 메소드
		System.out.println("제목 : ");
		String title = sc.nextLine();

		System.out.println("내용 : ");
		String content = sc.nextLine();

		Calendar cal = Calendar.getInstance();

		wr = new Write(title, content, cal, MainMenu.ID);

		transfer.add(wr);
		System.out.println("성공적으로 등록되었습니다.");

	}

	public void myTransfer() {
		transferList();
		System.out.println("글 번호 입력 : ");
		int boardNum = sc.nextInt();
		sc.nextLine();

		System.out.println("1. 삭제");
		System.out.println("2. 수정");
		System.out.println("9. 이전 메뉴로");
		int menu = sc.nextInt();
		sc.nextLine();
		switch (menu) {
		case 1:
			deleteTransfer(boardNum);
		case 2:
			editTransfer(boardNum);
		case 9:
			return;
		default:
			System.out.println("잘못 입력했습니다.");
		}
	}

	public void deleteTransfer(int index) {// 양도글 삭제 메소드

		index--;

		System.out.println(transfer.get(index));
		System.out.println("정말 삭제하시겠습니까? (y/n)");
		String yn = sc.nextLine();
		if (yn.equalsIgnoreCase("Y")) {
			transfer.remove(index);
		} else {
			System.out.println("삭제를 취소합니다.");
		}

	}

	public void editTransfer(int index) {// 양도글 수정 메소드

		index--;

		System.out.println(transfer.get(index));
		System.out.println("이 글을 수정하시겠습니까? (y/n)");
		String yn = sc.nextLine();
		if (yn.equalsIgnoreCase("Y")) {
		} else {
			System.out.println("수정을 취소합니다.");
		}

	}

}
