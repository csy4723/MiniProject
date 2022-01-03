package com.uni.miniProject.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
 
import com.uni.miniProject.model.vo.CampInfo;
import com.uni.miniProject.model.vo.Member;
import com.uni.miniProject.view.MainMenu;

public class CampingController {
	ArrayList<CampInfo> camp = new ArrayList<CampInfo>();// 캠핑 예약 상품 담을 리스트
	Scanner sc = new Scanner(System.in);
	CampInfo cinfo = new CampInfo();
	FileOutputStream fos = null;
	Member mem = new Member();

	public CampingController() {
		// TODO Auto-generated constructor stub
	}

	public void campRegister() {

		System.out.println("캠핑장 이름 : ");
		String Name = sc.nextLine();

		System.out.println("캠핑장 지역 : ");
		String Area = sc.nextLine();

		System.out.println("가격 : ");
		int price = sc.nextInt();
		sc.nextLine();
 
		System.out.println("이용 날짜(yyyy-MM-dd) : ");
		String date = sc.nextLine();
		String[] dArr = date.split("-");

		int year = Integer.valueOf(dArr[0]);
		int month = Integer.valueOf(dArr[1]);
		int day = Integer.valueOf(dArr[2]);

		Calendar cDate = Calendar.getInstance();
		cDate.set(year, month, day);

		cinfo = new CampInfo(Name, Area, price, cDate);

		camp.add(cinfo);// 여기까지 ArrayList에 캠핑목록 담는 거
		
		

	}

	public void campList() {
	
		for(int i = 0; i<camp.size(); i++) {
			if(camp.get(i).getReservId().isBlank()) {
				
				System.out.println(camp.get(i).information());
			}
		}
		
	}

	public void campDelete() {

		System.out.println("삭제할 캠핑장 이름을 입력하세요");
		String name = sc.nextLine();

		for (int i = 0; i < camp.size(); i++) {

			if (name.equals(camp.get(i).getCampName())) {
				camp.remove(i);
				return;
			}

		}
		System.out.println("삭제할 캠핑장이 없습니다.");
	}

	public void campRead() {// 파일에 있던 객체를 ArrayList에 담아주는 메소드

		File f = new File("camping List.txt");
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

				CampInfo c = new CampInfo(name, area, price, setCalendar(year, month, day), reservId);
				
				camp.add(c);

			}

		} catch (FileNotFoundException e) {
			
		}catch(EOFException e) {
			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
	}
	
	
	
	public void campWrite() {// 프로그램 종료할때 저장하는 거 
		
		File folder = new File("C:\\Camping List");
		
		if(!folder.exists()) {
			folder.mkdir();
		}/// 없어도 될듯?
		
		File ex = new File(folder, "Camping List.txt");
		
		if(ex.exists()) {
			ex.delete();
		}
		
		
		File f = new File("camping List.txt");
		
	try(BufferedWriter bw = new BufferedWriter(new FileWriter(f.getName()))){
		
		for(int i = 0; i < camp.size(); i++) {
			
			bw.write(camp.get(i).toString());
			bw.newLine();
			
		}
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
	}

	public Calendar setCalendar(int year, int month, int day) {

		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);

		return cal;
	}
	
	
	public void reservation(String res) {//예약 메소드->검색메소드에서 해당 캠핑장 인덱스 넘겨받아야 됨
		
		
		System.out.println("예약하시겠습니까? (y/n)");
		String yn = sc.nextLine();
		
		if(yn.equalsIgnoreCase("N")) {
			return;
		}
		
		int i = 0;
		for(CampInfo c : camp) {
			
			if(c.getCampName().equals(res)) {
				break;
			}
			i++;
		}// 이름이 일치하는 캠핑장 인덱스 추출
		
		
		
		int result = payment(res, i);
		
		if(result == 0) {
			System.out.println("현재 포인트가 부족합니다.");
		}else if(result == 1) {
			
			
			
			camp.get(i).setReservId(MainMenu.ID);
		
		System.out.println(MainMenu.ID+"님이 "+camp.get(i).getCampName()+"을 예약했습니다.");
		System.out.println("잔여 포인트 : "+ mem.getPoint());
		System.out.println("조회 밎 취소는 마이페이지에서 하실 수 있습니다.");
			
		}
		
	}
	
	private int payment(String res, int num) {// 포인트 결제 
		
		int cP= camp.get(num).getCampPrice();
		int mP = mem.getPoint(); // 현재 회원의 포인트 
		
		int result = 0;
		if(mP < cP) {
			result =  0;
		}else if(mP >= cP) {
		 result = 1; 
		 mem.setPoint(mP - cP); 
		}
		
		return result;
	}

	public void cancleReserv() {
		System.out.println("취소할 예약을 선택해주세요");
		
		for(CampInfo c : camp) {
			if(c.getReservId().equals(MainMenu.ID)) {
				System.out.println(c.information());
				
			}
		}
		
		System.out.println("캠핑장 이름 : ");
		String name = sc.nextLine();
		
		
		for(CampInfo c : camp) {
			
			if(c.getCampName().equals(name)) {
			
				c.setReservId(" ");
				System.out.println("성공적으로 취소되었습니다.");
				return;
			}
			
		}
		
		System.out.println("입력한 이름과 일치하는 예약정보가 없습니다.");
		
		
		
	}
	
	public void campSeach() {
		System.out.println("[등록된 캠핑장 목록]");
		campList();
		
		System.out.println("===캠핑장 검색===");
		System.out.println("키워드 입력 : ");
		String keyword = sc.nextLine();
		
		camp.contains(keyword);
//		String search = camp.get(0).get(keyword).toString();
		
		System.out.println("검색 결과: ");
		//SeachKeyword();
		
		System.out.println("어느 캠핑장으로 예약하시겠습니까? : ");
		String res = sc.nextLine();
		sc.nextLine();
		
		reservation(res);
	}

	
	
	
	
	
	

}
