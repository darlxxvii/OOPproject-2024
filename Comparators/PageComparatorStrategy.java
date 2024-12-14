package Comparators;

import Research.ResearchPaper;

public class PageComparatorStrategy implements ResearchPaperComparatorStrategy {
    @Override
    public int compare(ResearchPaper paper1, ResearchPaper paper2) {
        return Integer.compare(paper1.getPages(), paper2.getPages());
    }
}
