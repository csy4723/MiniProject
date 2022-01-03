package com.uni.miniProject.run;


import com.uni.miniProject.controller.CampingController;
import com.uni.miniProject.view.MainMenu;

public class Run { 

	public static void main(String[] args) {
		MainMenu mm= new MainMenu();
		CampingController cc = new CampingController();
		
	
		 
		
//		cc.campRead();
//		cc.campList();
		mm.mainMenu();

	}

}
