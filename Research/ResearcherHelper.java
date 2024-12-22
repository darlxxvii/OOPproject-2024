package Research;

import java.util.ArrayList;
import java.util.List;

public class ResearcherHelper {
    private List<ResearchPaper> publishedPapers = new ArrayList<>();

    public void conductResearch(String topic) {
        System.out.println("Conducting research on: " + topic);
    }

    public void publishPaper(ResearchPaper paper) {
        publishedPapers.add(paper);
        System.out.println("Published paper: " + paper.getTitle());
    }

    public List<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }

    public int calculateHIndex() {
        publishedPapers.sort((p1, p2) -> Integer.compare(p2.getCitations(), p1.getCitations()));
        int hIndex = 0;
        for (int i = 0; i < publishedPapers.size(); i++) {
            if (publishedPapers.get(i).getCitations() >= (i + 1)) {
                hIndex = i + 1;
            } else {
                break;
            }
        }
        return hIndex;
    }

    public int getTotalCitations() {
        return publishedPapers.stream().mapToInt(ResearchPaper::getCitations).sum();
    }
}
