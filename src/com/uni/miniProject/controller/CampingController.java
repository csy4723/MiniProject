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

public class CampingController {
	ArrayList<CampInfo> camp = new ArrayList<CampInfo>();// 캠핑 예약 상품 담을 리스트
	Scanner sc = new Scanner(System.in);
	CampInfo cinfo = new CampInfo();
	FileOutputStream fos = null;

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
			System.out.println(camp.get(i).information());
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

				int year = Integer.valueOf(arr2[0]);
				int month = Integer.valueOf(arr2[1]);
				int day = Integer.valueOf(arr2[2]);

				CampInfo c = new CampInfo(name, area, price, setCalendar(year, month, day));
				
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
	
	
	
	public void campWrite() {
		
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

	Calendar setCalendar(int year, int month, int day) {

		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);

		return cal;
	}

}
