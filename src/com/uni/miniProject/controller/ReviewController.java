package com.uni.miniProject.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Scanner;

import com.uni.miniProject.model.comparator.AscReviewID;
import com.uni.miniProject.model.comparator.AscReviewTitle;
import com.uni.miniProject.model.comparator.DescReviewID;
import com.uni.miniProject.model.comparator.DescReviewTitle;
import com.uni.miniProject.model.vo.Write;
import com.uni.miniProject.view.MainMenu;

public class ReviewController {
	ArrayList<Write> mWrite = new ArrayList<Write>();

	Scanner sc = new Scanner(System.in);
	Write wr = new Write();
	static ArrayList<Write> rWrite = new ArrayList<Write>();
	CampingController cc = new CampingController();


	public ReviewController() {

	public void reviewWrite() {
		 

	}

	public void ReviewList() {
		System.out.println("----");
		int index = 0;
		for (Write wr : rWrite) {
			System.out.println("[글 번호 " + (index + 1) + "] " + wr.information());
			index++;
		}
	}

	public void ReviewRead() {// 리뷰 파일 읽기

		File f = new File("Review.txt");
		ArrayList temp = null;

		try (BufferedReader br = new BufferedReader(new FileReader(f.getName()))) {
			String tem = null;

			while ((tem = br.readLine()) != null) {

				String[] arr = tem.split(",");

				String title = arr[0];
				String content = arr[1];
				String[] arr2 = arr[2].split("-");
				String id = arr[3];

				int year = Integer.valueOf(arr2[0]);
				int month = Integer.valueOf(arr2[1]);
				int day = Integer.valueOf(arr2[2]);

				CampingController cc = new CampingController();
				Write w = new Write(title, content, cc.setCalendar(year, month, day), id);

				rWrite.add(w);
			}
		} catch (FileNotFoundException e) {
			// System.out.println("리뷰저장 파일이 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public void ReviewSave() {// 리뷰파일 저장하기

		File ex = new File("Review.txt");

		if (ex.exists()) {
			ex.delete();
		}

		File f = new File("Review.txt");

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(f.getName()))) {

			for (int i = 0; i < rWrite.size(); i++) {
				bw.write(rWrite.get(i).toStrFile());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ReviewWrite() {
		System.out.println("===리뷰 글 쓰기===");

		System.out.println("제목 : ");
		String title = sc.nextLine();

		System.out.println("내용 : ");
		String content = sc.nextLine();

		Calendar cal = Calendar.getInstance();

		wr = new Write(title, content, cal, MainMenu.ID);
		rWrite.add(wr);
		
		int lastindex = rWrite.size()-1;
		
		mWrite.add(rWrite.get(lastindex));

		System.out.println("리뷰 작성이 완료되었습니다.");
	}

	public void ReviewSearch() {
		
		
		System.out.println("[ 등록된 리뷰 목록 ]");
		ReviewList();

		while (true) {
			System.out.println("===리뷰 게시글 검색===");
			System.out.println("검색할 제목 입력 : ");
			String head = sc.nextLine();

			System.out.println("===검색 결과===");

			for (int i = 0; i < rWrite.size(); i++) {
				if (rWrite.get(i).getTitle().contains(head)) {
					System.out.println(rWrite.get(i).information());
				}
			}

			while (true) {
				System.out.println("계속 검색하시겠습니까?(y/n) : ");
				char ch = sc.nextLine().toLowerCase().charAt(0);

				if (ch == 'y') {
					break;
				} else if (ch == 'n') {
					System.out.println("이전 메뉴로 돌아갑니다.");
					return;
				} else {
					System.out.println("정확히 입력해주세요.");
				}
			}
		}
	}

	public void ReviewSort() {
		while (true) {
			System.out.println("===리뷰 글 정렬===");
			System.out.println("1. 제목 오름차순 정렬");
			System.out.println("2. 제목 내림차순 정렬");
			System.out.println("3. 아이디 오름차순 정렬");
			System.out.println("4. 아이디 내림차순 정렬");
			System.out.println("9. 이전 메뉴로");
			System.out.println("메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();

			if (menu == 1) {
				AscReviewTitle();
			} else if (menu == 2) {
				DescReviewTitle();
			} else if (menu == 3) {
				AscReviewTitle();
			} else if (menu == 4) {
				DescReviewID();
			} else if (menu == 9) {
				return;
			} else {
				System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
				break;
			}
		}
	}

	public void MyReview() {

		if (mWrite.isEmpty()) {
			for (int i = 0; i < rWrite.size(); i++) {
				if (rWrite.get(i).getUserId().equals(MainMenu.ID)) {
					mWrite.add(rWrite.get(i));
				}
			}
		}
		while (true) {
			System.out.println("===내 글 조회===");

			if (mWrite.isEmpty()) {
				System.out.println("작성된 글이 없습니다.");
				return;
			}
			for (int i = 0; i < mWrite.size(); i++) {
				System.out.println(i + 1 + ". " + mWrite.get(i).toString());
			}

			System.out.println("삭제 및 수정할 번호를 입력해주세요 : ");
			int index = sc.nextInt();
			sc.nextLine();
			if (index == 0) {
				break;
			}
			index--;

			System.out.println("1. 내 글 삭제");
			System.out.println("2. 내 글 수정");

			int num = sc.nextInt();
			sc.nextLine();
			System.out.println(mWrite.get(index).toString());

			switch (num) {
			case 1:
				System.out.println("이 글을 삭제하시겠습니까? (y/n)");
				String check = sc.nextLine();

				if (check.equalsIgnoreCase("Y")) {
					for (int i = 0; i < rWrite.size(); i++) {
						if (rWrite.get(i).equals(mWrite.get(index))) {
							rWrite.remove(i);
						}
					}
					mWrite.remove(index);
					System.out.println("삭제가 완료되었습니다.");
				} else {
					System.out.println("삭제를 취소합니다.");
				}
				break;

			case 2:
				System.out.println("이 글을 수정하시겠습니까? (y/n)");
				String check2 = sc.nextLine();

				if (check2.equalsIgnoreCase("Y")) {
					System.out.println("수정할 제목 : ");
					String edit1 = sc.nextLine();
					for (int i = 0; i < rWrite.size(); i++) {
						if (rWrite.get(i).equals(mWrite.get(index))) {
							rWrite.get(i).setTitle(edit1);
						}
					}
					mWrite.get(index).setTitle(edit1);

					System.out.println("수정할 내용 : ");
					String edit2 = sc.nextLine();
					for (int i = 0; i < rWrite.size(); i++) {
						if (rWrite.get(i).equals(mWrite.get(index))) {
							rWrite.get(i).setContent(edit2);
						}
					}
					mWrite.get(index).setContent(edit2);
					System.out.println("수정이 완료되었습니다.");
					break;
				} else {
					System.out.println("수정을 취소합니다.");
				}
			}
			break;
		}
	}

	public void AscReviewTitle() { // 제목 오름차순 정렬
		Collections.sort(rWrite, new AscReviewTitle());
		ReviewList();
	}

	public void DescReviewTitle() {
		Collections.sort(rWrite, new DescReviewTitle());
		ReviewList();
	}

	public void AscReviewID() { // 아이디 오름차순 정렬
		Collections.sort(rWrite, new AscReviewID());
		ReviewList();
	}

	public void DescReviewID() {
		Collections.sort(rWrite, new DescReviewID());
		ReviewList();
	}
}
