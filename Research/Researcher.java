package Research;

import java.util.List;

public interface Researcher {
    void conductResearch(String topic);
    void publishPaper(ResearchPaper paper);
    List<ResearchPaper> getPublishedPapers();
    int calculateHIndex();
    int getTotalCitations();
	String getName();
}
