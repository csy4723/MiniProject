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

import com.uni.miniProject.model.vo.Member;


public class UserController {

	Scanner sc = new Scanner(System.in);
	Member m = new Member();
	ArrayList<Member> user = new ArrayList<Member>();// 유저정보 담들것

	public UserController() {
		// TODO Auto-generated constructor stub
	}

	public String signUp() { // 회원가입

		System.out.println("회원 가입");
		String id = "";
		String pw = "";
		boolean b = true;
		
		while (b) {

			System.out.println("아이디와 비밀번호를 입력하세요 ");
			System.out.println("아이디 입력 : ");
			id = sc.nextLine();
			System.out.println("비밀번호 입력 : ");
			pw = sc.nextLine();

			if (user.size() == 0) { // 담긴 값이 없으면 등록
				b = false;
			} else{ // 담긴값이 있으면
				for (Member u : user) { // user를 반복해 실행
					
					if (!u.getUserId().equals(id)) { // 입력받은 id와 가져온 userid가 일치하지않으면 회원 등록
						b = false;
					}

				}
			}
			if(b) {
				System.out.println("중복된 아이디가 있습니다. 다시입력해주세요");	
			}
			

		}

		System.out.println("이름을 입력하세요 : ");
		String name = sc.nextLine();
		System.out.println("나이를 입력하세요 : ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.println("성별을 입력하세요 :(m/f)");
		char gender = sc.nextLine().charAt(0);

		System.out.println("이메일을 입력하세요 : ");
		String email = sc.nextLine();

		System.out.println("회원가입이 완료 되었습니다 " + id + "님 환영합니다.");
		
		Member m = new Member(id, pw, name, age, gender, email);
		
		user.add(m);
		
		return id;

		/*
		 * if (idCheck(id)) { System.out.println("사용중인 ID입니다."); return null; } else {
		 * System.out.println(id + "님 환영합니다."); u = new UserInfo(id, pw); 
		 * user.add(u);// 회원목록 리스트에 담기
		 * 
		 * return u; }
		 */

		
		
	}



//	public UserInfo logIn() { // 로그인
//
//		System.out.println("아이디와 비밀번호를 입력해주세요");
//		System.out.println("ID 입력 : ");
//		String id = sc.nextLine();
//
//		System.out.println("PW 입력 : ");
//		String pw = sc.nextLine();
//
//		u = FindbyID(id);
//		if (u == null) {
//			System.out.println("등록되지않은 아이디입니다");
//			
//			return null;
//		} else if (u.getPw().equals(pw)) {
//			System.out.println(u.getId() + " 님 로그인 되었습니다.");
//			
//			return u;
//		} else {
//			System.out.println("아이디 혹은 비밀번호를 다시 확인해주세요");
//			return null;
//		}
//		
//	}

	public String logIn() {
		String id = "";
		String pw = "";
		
		
		boolean b = true;
		while (b) {

			System.out.println("아이디와 비밀번호를 입력하세요 ");
			System.out.println("아이디 입력 : ");
			id = sc.nextLine();
			System.out.println("비밀번호 입력 : ");
			pw = sc.nextLine();

			if (user.size() == 0) { // 담긴값이 없을경우
				System.out.println("등록된 아이디가 없습니다");
				b = false;
			} else { //
				for (Member u : user) {
					if (!u.getUserId().equals(id) || !u.getUserPwd().equals(pw)) {
						b = true;
					} else if (u.getUserId().equals(id) && u.getUserPwd().equals(pw)) {
						System.out.println(id + "님 로그인 되었습니다.");
						b = false;
					}

				}
			}
			if (b) {
				
				System.out.println("아이디 혹은 비밀번호를 다시확인해 주세요");
			}


		}

		return id;
	}

	public void userDelete() {// 회원 탈퇴

		System.out.println("탈퇴할 아이디를 입력해주세요");
		String deleteid = sc.nextLine();

		for(Member mem : user) {
			if(mem.getUserId().equals(deleteid)) {
				user.remove(mem);
				break;
			}
		}
		System.out.println("회원탈퇴가 완료되었습니다.");

	}

	public void pointCharge() { // 포인트 충전부
		
		int point = 0;
		
		System.out.println("포인트 충전 ");
		System.out.println("충전할 금액을 입력해 주세요 : ");
		point = sc.nextInt();
		
		System.out.println(point + "포인트가 충전 되었습니다.");
		
		m.setPoint(point);
		
		
	}

	private boolean idCheck(String id) { // 중복된 아이디 있는지 체크
		boolean check = true;
		m = FindbyID(id);
		if (m == null)
			check = false;

		return check;
	}

	private Member FindbyID(String id) { // 리스트에서 아이디 찾기
		for (Member u : user) {
			if (u.getUserId().equals(id)) {
				return u;
			}
		}
		return null;
	}
	
	public void updateUser() {
		
		while(true) {
			
			System.out.println("개인정보 수정 ");
			System.out.println("1. 비밀번호 수정 ");
			System.out.println("2. 이름 수정 ");
			System.out.println("3. 나이 수정 ");
			System.out.println("4. 이메일 수정 ");
			System.out.println("9. 이전 메뉴로 ");
			
			System.out.println("메뉴를 선택해주세요 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			if(menu == 9) {
				System.out.println("이전메뉴로 돌아갑니다");
				return;
			}
			
			System.out.println("아이디를 입력해 주세요 : ");
			String id = sc.nextLine();
			
			m = FindbyID(id);
			if(m == null) {
				System.out.println("등록되지 않은 회원입니다.");
				
			}else {
				System.out.println("기존정보 : " + m.info());
				
				
				System.out.println("변경 내용 : ");
				String update = sc.nextLine();
				
				m.updateMember(m, menu, update); // 1번 : 비밀번호 , 2번 : 이름 , 3번 : 나이
				
				System.out.println("회원정보가 변경되었습니다.");
			}
			
			
		}
	}
	
	public void serchUser() { // 개인정보 조회
		
		System.out.println("개인정보 조회");
		System.out.println("============================");
		System.out.println("아이디를 입력해주세요 : ");
		String id = sc.nextLine();
		
		for(int i=0;i<user.size();i++) {
			if(id.equals(user.get(i).getUserId())) {
				System.out.println(user.get(i).getUserId() + " " + user.get(i).getUserPwd() +" " + user.get(i).getName() +" "
						+ user.get(i).getAge() + " " + user.get(i).getGender() + " " + user.get(i).getEmail() + " " + user.get(i).getPoint());
					
			}else {
				System.out.println("다시확인해주세요");
			}
			
		}
	
	}
	
	
	public void userSaveFile() {// 종료할때 저장하는 메소드
		
	File ex = new File("User list");
	
	if(ex.exists()) {
		
	boolean b =	ex.delete();
	System.out.println(b);// 지워졌는지 확인
	}
	
	File f = new File("User list.txt");
	
	try(BufferedWriter bw = new BufferedWriter(new FileWriter(f.getName()))){

		for(int i = 0; i< user.size(); i++) {
			bw.write(user.get(i).toString());///주소값이 저장됨
			bw.newLine();
		}
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	
	public void userReadFile() {
		
		File f = new File("User list.txt");
		ArrayList tem = null;
				
				try(BufferedReader br = new BufferedReader(new FileReader(f.getName()))) {
					
					String temp = null;
					while((temp = br.readLine()) != null) {
						
						String[] arr = temp.split(",");
						
						String id = arr[0];
						String pwd = arr[1];
						String name = arr[2];
						int age = Integer.valueOf(arr[3]);
						char gender = arr[4].charAt(0);
						String email = arr[5];
						int point = Integer.valueOf(arr[6]);
						
						Member m = new Member(id, pwd, name, age, gender, email, point);
						
						user.add(m);
						
				
					}
					
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch(EOFException e){
				
	}catch (IOException e) {
					// TODO Auto-generated catch block
					
				}
		
		
	}
	

}
