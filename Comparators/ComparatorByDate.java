package Comparators;

public class ComparatorByDate implements Comparable<ResearchPaper> {

	@Override
	public int compareTo(ResearchPaper r1, ResearchPaper r2) {
		// TODO Auto-generated method stub
		return r1.getPublishedDate().compareTo(r2.getPublishedDate());
	}

}
