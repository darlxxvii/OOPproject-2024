package SystemParts;

import Enums.CompletionStatus;
import Research.ResearchPaper;
import Research.Researcher;

import java.util.ArrayList;
import java.util.List;

public class DiplomaProject {
    private String projectTitle;
    private List<ResearchPaper> publishedPapers;
    private Researcher Supervisor;
    private CompletionStatus completionStatus;

    public DiplomaProject(String projectTitle, Researcher Supervisor) {
    	if (Supervisor.calculateHIndex() <= 3) {
            throw new IllegalArgumentException("The supervisor's H-index must be greater than 3 to supervise the project.");
        }
        this.projectTitle = projectTitle;
        this.Supervisor = Supervisor;
        this.publishedPapers = new ArrayList<>();
        this.completionStatus = CompletionStatus.PLANNED;
    }

	public void addResearchPaper(ResearchPaper paper) {
        publishedPapers.add(paper);
    }

    public List<ResearchPaper> getResearchPapers() {
        return publishedPapers;
    }

    public void startProject() {
        this.completionStatus = CompletionStatus.IN_PROGRESS;
        System.out.println("The project '" + projectTitle + "' has started and is now in progress.");
    }
    public CompletionStatus getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(CompletionStatus status) {
        this.completionStatus = status;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

	public Researcher getSupervisor() {
		return Supervisor;
	}
}
