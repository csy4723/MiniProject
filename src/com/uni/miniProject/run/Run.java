package com.uni.miniProject.run;


import com.uni.miniProject.controller.CampingController;
import com.uni.miniProject.controller.UserController;
import com.uni.miniProject.model.vo.Member;
import com.uni.miniProject.view.MainMenu;

public class Run { 

	public static void main(String[] args) {
		MainMenu mm= new MainMenu();
		CampingController cc = new CampingController();
		UserController uc = new UserController();
		 
	
		 
		
//		cc.campRead();
//		cc.campList();
		mm.mainMenu();
//		uc.SignUp();
//		uc.login();
		

		
		

	}

}
