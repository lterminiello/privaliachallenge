package com.lterminiello.privaliachallenge.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Lists {


	public static <E> ArrayList<E> newArrayList() {
		return new ArrayList<>();
	}

	@SafeVarargs
	public static <E> ArrayList<E> newArrayList(E... elements) {
		ArrayList<E> list = new ArrayList<>();
		Collections.addAll(list, elements);
		return list;
	}

	public static <E> ArrayList<E> newArrayList(Iterable<? extends E> elements) {
		return (elements instanceof Collection) ? new ArrayList<>((Collection<? extends E>)elements)
				: newArrayList(elements.iterator());
	}
	

	public static <E> ArrayList<E> newArrayList(Iterator<? extends E> elements) {
		ArrayList<E> list = newArrayList();
		while (elements.hasNext()) {
			list.add(elements.next());
		}
		return list;
	}
	

	
	public static Boolean isNullOrEmpty(List<?> list) {
		return (list == null) || list.isEmpty();
	}

	
}