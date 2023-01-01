package com.sabtok.entity;

import java.util.Comparator;

import lombok.Getter;

@Getter
public class PageComparator implements Comparator<Page>{

	public int compare(Page o1, Page o2) {
		return o1.getTitle().compareTo(o2.getTitle());
	}

}
