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
import java.util.HashSet;
import java.util.Scanner;

import com.uni.miniProject.model.vo.CampInfo;
import com.uni.miniProject.view.MainMenu;

public class TransferController {
	Scanner sc = new Scanner(System.in);

	CampingController cc = new CampingController();

	ArrayList<CampInfo> transferCamp = new ArrayList<CampInfo>(); // 전체 양도 글들을 담을 목록
//	ArrayList<CampInfo> loginReserve = new ArrayList<CampInfo>(); // 로그인된 아이디의 예약 목록
//	ArrayList<CampInfo> loginTransferCamp = new ArrayList<CampInfo>(); // 로그인된 아이디의 양도글 목록

	public TransferController() {
	}

	public void transferList() { // 전체 양도글 조회

		System.out.println("=====양도글 목록====");
		HashSet<CampInfo> list = new HashSet<CampInfo>(transferCamp);// HashSet을 이용해서 중복 제거하기
		transferCamp = new ArrayList<CampInfo>(list);
		System.out.println("총 양도글 수 : " + transferCamp.size());
		for (int i = 0; i < transferCamp.size(); i++) {

			System.out.println(i + 1 + ". " + transferCamp.get(i));
		}

	}

//	public void getTransfer() {// 양도 받기 메소드
//		// 전체 양도글 조회
//		if (transferCamp.isEmpty()) {
//			System.out.println("등록된 양도글이 없습니다.");
//			return;
//		}
//		System.out.println("양도 받을 글 번호를 입력하세요 : ");
//		int num = sc.nextInt();
//		sc.nextLine();
//		num += -1;
//
//		System.out.println("양도 받으시겠습니까? (y/n)");
//		String yn = sc.nextLine();
//
//		if (yn.equalsIgnoreCase("Y")) {
//			System.out.println(transferCamp.get(num));
//			loginReserve.add(transferCamp.get(num)); // 로그인된 아이디의 예약목록에 추가
//
////			for (int i = 0; i < loginReserve.size(); i++) {
////				if (loginReserve.get(i).getCampName().equals(transferCamp.get(num).getCampName())) {
////					loginReserve.get(i).setReservId(MainMenu.ID); // 양도 받을 시 로그인된 아이디로 예약 아이디 변경
////				}
////			}
//
//			logRsInputId(transferCamp.get(num).getCampName(), MainMenu.ID);// 양도 받을 시 로그인된 아이디로 예약 아이디 변경
//
//			for (int i = 0; i < cc.camp.size(); i++) {
//				if (cc.camp.get(i).getCampName().equals(transferCamp.get(num).getCampName())) {
//					cc.camp.get(i).setReservId(MainMenu.ID);// 전체 캠핑 리스트의 아이디를 로그인된 아이디로 변경
//				}
//
//			}
//
//			transferCamp.remove(num); // 전체 양도글 목록에서 삭제
//			// 전체 양도글 목록에 없을때 다른아이디로 로그인된 양도글도 지워져야함...(미구현)
//			// 전체캠핑장 이름은 같고 id만 다를때 지우기
//
//			System.out.println("양도가 완료되었습니다.");
//		} else {
//			System.out.println("양도 받기를 취소합니다.");
//			return;
//		}
//
//		//진행상황 확인용
//		System.out.println("내 예약정보");
//		for (int i = 0; i < loginReserve.size(); i++) {
//			System.out.println(loginReserve.get(i));//내 예약정보 출력
//		}
//		System.out.println("내 양도정보");
//		for (int i = 0; i < loginTransferCamp.size(); i++) {
//			System.out.println(loginTransferCamp.get(i));//내 양도정보 출력
//		}
//		System.out.println("전체 양도글");
//		for (int i = 0; i < transferCamp.size(); i++) {
//			System.out.println(transferCamp.get(i));// 전체 양도글 출력
//		}
//		
//		System.out.println("전체 캠핑글");
//		for (int i = 0; i < cc.camp.size(); i++) {
//			System.out.println(cc.camp.get(i));
//		}
//		
//	}

