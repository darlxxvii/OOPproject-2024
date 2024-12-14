package Comparators;

import Research.ResearchPaper;

public class DateComparatorStrategy implements ResearchPaperComparatorStrategy {
    @Override
    public int compare(ResearchPaper paper1, ResearchPaper paper2) {
        return paper1.getDate().compareTo(paper2.getDate());
    }
}
