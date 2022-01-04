package com.uni.miniProject.model.comparator;

import java.util.Comparator;

import com.uni.miniProject.model.vo.CampInfo;

public class AscCampArea implements Comparator<CampInfo>{

	@Override
	public int compare(CampInfo o1, CampInfo o2) {
		return o1.getCampArea().compareTo(o2.getCampArea());
	}
 
}
