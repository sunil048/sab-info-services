package com.sabtok.persistance.entity;

import java.util.Comparator;

public class PageComparator implements Comparator<Page>{

	public int compare(Page o1, Page o2) {
		return o1.getTitle().compareTo(o2.getTitle());
	}

}
