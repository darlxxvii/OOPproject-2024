package SystemParts;

import java.util.ArrayList;
import java.util.List;

import Enums.CompletionStatus;
import Research.ResearchPaper;
import Research.Researcher;

public class DiplomaProject {
    private String projectTitle;
    private List<ResearchPaper> publishedPapers;
    private Researcher projectSupervisor;
    private CompletionStatus completionStatus;

    public DiplomaProject(String projectTitle, Researcher projectSupervisor) {
        this.projectTitle = projectTitle;
        this.projectSupervisor = projectSupervisor;
        this.publishedPapers = new ArrayList<>();
        this.completionStatus = CompletionStatus.PLANNED;
    }

    public void addResearchPaper(ResearchPaper paper) {
        publishedPapers.add(paper);
    }

    public List<ResearchPaper> getResearchPaper() {
        return publishedPapers;
    }

    public void startProject(Researcher projectSupervisor, String projectTitle) {
        this.projectSupervisor = projectSupervisor;
        this.projectTitle = projectTitle;
        this.completionStatus = CompletionStatus.IN_PROGRESS;
    }
}