package com.uni.miniProject.model.comparator;

import java.util.Comparator;

import com.uni.miniProject.model.vo.Write;

public class DescFreeTitle implements Comparator<Write> {
	@Override
	public int compare(Write o1, Write o2) {
		return o2.getTitle().compareTo(o1.getTitle());
	}


}
