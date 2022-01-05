package com.uni.miniProject.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.uni.miniProject.model.vo.CampInfo;
import com.uni.miniProject.view.MainMenu;

public class TransferController {
	Scanner sc = new Scanner(System.in);

	static ArrayList<CampInfo> transferCamp = new ArrayList<CampInfo>(); // 전체 양도 글들을 담을 목록
	ArrayList<CampInfo> loginTransferCamp = new ArrayList<CampInfo>(); // 로그인된 아이디의 양도글 목록

	public TransferController() {
	}

	public void getTransfer() {// 양도 받기 메소드
		int j = 0;
		for (int i = 0; i < transferCamp.size(); i++) {// 전체 양도글 조회
			if (!transferCamp.get(i).getReservId().equals(MainMenu.ID)) {// 내가 등록한 양도는 안뜨게

				System.out.println(transferCamp.get(i));
				j++;

			}

		}

		if (transferCamp.isEmpty() || j == 0) {// 양도글 없을
			System.out.println("등록된 양도글이 없습니다.");
			return;
		}

		System.out.println("양도 받을 캠핑장의 이름을 입력하세요");
		String transferName = sc.nextLine();
		int k = 0;
		for (int i = 0; i < transferCamp.size(); i++) {
			if (transferCamp.get(i).getCampName().equals(transferName)) {
				System.out.println(transferCamp.get(i));
				k = i;
			}
		}

		System.out.println("위의 캠핑장을 양도 받으시겠습니까? (y/n)");
		String yn = sc.nextLine();
		if (yn.equalsIgnoreCase("Y")) {

			transferCamp.remove(k);// 전체 양도 리스트에서 삭제

			for (int i = 0; i < loginTransferCamp.size(); i++) {
				if (loginTransferCamp.get(i).getCampName().equals(transferName)) {
					loginTransferCamp.remove(i);// 양도글 올린사람 아이디의 내 양도에서 삭제
				}
			}

			for (int i = 0; i < CampingController.camp.size(); i++) {
				if (CampingController.camp.get(i).getCampName().equals(transferName)) {
					CampingController.camp.get(i).setReservId(MainMenu.ID);// 전체 캠핑장에서 양도받을 캠핑 id를 양도받은사람 id로 수정(양도받은사람
																			// 예약에 추가)
					CampingController.camp.get(i).setState(false);// 양도 등록 상태 X로 전환
				}

			}

			System.out.println("양도가 완료되었습니다.");
		} else {
			System.out.println("양도 받기를 취소합니다.");
			return;
		}

	}

	public void postTransfer() { // 양도글 등록 메소드
		// 전체 양도글 조회할땐 파일이 있을테니까 그걸 읽어서 배열에 담아가지고

		System.out.println();

		boolean uState = false;
		for (CampInfo c : CampingController.camp) {

			if (c.getReservId().equals(MainMenu.ID)) {
				System.out.println(c);// 로그인된 아이디의 예약 목록 출력
				uState = true;
			}

		}

		if (!uState) {
			System.out.println("예약된 정보가 없습니다.");// 예약 없을시 리턴
			return;
		}

		System.out.println("양도할 캠핑장의 이름을 입력하세요 : (이전메뉴 : exit)");
		String name = sc.nextLine();
		if (name.equals("exit")) {
			return;
		}
		int i = 0;// 캠핑장 인덱스 담을 변수
		for (CampInfo c : CampingController.camp) {

			if (c.getCampName().equals(name)) {
				break;
			}

			i++;
		}

		if (i == CampingController.camp.size()) {
			System.out.println("입력한 이름의 캠핑장 없습니다.");
			return;
		}

		CampInfo temp = CampingController.camp.get(i);

		System.out.println(temp.information());// 캠핑장 정보 출력
		System.out.println("위 예약을 양도하시겠습니까? (y/n)");
		String yn = sc.nextLine();

		if (yn.equalsIgnoreCase("Y")) {

			try {
				transferCamp.add(temp.clone());
			} catch (CloneNotSupportedException e) {
				System.out.println("양도글 등록에 실패했습니다.");
				return;

			} // 양도글 리스트에 담기
			System.out.println(transferCamp.size());

			System.out.println();
			// System.out.println("현재 " + MainMenu.ID + "님의 양도된 예약" +
			// loginTransferCamp.size() + "개");

			for (int j = 0; j < transferCamp.size(); j++) {
				if (transferCamp.get(j).getReservId().equals(MainMenu.ID)) {
					System.out.println(transferCamp.get(j).information());
				}

			}
			CampingController.camp.get(i).setState(true); // 양도 등록 상태 O로 전환
			CampingController.camp.get(i).setReservId(" ");
			System.out.println("위의 예약을 성공적으로 등록되었습니다.");

		} else {
			System.out.println("양도 등록을 취소합니다.");
		}

	}

