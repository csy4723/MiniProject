package com.uni.miniProject.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserController {

	Scanner sc = new Scanner(System.in);

	public void SignUp() {
		File folder = new File("C:\\UserDB");
		folder.mkdir();
		File f = new File("C:\\UserDB\\UserDB.txt"); // 파일 생성
		
		

		try {
			FileWriter fw = new FileWriter(f, true);
			if (f.isFile() && f.canWrite()) {
				System.out.println("아이디와 비밀번호를 입력하세요");
				System.out.println("ID 입력 : ");
				fw.append(sc.nextLine()); // filewriter 에 내용 추가
				fw.append("\t"); // 탭으로 구분
				System.out.println("PW 입력 : ");
				fw.append(sc.nextLine());
				fw.append("\r"); // 개행
				System.out.println("회원가입이 완료되었습니다.");

			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void login() {
		int pass=0;
		
		System.out.println("아이디와 비밀번호를 입력해주세요");
		System.out.println("ID 입력 : ");
		String id = sc.nextLine();
		
		System.out.println("PW 입력 : ");
		String pw = sc.nextLine();
		
		File f = new File("C:\\UserDB\\UserDB.txt");
		
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr); // filereader를 통해 읽어온 내용을 가지고잇음
			String line = ""; 
			
			while((line = br.readLine()) != null) {// line 에 들어오는 내용이 없어질때까지 반복
				
				System.out.println(line.indexOf(id) +" " + line.indexOf(pw)); // 확인용 출력문
				int passId = line.indexOf(id);
				int passPw = line.indexOf(pw);
				
				if(passId != -1 && passPw != -1) { // indexof로 값을 읽어와서 passid와 passpw가 -1값이 아니라면 로그인성공
					System.out.println("로그인");
					pass=1;
				}
				
				
			}if (pass == 0){
				System.out.println("아이디 혹은 비밀번호를 다시확인해주세요");
			}br.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
