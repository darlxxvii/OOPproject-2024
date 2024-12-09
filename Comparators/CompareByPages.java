package Comparators;

import Research.ResearchPaper;

public class CompareByPages implements Comparable<ResearchPaper> {
	@Override
	public int compareTo(ResearchPaper r1, ResearchPaper r2) {
		return Integer.compare(r2.getPublishedPages(), r1.getPublishedPages());;
	} 
}
