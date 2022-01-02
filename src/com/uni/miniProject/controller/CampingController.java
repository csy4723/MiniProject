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
import com.uni.miniProject.view.MainMenu;

public class CampingController {
	public ArrayList<CampInfo> camp = new ArrayList<CampInfo>();// 캠핑 예약 상품 담을 리스트
	Scanner sc = new Scanner(System.in);
	CampInfo cinfo = new CampInfo();
	FileOutputStream fos = null;
	MainMenu mm = new MainMenu();
	

	public CampingController() {
		// TODO Auto-generated constructor stub
	}

	public void campRegister() {// 캠핑장 등록 메소드

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
		cDate.set(year, month-1, day);

		cinfo = new CampInfo(Name, Area, price, cDate);

		camp.add(cinfo);// 여기까지 ArrayList에 캠핑목록 담는 거

	}

	public void campList() {//캠핑장 조회 메소드

		for(int i = 0; i<camp.size(); i++) {
			
			if(camp.get(i).getReservId().isBlank()) {//캠핑객체의 예약 아이디가 비어있으면 true반환
				System.out.println((i+1)+"번 : "+camp.get(i).information());
			}
			
		}
		
	}

	public void campDelete() {//캠핑장 삭제 메소드

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

	public void campRead() {// 프로그램 실행할 때 파일에 있던 객체를 ArrayList에 담아주는 메소드

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

				int year = Integer.valueOf(arr2[0]);
				int month = Integer.valueOf(arr2[1]);
				int day = Integer.valueOf(arr2[2]);

				CampInfo c = new CampInfo(name, area, price, setCalendar(year, month-1, day));
				
				camp.add(c);

			}

		} catch (FileNotFoundException e) {
			System.out.println("현재 등록된 캠핑장 수 : 0");
		}catch(EOFException e) {
			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
	}
	
	
	
	public void campWrite() {// 프로그램 종료할때마다 파일 출력하는 메소드
		
		File folder = new File("C:\\Camping List");
		
		if(!folder.exists()) {
			folder.mkdir();
		}
		
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

	public Calendar setCalendar(int year, int month, int day) {//날짜 메소드

		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);

		return cal;
	}
	
	public void reservCamp(int num) {//예약 할때 메소드 
		
		System.out.println(num+"번 : "+ camp.get(num-1));
		camp.get(num-1).setReservId(mm.ID);// 현재 로그인 중인 아이디를 set
		System.out.println("예약이 완료되었습니다. ");
		
		
	}

}