	public void postTransfer() { // 양도글 등록 메소드
		// 전체 양도글 조회할땐 파일이 있을테니까 그걸 읽어서 배열에 담아가지고

		System.out.println();

		boolean uState = false;
		for (CampInfo c : CampingController.camp) {

			if (c.getReservId().equals(MainMenu.ID)) {
				System.out.println(c.information());
				uState = true;
			}

		} // 유저의 예약된 정보 뽑기

		if (!uState) {
			System.out.println("예약된 정보가 없습니다.");
		} // 예약된게 없으면 출력

		System.out.println("양도할 캠핑장의 이름을 입력하세요");
		String name = sc.nextLine();

		int i = 0;// 캠핑장 인덱스 담을 변수
		for (CampInfo c : CampingController.camp) {

			if (c.getCampName().equals(name)) {
				System.out.println(i);
				break;
			}

			i++;
		}

		if (i == CampingController.camp.size()) {
			System.out.println("입력한 이름의 캠핑장 없습니다.");
			return;
		}

		System.out.println(i);
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
			System.out.println("현재 " + MainMenu.ID + "님의 양도된 예약");

			for (int j = 0; j < transferCamp.size(); j++) {
				if (transferCamp.get(j).getReservId().equals(MainMenu.ID)) {
					System.out.println(transferCamp.get(j).information());
				}

			}

			CampingController.camp.get(i).setReservId(" ");
			CampingController.camp.get(i).setState(true);
			System.out.println("성공적으로 등록되었습니다.");
			

		} else {
			System.out.println("양도 등록을 취소합니다.");
			return;
		}

		// 진행상황 확인용

		System.out.println("전체 캠핑 목록");
		for (CampInfo c : CampingController.camp) {
			System.out.println(c.toString());
		}

		System.out.println("전체 양도글");

		for (int j = 0; j < transferCamp.size(); j++) {

			System.out.println(transferCamp.get(j).toString());

		}

		System.out.println("내 양도 정보");

