package com.uni.miniProject.model.comparator;

import java.util.Comparator;

import com.uni.miniProject.model.vo.Write;

public class AscReviewID implements Comparator<Write>{

	@Override
	public int compare(Write o1, Write o2) {
		return o1.getUserId().compareTo(o2.getUserId());
	}

}