	public void myTransfer() {// 내 양도글 관리 메소드
		// 전체 양도글 조회

		System.out.println("====나의 양도글====");
		myTransferList();
		System.out.println("캠핑장 이름 입력 : (이전메뉴 : exit)");
		String campName = sc.nextLine();
		if (campName.equals("exit")) {
			return;
		}
		System.out.println("1. 삭제");
		System.out.println("2. 수정(삭제후 재등록)");
		System.out.println("9. 이전 메뉴로");
		int menu = sc.nextInt();
		sc.nextLine();
		switch (menu) {
		case 1:
			deleteTransfer(campName);
			break;
		case 2:
			editTransfer(campName);
			break;
		case 9:
			return;
		default:
			System.out.println("잘못 입력했습니다.");
		}
	}

	public void deleteTransfer(String campName) {// 양도글 삭제 메소드

		int k = 0;
		for (int i = 0; i < loginTransferCamp.size(); i++) {
			if (loginTransferCamp.get(i).getCampName().equals(campName)) {
				System.out.println(loginTransferCamp.get(i));
				k = i;
			}
		}

		System.out.println("위의 양도를 삭제하시겠습니까? (y/n)");
		String yn = sc.nextLine();
		if (yn.equalsIgnoreCase("Y")) {
			for (int i = 0; i < transferCamp.size(); i++) {
				if (transferCamp.get(i).getCampName().equals(campName)) {
					transferCamp.remove(i); // 전체 양도글 목록에서 삭제
				}
			}

			loginTransferCamp.remove(k);// 로그인된 아이디의 내 양도에서 삭제

			for (int i = 0; i < CampingController.camp.size(); i++) {
				if (CampingController.camp.get(i).getCampName().equals(campName)) {
					CampingController.camp.get(i).setReservId(MainMenu.ID);
					CampingController.camp.get(i).setState(false);// 양도등록 상태 X로 전환
				}
			}

			System.out.println("삭제가 완료되었습니다.");
		} else {
			System.out.println("삭제를 취소합니다.");
		}

	}

	public void editTransfer(String campName) {// 양도글 수정 메소드

		deleteTransfer(campName);

		postTransfer();
	}

	public void transferList() { // 전체 양도글 조회

		System.out.println("=====양도글 목록====");

		System.out.println("총 양도글 수 : " + transferCamp.size());
		for (int i = 0; i < transferCamp.size(); i++) {

			System.out.println(i + 1 + ". " + transferCamp.get(i));

		}

	}

	public void myTransferList() {

		loginTransferCamp.clear();
		for (int i = 0; i < transferCamp.size(); i++) {
			if (transferCamp.get(i).getReservId().equals(MainMenu.ID)) {
				try {
					loginTransferCamp.add(transferCamp.get(i).clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("내 양도 목록");
		System.out.println("총 " + loginTransferCamp.size() + "개");

		for (CampInfo c : loginTransferCamp) {
			System.out.println(c);
		}

	}

	

	public void tCampRead() {

		File f = new File("transferBoard.txt");
		ArrayList tem = null;

		try (BufferedReader br = new BufferedReader(new FileReader(f.getName()))) {

			String temp = null;
			while ((temp = br.readLine()) != null) {

				String[] arr = temp.split(",");

				String name = arr[0];
				String area = arr[1];
				int price = Integer.valueOf(arr[2]);

				String[] arr2 = arr[3].split("-");

				String reservId = arr[4];

				int year = Integer.valueOf(arr2[0]);
				int month = Integer.valueOf(arr2[1]);
				int day = Integer.valueOf(arr2[2]);

				boolean state = Boolean.valueOf(arr[5]);
				CampingController cc = new CampingController();
				CampInfo c = new CampInfo(name, area, price, cc.setCalendar(year, month, day), reservId, state);

				transferCamp.add(c);

			}

		} catch (FileNotFoundException e) {

		} catch (EOFException e) {

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void tCampWrite() {

		File ex = new File("transferBoard.txt");

		if (ex.exists()) {
			ex.delete();
		}

		File f = new File("transferBoard.txt");

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(f.getName()))) {

			for (int i = 0; i < transferCamp.size(); i++) {

				bw.write(transferCamp.get(i).toStrtrans());
				bw.newLine();

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
