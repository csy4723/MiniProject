package com.uni.miniProject.model.comparator;

import java.util.Comparator;

import com.uni.miniProject.model.vo.Write;

public class DescReviewID implements Comparator<Write>{

	@Override
	public int compare(Write o1, Write o2) {
		return o2.getUserId().compareTo(o1.getUserId());
	}

}
