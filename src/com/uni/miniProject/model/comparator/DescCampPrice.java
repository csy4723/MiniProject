package com.uni.miniProject.model.comparator;

import java.util.Comparator;

import com.uni.miniProject.model.vo.CampInfo;

public class DescCampPrice implements Comparator<CampInfo>{

	@Override
	public int compare(CampInfo o1, CampInfo o2) {
		return -(o1.getCampPrice() - o2.getCampPrice());
	}

}
 