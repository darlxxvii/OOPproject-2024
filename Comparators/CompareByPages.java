package Comparators;

import java.util.Comparator;

import Research.ResearchPaper;

public class CompareByPages implements Comparator<ResearchPaper> {
	public int compare(ResearchPaper r1, ResearchPaper r2) {
		return Integer.compare(r2.getPages(), r1.getPages());
	} 
}
