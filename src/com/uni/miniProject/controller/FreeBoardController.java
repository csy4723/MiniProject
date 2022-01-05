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
import java.util.Calendar;
import java.util.Scanner;

import com.uni.miniProject.model.comparator.AscFreeId;
import com.uni.miniProject.model.comparator.AscFreeTitle;
import com.uni.miniProject.model.comparator.DescFreeId;
import com.uni.miniProject.model.comparator.DescFreeTitle;
import com.uni.miniProject.model.vo.CampInfo;
import com.uni.miniProject.model.vo.Write;
import com.uni.miniProject.view.MainMenu;

public class FreeBoardController {
	Scanner sc = new Scanner(System.in);

	Write wr = new Write();
	static ArrayList<Write> freeBoard = new ArrayList<Write>();// 자유게시판 전체 글들을 담을 리스트
	ArrayList<Write> myFree = new ArrayList<Write>();// 로그인된 아이디의 글들만 담을 리스트

	public FreeBoardController() {
	}

	public void searchFree() {
		if (!freeBoard.isEmpty()) {
			while (true) {

				System.out.println("1. 제목검색");
				System.out.println("2. 내용검색");
				System.out.println("3. 제목|내용 통합검색");
				System.out.println("4. 아이디 검색");
				System.out.println("9. 이전 메뉴로");
				int num = sc.nextInt();
				sc.nextLine();
				if (num == 9) {
					break;
				}
				ArrayList<Write> temp = new ArrayList<Write>();
				System.out.println("키워드 입력");
				String keyword = sc.nextLine();

				switch (num) {

				case 1:
					for (int i = 0; i < freeBoard.size(); i++) {
						if (freeBoard.get(i).getTitle().contains(keyword)) {// 제목검색
							temp.add(freeBoard.get(i));
						}
					}
					break;
				case 2:
					for (int i = 0; i < freeBoard.size(); i++) {
						if (freeBoard.get(i).getContent().contains(keyword)) {// 내용검색
							temp.add(freeBoard.get(i));
						}
					}
					break;
				case 3:
					for (int i = 0; i < freeBoard.size(); i++) {
						if (freeBoard.get(i).getTitle().contains(keyword)// 통합검색
								|| freeBoard.get(i).getContent().contains(keyword)) {
							temp.add(freeBoard.get(i));
						}
					}
					break;
				case 4:
					for (int i = 0; i < freeBoard.size(); i++) {
						if (freeBoard.get(i).getUserId().contains(keyword)) {// 아이디검색
							temp.add(freeBoard.get(i));
						}
					}
					break;
				default:
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
				}
				if (temp.isEmpty()) {
					System.out.println("검색 결과가 없습니다.");
				} else {
					System.out.println("===검색 결과===");
					for (int i = 0; i < temp.size(); i++) {
						System.out.println(temp.get(i).toString());
					}
				}
			}
		} else {
			System.out.println("아직 아무글도 작성되지 않았습니다.");
		}

	}

