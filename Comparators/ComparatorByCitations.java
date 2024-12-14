package Comparators;

import java.util.Comparator;

import Research.ResearchPaper;

public class ComparatorByCitations implements Comparator<ResearchPaper>{

	public int compare(ResearchPaper r1, ResearchPaper r2) {
		// TODO Auto-generated method stub
		return Integer.compare(r2.getCitations(), r1.getCitations());
	}

}
