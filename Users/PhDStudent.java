package Users;

import java.util.List;

import Enums.DegreeLevel;
import Enums.EducationalProgram;
import Enums.Schools;
import Research.ResearchPaper;
import Research.Researcher;
import Research.ResearcherHelper;
import SystemParts.School;

public class PhDStudent extends Student implements Researcher{

    private int credits;
    private int yearOfStudy;
    private ResearcherHelper researcherHelper = new ResearcherHelper();
    public PhDStudent(String name, String surname, String email, int enrollmentYear,Schools school, EducationalProgram educationalProgram) {
        super(name, surname, email, enrollmentYear, DegreeLevel.PHD, school, educationalProgram);  
    }

    public void earnCredits(int earnedCredits) {
        credits += earnedCredits;
        System.out.println("Total earned: " + credits);
        if (credits == 19) {
            System.out.println("You have completed your credits for the degree.");
        }
    }

    public boolean isEligibleForGraduation() {
        return credits >= 19 && yearOfStudy == 3;
    }

    @Override
    public void conductResearch(String topic) {
        researcherHelper.conductResearch(topic);
    }

    @Override
    public void publishPaper(ResearchPaper paper) {
        researcherHelper.publishPaper(paper);
    }

    @Override
    public List<ResearchPaper> getPublishedPapers() {
        return researcherHelper.getPublishedPapers();
    }

    @Override
    public int calculateHIndex() {
        return researcherHelper.calculateHIndex();
    }

    @Override
    public int getTotalCitations() {
        return researcherHelper.getTotalCitations();
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
