package Comparators;
import java.util.List;

import Research.ResearchPaper;

public class ComparatorContext {
    private ResearchPaperComparatorStrategy strategy;

    public void setStrategy(ResearchPaperComparatorStrategy strategy) {
        this.strategy = strategy;
    }

    public void sortPapers(List<ResearchPaper> papers) {
        if (strategy == null) {
            throw new IllegalStateException("Strategy is not set!");
        }
        papers.sort(strategy::compare);
    }
}
