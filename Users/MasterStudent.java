package Users;

import java.util.List;

import Enums.DegreeLevel;
import Research.ResearchPaper;

public class MasterStudent extends GraduateStudent {

    private int credits;
    private int yearOfStudy;

    public MasterStudent(String name, String surname, String email, int enrollmentYear) {
        super(name, surname, email, enrollmentYear, DegreeLevel.MASTER);  
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

    @Override
    public void conductResearch() {
        System.out.println(getName() + " is conducting research as part of the master's program.");
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
