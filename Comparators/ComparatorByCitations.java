package Comparators;

import Research.ResearchPaper;

public class ComparatorByCitations implements Comparable<ResearchPaper>{

	public int compareTo(ResearchPaper r1, ResearchPaper r2) {
		// TODO Auto-generated method stub
		return Integer.compare(r2.getCitations(), r1.getCitations());
	}

}
