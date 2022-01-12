package com.uni.miniProject.model.comparator;

import java.util.Comparator;

import com.uni.miniProject.model.vo.CampInfo;

public class DescCampArea implements Comparator<CampInfo>{
	@Override
	public int compare(CampInfo o1, CampInfo o2) {
		return o2.getCampArea().compareTo(o1.getCampArea());
	}
}
 