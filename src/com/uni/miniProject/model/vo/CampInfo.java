package com.uni.miniProject.model.vo;

public class CampInfo {
	
	private String campName;
	private String campArea;
	private int campPrice;
	private int campDay;
	
	public CampInfo() {
		// TODO Auto-generated constructor stub
	}

	public CampInfo(String campName, String campArea, int campPrice, int campDay) {
		super();
		this.campName = campName;
		this.campArea = campArea;
		this.campPrice = campPrice;
		this.campDay = campDay;
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

	public int getCampDay() {
		return campDay;
	}

	public void setCampDay(int campDay) {
		this.campDay = campDay;
	}


	public String information() {
		return "CampInfo [campName=" + campName + ", campArea=" + campArea + ", campPrice=" + campPrice + ", campDay="
				+ campDay + "]";
	}
	
	

}
