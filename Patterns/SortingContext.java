package Patterns;

import java.util.Comparator;
import java.util.List;

public class SortingContext<T> implements SortingStrategy<T>{

	@Override
	public List<T> sort(List<T> items, Comparator<T> comparator) {
		items.sort(comparator);
		return items;
	}

}
