package Users;

import Enums.CompletionStatus;
import Enums.DegreeLevel;
import Research.ResearchPaper;
import Research.Researcher;
import SystemParts.DiplomaProject;

import java.util.ArrayList;
import java.util.List;

abstract class GraduateStudent extends Student implements Researcher {
    protected String projectTitle;
    protected CompletionStatus projectStatus;
    protected List<ResearchPaper> researchPapers;
    protected DiplomaProject diplomaProject;
	private CompletionStatus completionStatus;
    public GraduateStudent(String name, String surname, String email, int enrollmentYear, DegreeLevel degreeLevel) {
    	super(name, surname, email, enrollmentYear, degreeLevel); 
        this.projectStatus = CompletionStatus.PLANNED;
        this.researchPapers = new ArrayList<>();
    }

    public void startProject() {
        this.setCompletionStatus(CompletionStatus.IN_PROGRESS);
        System.out.println("The project '" + projectTitle + "' has started and is now in progress.");
    }
    public void submitForReview() {
        if (projectStatus == CompletionStatus.IN_PROGRESS) {
            this.projectStatus = CompletionStatus.REVIEW_PENDING;
            System.out.println(getName() + " has submitted their project for review: " + projectTitle);
        } else {
            System.out.println("Project must be in progress before submitting for review.");
        }
    }

    public void defendProject(boolean success) {
        if (projectStatus == CompletionStatus.REVIEW_PENDING) {
            this.projectStatus = success ? CompletionStatus.DEFENDED : CompletionStatus.FAILED;
            System.out.println(getName() + " has " + (success ? "successfully defended" : "failed to defend") + " their project: " + projectTitle);
        } else {
            System.out.println("Project must be under review before it can be defended.");
        }
    }

    public void publishResearchPaper(ResearchPaper paper) {
        researchPapers.add(paper);
        System.out.println(getName() + " has published a research paper: " + paper.getTitle());
    }

    public List<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    public abstract void conductResearch();


	public CompletionStatus getCompletionStatus() {
		return completionStatus;
	}


	public void setCompletionStatus(CompletionStatus completionStatus) {
		this.completionStatus = completionStatus;
	} 
}