	public void freeSort() {

		while (true) {
			System.out.println("1. 제목 오름차순 정렬");
			System.out.println("2. 제목 내림차순 정렬");
			System.out.println("3. 아이디 오름차순 정렬");
			System.out.println("4. 아이디 내림차순 정렬");
			System.out.println("9. 이전 메뉴로");

			int num = sc.nextInt();
			sc.nextLine();
			switch (num) {
			case 1:
				freeBoard.sort(new AscFreeTitle());// 제목 오름차순
				System.out.println("정렬이 완료되었습니다.");
				return;
			case 2:
				freeBoard.sort(new DescFreeTitle());// 제목 내림차순
				System.out.println("정렬이 완료되었습니다.");
				return;
			case 3:
				freeBoard.sort(new AscFreeId());// 아이디 오름차순
				System.out.println("정렬이 완료되었습니다.");
				return;
			case 4:
				freeBoard.sort(new DescFreeId());// 아이디 내림차순
				System.out.println("정렬이 완료되었습니다.");
				return;
			case 9:
				System.out.println("이전 메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			}
		}

	}

	public void myFree() {

		if (myFree.isEmpty()) {
			for (int i = 0; i < freeBoard.size(); i++) {
				if (freeBoard.get(i).getUserId().equals(MainMenu.ID)) {// 전체 게시판에서 로그인된 아이디와 같은 글이 있을시
					myFree.add(freeBoard.get(i)); // 해당 글을 내 글 목록에 담기
				}
			}
		}
		while (true) {
			System.out.println("===내 글 조회===");
			if (myFree.isEmpty()) {
				System.out.println("작성된 글이 없습니다.");
				return;
			}
			for (int i = 0; i < myFree.size(); i++) {
				System.out.println(i + 1 + ". " + myFree.get(i).toString());// 내 글 리스트 출력
			}
			System.out.println("글 번호 입력 : (이전메뉴 : 0)");
			int index = sc.nextInt();
			sc.nextLine();
			if (index == 0) {
				break;
			}
			index--;
			System.out.println("1. 삭제");
			System.out.println("2. 수정");
			int num = sc.nextInt();
			sc.nextLine();
			System.out.println(myFree.get(index).toString());
			switch (num) {
			case 1:

				System.out.println("위 글을 삭제하시겠습니까? (y/n)");
				String yn = sc.nextLine();

				if (yn.equalsIgnoreCase("Y")) {

					for (int i = 0; i < freeBoard.size(); i++) {
						if (freeBoard.get(i).equals(myFree.get(index))) {
							freeBoard.remove(i);// 전체 글에서 삭제
						}
					}
					myFree.remove(index);// 로그인된 아이디의 글에서 삭제
					System.out.println("삭제가 완료되었습니다.");
				} else {
					System.out.println("삭제를 취소합니다.");
				}
				break;

			case 2:
				System.out.println("위 글을 수정하시겠습니까? (y/n)");
				String yn1 = sc.nextLine();

				if (yn1.equalsIgnoreCase("Y")) {
					System.out.println("수정할 내용선택 ");
					System.out.println("1. 제목");
					System.out.println("2. 내용");

					int num1 = sc.nextInt();
					sc.nextLine();
					switch (num1) {
					case 1:
						System.out.println("수정할 제목 입력 : ");
						String editTitle = sc.nextLine();
						for (int i = 0; i < freeBoard.size(); i++) {
							if (freeBoard.get(i).equals(myFree.get(index))) {
								freeBoard.get(i).setTitle(editTitle);// 전체 글에서 제목 수정
							}
						}

						myFree.get(index).setTitle(editTitle);// 로그인된 아이디의 글에서 제목 수정
						break;

					case 2:
						System.out.println("수정할 내용 입력 : .");
						String editContent = sc.nextLine();
						for (int i = 0; i < freeBoard.size(); i++) {
							if (freeBoard.get(i).equals(myFree.get(index))) {
								freeBoard.get(i).setContent(editContent);// 전체 글에서 내용 수정
							}
						}

						myFree.get(index).setContent(editContent);// 로그인된 아이디의 글에서 내용 수정
						break;
					}
				} else {
					System.out.println("수정을 취소합니다.");
				}

			}
			break;
		}

	}

	public void postFree() {

		System.out.println("글 제목 : ");
		String title = sc.nextLine();

		System.out.println("내용 : ");
		String content = sc.nextLine();

		Calendar cal = Calendar.getInstance();

		wr = new Write(title, content, cal, MainMenu.ID);

		freeBoard.add(wr);
		System.out.println("성공적으로 등록되었습니다.");

	}

	public void allFree() {
		System.out.println("===전체 글 목록===");
		for (Write w : freeBoard) {
			System.out.println(w);// 전체 글 리스트 출력
		}
	}

	public Calendar setCalendar(int year, int month, int day) {

		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);

		return cal;
	}

	public void freeBoardRead() {// 파일에 있던 객체를 ArrayList에 담아주는 메소드

		File f = new File("freeBoard.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(f.getName()))) {

			String temp = null;
			while ((temp = br.readLine()) != null) {

				String[] arr = temp.split(",");

				String title = arr[0];
				String content = arr[1];
				String userId = arr[3];

				String[] arr2 = arr[2].split("-");

				int year = Integer.valueOf(arr2[0]);
				int month = Integer.valueOf(arr2[1]);
				int day = Integer.valueOf(arr2[2]);

				Write w = new Write(title, content, setCalendar(year, month, day), userId);

				freeBoard.add(w);

			}

		} catch (FileNotFoundException e) {

		} catch (EOFException e) {

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void freeBoardWrite() {// 프로그램 종료할때 저장하는 거

		File ex = new File("freeBoard.txt");

		if (ex.exists()) {
			ex.delete();
		}

		File f = new File("freeBoard.txt");

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(f.getName()))) {

			for (int i = 0; i < freeBoard.size(); i++) {

				bw.write(freeBoard.get(i).toStrFile());
				bw.newLine();

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
