package Comparators;

import java.util.Comparator;

import Research.ResearchPaper;

public class ComparatorByDate implements Comparator<ResearchPaper> {

	@Override
	public int compare(ResearchPaper r1, ResearchPaper r2) {
		// TODO Auto-generated method stub
		return r1.getPublishedDate().compareTo(r2.getPublishedDate());
	}
}
