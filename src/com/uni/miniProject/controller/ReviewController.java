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
import java.util.Scanner;

import com.uni.miniProject.model.vo.Write;
import com.uni.miniProject.view.MainMenu;

public class ReviewController {

	Scanner sc = new Scanner(System.in);
	Write wr = new Write();
	ArrayList<Write> rWrite = new ArrayList<Write>();
	CampingController cc = new CampingController();
	
	{
		rWrite.add(new Write("무릉도원 캠핑장 너무 좋았어요~", "주변에 볼거리도 많고 재밌었어요!", cc.setCalendar(2021, 11, 11), "csy4723"));
		rWrite.add(new Write("토일관광농원 - 아이들이랑 갔다오기 좋은 캠핑장", "캠핑장 내에 수영장도 있고 방방이도 있어서 아이들과 재밌게 즐기고 왔네요^^",cc.setCalendar(2021, 12, 12), "qwp57"));
		rWrite.add(new Write("가족들과 함께 새해 맞이할 겸 오색장군바위 캠핑장 다녀왔습니다.", "산 근처 캠핑장이라 나무들이 많아 공기가 좋았습니다.",cc.setCalendar(2022, 01, 01), "chkm306"));
		rWrite.add(new Write("산들바다관광농원에서 인생샷 건졌네요", "바다 근처에 산책로가 있어서 사진 왕창 찍고왔어요! 풍경이 이뻐요 강추~",cc.setCalendar(2022, 01, 04), "ggu-min"));
	}

	public ReviewController() {
	}

	public void ReviewList() {
		int index = 0;
		for (Write wr : rWrite) {
			System.out.println("[글 번호 " + (index + 1) + "] " + wr);
			index++;
		}
	}

	/*	public void ReviewPrint() { // 출력문
		for (int i = 0; i < rWrite.size(); i++) {
			System.out.println(rWrite.get(i).toString());
		}
	}*/

	public void ReviewSave() {// 리뷰 파일에 저장하기
		/*File file = new File("Review List.txt");
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		File file = new File("Camping List.txt");

		if (file.exists()) {
			file.delete();
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Review List.txt"))) {
			for (int i = 0; i < rWrite.size(); i++) {
				bw.write(rWrite.get(i).information());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*public void ReviewSave() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Review List.txt", true))) {
			oos.writeObject(rWrite);// 파일에 ArrayList 저장
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	public void ReviewRead() {// 리뷰 파일 읽기

		try (BufferedReader br = new BufferedReader(new FileReader("Review List.txt"))) {
			String temp = null;
			while ((temp = br.readLine()) != null) {
				System.out.println(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*public void ReviewRead() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Review List.txt"))) {
			ArrayList<ReviewWrite> rWrite = (ArrayList<ReviewWrite>) ois.readObject(); // 파일에서 ArrayList읽기
			System.out.println(rWrite.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}*/

	public String ReviewDate() {
		
		/*File file = new File("Review List.txt");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
		return sdf.format(new Date(file.lastModified()));*/
	}

	public void ReviewWrite() {
		System.out.println("===리뷰 글 쓰기===");

		System.out.println("제목 : ");
		String title = sc.nextLine();

		System.out.println("내용 : ");
		String content = sc.nextLine();

		// int count = rWrite.size() + 1;
		Calendar cal = Calendar.getInstance();
		
		r = new Write(title, content, cal, MainMenu.ID);
		rWrite.add(r);
		System.out.println("리뷰 작성이 완료되었습니다.");
	}

	public void ReviewSearch() {

		System.out.println("[ 등록된 리뷰 목록 ]");
		ReviewList();

		while (true) {
			System.out.println("===리뷰 게시글 검색===");
			System.out.println("검색할 제목 입력 : ");
			String head = sc.nextLine();

			System.out.println("===검색 결과==="); // contain 써보기 (썻더니 -1나옴)
			
			
			
			/*for (int i = 0; i < rWrite.size(); i++) {
				if (rWrite.get(i).getContent().equals(head)) {
					System.out.println(rWrite.get(i).toString());
			
				} else if (!rWrite.get(i).getContent().equals(head)) { // 물어보기
					System.out.println("검색어와 일치한 게시글이 없습니다. 다시 검색해주세요.");
					}
			}*/
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
				break;
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

			} else if (menu == 2) {

			} else if (menu == 3) {

			} else if (menu == 4) {

			} else if (menu == 9) {
				return;
			} else {
				System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
				break;
			}
		}

	}

	public void MyReview() {
		System.out.println("===내 글 조회===");
		ReviewList();

		/*for (int i = 0; i < rWrite.size(); i++) {
			System.out.println(i + "번 " + rWrite.get(i));
		}*/

		System.out.println("1. 내 글 수정");
		System.out.println("2. 내 글 삭제");

		System.out.println("메뉴 선택 : ");
		int menu = sc.nextInt();
		sc.nextLine();

		switch (menu) {
		case 1:
			ReviewEdit();
			break;
		case 2:
			ReviewDetele();
		}
	}

	public void ReviewEdit() {
		System.out.println("===내 글 수정===");
		ReviewList();

		System.out.println("수정할 게시글의 번호를 입력해주세요 : ");
		int num = sc.nextInt();
		sc.nextLine();

		System.out.println("제목 : ");
		String title = sc.nextLine();

		System.out.println("내용 : ");
		String content = sc.nextLine();

		rw = new ReviewWrite(title, content, ReviewDate(), MainMenu.ID);

		rWrite.set(num, rw);

		System.out.println("수정이 완료되었습니다.");
	}

	public void ReviewDetele() {
		System.out.println("===내 글 삭제===");
		System.out.println("삭제할 게시글의 번호를 입력해주세요 : ");
		int num = sc.nextInt();
		sc.nextLine();

		while (true) {
			System.out.println("정말 삭제하시겠습니까? (y/n)");
			String check = sc.nextLine();

			if (check.equalsIgnoreCase("Y")) {
				rWrite.remove(num);
				System.out.println("삭제가 완료되었습니다.");
				break;
			} else {
				System.out.println("삭제를 취소되었습니다.");
				return;
			}
		}
	}
}