		for (int j = 0; j < transferCamp.size(); j++) {

			if (transferCamp.get(j).getCampName().equals(MainMenu.ID)) {
				System.out.println(transferCamp.get(j).information());
			}

		}

//	public void myTransfer() {// 내 양도글 관리 메소드
//		// 전체 양도글 조회
//
//		System.out.println("====나의 양도글====");
//		myTransferList();
//		System.out.println("글 번호 입력 : ");
//		int boardNum = sc.nextInt();
//		boardNum += -1;
//		sc.nextLine();
//
//		System.out.println("1. 삭제");
//		System.out.println("2. 수정");
//		System.out.println("9. 이전 메뉴로");
//		int menu = sc.nextInt();
//		sc.nextLine();
//		switch (menu) {
//		case 1:
//			deleteTransfer(boardNum);
//			break;
//		case 2:
//			editTransfer(boardNum);
//			break;
//		case 9:
//			return;
//		default:
//			System.out.println("잘못 입력했습니다.");
//		}
//	}
//
//	public void deleteTransfer(int index) {// 양도글 삭제 메소드
//
//		CampInfo temp = loginTransferCamp.get(index);
//		System.out.println(temp);
//		System.out.println("정말 삭제하시겠습니까? (y/n)");
//		String yn = sc.nextLine();
//
////		String name = loginTransferCamp.get(index).getCampName();
////		for (int i = 0; i < cc.camp.size(); i++) {
////			if (cc.camp.get(i).getCampName().equals(name)) {
////				cc.camp.get(i).setReservId(" ");
////			}
////		}
//		ccInputId(temp.getCampName(), " ");
//		if (yn.equalsIgnoreCase("Y")) {
//			for (int i = 0; i < transferCamp.size(); i++) {
//				if (temp.equals(transferCamp.get(i))) {
//					transferCamp.get(i).setReservId(" ");
//					transferCamp.remove(i); // 전체 양도글 목록에서 삭제
//				}
//			}
//
//			loginReserve.add(temp); // 삭제할 양도글을 내 예약에 담기
//
//			logTCInputId(temp.getCampName(), MainMenu.ID);// 내 예약에목록에 아이디 추가
//
//			loginTransferCamp.get(index).setReservId(" ");
//			loginTransferCamp.remove(index);// 로그인된 아이디의 양도글 목록에서 삭제
//
//			System.out.println("삭제가 완료되었습니다.");
//			return;
//		} else {
//			System.out.println("삭제를 취소합니다.");
//		}
//		//진행상황 확인용
//		System.out.println("내 예약정보");
//		for (int i = 0; i < loginReserve.size(); i++) {
//			System.out.println(loginReserve.get(i));//내 예약정보 출력
//		}
//		System.out.println("내 양도정보");
//		for (int i = 0; i < loginTransferCamp.size(); i++) {
//			System.out.println(loginTransferCamp.get(i));//내 양도정보 출력
//		}
//		System.out.println("전체 양도글");
//		for (int i = 0; i < transferCamp.size(); i++) {
//			System.out.println(transferCamp.get(i));// 전체 양도글 출력
//		}
//		
//		System.out.println("전체 캠핑글");
//		for (int i = 0; i < cc.camp.size(); i++) {
//			System.out.println(cc.camp.get(i));
//		}
//	}
//
//	public void editTransfer(int index) {// 양도글 수정 메소드
//
//		System.out.println(loginTransferCamp.get(index));
//		System.out.println("이 글을 수정하시겠습니까? (y/n)");
//
//		String yn = sc.nextLine();
//		if (yn.equalsIgnoreCase("Y")) {
//
//			System.out.println("내 예약 목록");
//			myReserve();
//
//			System.out.println("수정할 예약 번호를 입력하세요 : ");
//			int num = sc.nextInt();
//			sc.nextLine();
//			num += -1;
//
//			CampInfo transferTemp = loginReserve.get(num);// 수정할 양도글
//			System.out.println(transferTemp.information()); // 양도할 캠핑장 정보 출력
//
//			System.out.println("위 예약을 양도하시겠습니까? (y/n)");
//			String yn1 = sc.nextLine();
//
//			if (yn1.equalsIgnoreCase("Y")) {
//				transferCamp.add(transferTemp); // 수정할 양도글을 전체 양도글 리스트에 담기
//				loginReserve.add(loginTransferCamp.get(index)); // 수정 전 양도글을 내 예약에 담기
////				for (int i = 0; i < loginReserve.size(); i++) {
////					if (loginReserve.get(i).getCampName().equals(loginTransferCamp.get(index).getCampName())) {
////						loginReserve.get(i).setReservId(MainMenu.ID);//
////					}
////				}
//				logRsInputId(loginTransferCamp.get(index).getCampName(), MainMenu.ID);
//				loginTransferCamp.add(transferTemp);// 내 양도글 리스트에 담기
////				for (int i = 0; i < loginTransferCamp.size(); i++) {
////					if (loginTransferCamp.get(i).getCampName().equals(transferTemp.getCampName())) {
////						loginTransferCamp.get(i).setReservId(MainMenu.ID);// 내 양도글에 아이디 담기
////					}
////				}
//
//				logTCInputId(transferTemp.getCampName(), MainMenu.ID);// 내 양도글에 아이디 담기
//
//				loginReserve.get(num).setReservId(" "); // 내 예약목록에서 새로 양도한것 삭제
//				loginReserve.remove(num);
////				for (int i = 0; i < loginReserve.size(); i++) {
////					if (loginReserve.get(i).getCampName().equals(loginTransferCamp.get(index).getCampName())) {
////						loginReserve.get(i).setReservId(MainMenu.ID);// 내 예약에 아이디 담기
////					}
////						
////				}
//
//				logRsInputId(loginTransferCamp.get(index).getCampName(), MainMenu.ID);
//				for (int i = 0; i < transferCamp.size(); i++) {
//					if (loginTransferCamp.get(index).getCampName().equals((transferCamp.get(i).getCampName()))) {
//						// transferCamp.get(i).setReservId(" ");// 수정 전 양도 전체 양도글 목록에서 삭제
//						transferCamp.remove(i);
//					}
//
//				}
//				tSCInputId(loginTransferCamp.get(index).getCampName(), " ");
//
//				loginTransferCamp.get(index).setReservId(" ");// 수정 전 양도 내 양도글 목록에서 삭제
//				loginTransferCamp.remove(index);
//				System.out.println("성공적으로 등록되었습니다.");
//
//			} else {
//				System.out.println("수정을 취소합니다.");
//			}
//
//		} else {
//
//			return;
//		}
//		//진행상황 확인용
//		System.out.println("내 예약정보");
//		for (int i = 0; i < loginReserve.size(); i++) {
//			System.out.println(loginReserve.get(i));//내 예약정보 출력
//		}
//		System.out.println("내 양도정보");
//		for (int i = 0; i < loginTransferCamp.size(); i++) {
//			System.out.println(loginTransferCamp.get(i));//내 양도정보 출력
//		}
//		System.out.println("전체 양도글");
//		for (int i = 0; i < transferCamp.size(); i++) {
//			System.out.println(transferCamp.get(i));// 전체 양도글 출력
//		}
//		
//		System.out.println("전체 캠핑글");
//		for (int i = 0; i < cc.camp.size(); i++) {
//			System.out.println(cc.camp.get(i));
//		}
//	}
//
//	public void myReserve() {// 내 예약 출력
//		loginReserve.clear();// 받기전 초기화
//		
//		System.out.println("내 예약 목록");
//
//		
//		for (int i = 0; i < cc.camp.size(); i++) {
//			if (cc.camp.get(i).getReservId().equals(MainMenu.ID)) {
//				loginReserve.add(cc.camp.get(i));
//			}
//		}
//
//		HashSet<CampInfo> list = new HashSet<CampInfo>(loginReserve);// HashSet을 이용해서 중복 제거하기
//		loginReserve = new ArrayList<CampInfo>(list);
//
//		for (int i = 0; i < loginReserve.size(); i++) {
//			System.out.println(i + 1 + ". " + loginReserve.get(i)); // 로그인된 아이디의 예약 목록 출력
//		}
//
//	}
//
//	public void myTransferList() {
//		System.out.println("내 양도 목록");
//		System.out.println("총 " + loginTransferCamp.size() + "개");
//		for (int i = 0; i < loginTransferCamp.size(); i++) {
//
//			System.out.println(i + 1 + " . " + loginTransferCamp.get(i)); // 로그인된 아이디의 양도 목록 출력
//		}
//
//		HashSet<CampInfo> list = new HashSet<CampInfo>(loginTransferCamp);// HashSet을 이용해서 중복 제거하기
//		loginTransferCamp = new ArrayList<CampInfo>(list);
//	}
//
//	public void logTCInputId(String campName, String id) {
//		for (int i = 0; i < loginTransferCamp.size(); i++) {
//			if (loginTransferCamp.get(i).getCampName().equals(campName)) {
//				loginTransferCamp.get(i).setReservId(id);// 내 양도글에 아이디 담기
//			}
//		}
//	}
//
//	public void logRsInputId(String campName, String id) {
//		for (int i = 0; i < loginReserve.size(); i++) {
//			if (loginReserve.get(i).getCampName().equals(campName)) {
//				loginReserve.get(i).setReservId(id);// 내 예약에 아이디 담기
//			}
//		}
//	}
//
//	public void tSCInputId(String campName, String id) {
//		for (int i = 0; i < transferCamp.size(); i++) {
//			if (transferCamp.get(i).getCampName().equals(campName)) {
//				transferCamp.get(i).setReservId(id);// 전체 양도글에 아이디 담기
//			}
//		}
//	}
//
//	public void ccInputId(String campName, String id) {
//		for (int i = 0; i < cc.camp.size(); i++) {
//			if (cc.camp.get(i).getCampName().equals(campName)) {
//				cc.camp.get(i).setReservId(id);// 전체 캠핑 리스트에 아이디 담기
//			}
//
//		}
//	}

	}

	public void tCampSaveFile() {

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

	
	public void tCampReadFile() {
		
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

				CampInfo c = new CampInfo(name, area, price, cc.setCalendar(year, month, day), reservId, state);
				
				transferCamp.add(c);

			}

		} catch (FileNotFoundException e) {
			
		}catch(EOFException e) {
			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
				
				
				
				
				
		
	}
	
	
}
