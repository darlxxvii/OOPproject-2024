package Users;

import java.util.List;

import Enums.DegreeLevel;
import Enums.EducationalProgram;
import Research.ResearchPaper;
import Research.Researcher;
import SystemParts.School;

public class PhDStudent extends Student implements Researcher{

    private int credits;
    private int yearOfStudy;

    public PhDStudent(String name, String surname, String email, int enrollmentYear,School school, EducationalProgram educationalProgram) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void publishPaper(ResearchPaper paper) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ResearchPaper> getPublishedPapers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calculateHIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalCitations() {
		// TODO Auto-generated method stub
		return 0;
	}
}
