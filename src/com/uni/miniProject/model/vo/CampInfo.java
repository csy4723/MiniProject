package com.uni.miniProject.model.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.uni.miniProject.view.MainMenu; 

public class CampInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2216673415598447086L;
	private String campName;
	private String campArea;
	private int campPrice;
	private Calendar campDay;
	private String reservId = " ";
	
	public CampInfo() {
		// TODO Auto-generated constructor stub
	} 

	public CampInfo(String campName, String campArea, int campPrice, Calendar campDay) {
		super();
		this.campName = campName;
		this.campArea = campArea;
		this.campPrice = campPrice;
		this.campDay = campDay;
	}
	
	public CampInfo(String campName, String campArea, int campPrice, Calendar campDay, String reservId) {// 실행 read용
		super();
		this.campName = campName;
		this.campArea = campArea;
		this.campPrice = campPrice;
		this.campDay = campDay;
		this.reservId = reservId;
	}


	public String getCampName() {
		return campName;
	}
 
	public void setCampName(String campName) {
		this.campName = campName;
	}

	public String getCampArea() {
		return campArea;
	}

	public void setCampArea(String campArea) {
		this.campArea = campArea;
	}

	public int getCampPrice() {
		return campPrice;
	}

	public void setCampPrice(int campPrice) {
		this.campPrice = campPrice;
	}

	public Calendar getCampDay() {
		return campDay;
	}

	public void setCampDay(Calendar campDay) {
		this.campDay = campDay;
	}


	public String getReservId() {
		return reservId;
	}

	public void setReservId(String reservId) {
		this.reservId = reservId;
	}

	public String information() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일");
		String date = sf.format(campDay.getTime());
		
		return campName +" 캠핑장의 위치는 "+ campArea + "이고 가격은 " + campPrice + "원이다. (날짜 : "
				+ date + ")";
	}

	@Override
	public String toString() { // 파일로 입출력할 때 사용
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sf.format(campDay.getTime());
		return campName + "," + campArea + "," + campPrice + ","
				+ date +"," +reservId;
	}
	
	

}
