package Users;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;

import Enums.DegreeLevel;
import Enums.EducationalProgram;
import Enums.Schools;
import Research.ResearchPaper;
import Research.Researcher;
import Research.ResearcherHelper;
import SystemParts.School;

public class MasterStudent extends Student implements Researcher {
    private int credits;
    private int yearOfStudy;
    private List<ResearchPaper> publishedPapers;
    private ResearcherHelper researcherHelper = new ResearcherHelper();

    public MasterStudent(String name, String surname, String email, int enrollmentYear, DegreeLevel degreeLevel, Schools school, EducationalProgram educationalProgram) {
        super(name, surname, email, enrollmentYear, DegreeLevel.MASTER, school, educationalProgram);
        this.publishedPapers = new ArrayList<>();
    }

    public void earnCredits(int earnedCredits) {
        credits += earnedCredits;
        System.out.println("Total earned: " + credits);
        if (credits == 18) {
            System.out.println("You have completed your credits for the degree.");
        }
    }

    public boolean isEligibleForGraduation() {
        return credits >= 18 && yearOfStudy == 2;
    }

    // Researcher methods
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


}
