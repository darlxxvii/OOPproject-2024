package Users;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import Enums.DegreeLevel;
import Enums.EducationalProgram;
import Enums.Organizations;
import SystemParts.Course;
import SystemParts.Mark;
import SystemParts.School;

public class BachelorStudent extends Student {
    private School school;
    private EducationalProgram educationalProgram;
    private Map<Organizations, LocalDate> membershipStartDates;
    private Organizations headOrganization;
    private Map<Course, Mark> completedCourses;
    public BachelorStudent(String name, String surname, String email, int enrollmentYear) {
        super(name, surname, email, enrollmentYear, DegreeLevel.BACHELOR);  
    }
    
    public BachelorStudent(String studentID, String name, String surname, String email, School school, EducationalProgram educationalProgram) {
        super(studentID, name, surname, email);
        this.school = school;
        this.educationalProgram = educationalProgram;
        this.membershipStartDates = new HashMap<>();
        this.setHeadOrganization(null);
    }

   
    public void changeEducationalProgram(EducationalProgram newProgram) {
        if (!school.isProgramAvailable(newProgram)) {
            throw new IllegalArgumentException("Program not available at the specified school.");
        }
        this.educationalProgram = newProgram;
    }

    public void joinOrganization(Organizations organization) {
        if (membershipStartDates.containsKey(organization)) {
            System.out.println("Already a member of " + organization);
        } else {
            membershipStartDates.put(organization, LocalDate.now());
            System.out.println("Joined organization: " + organization);
        }
    }
    
    public void becomeHead(Organizations organization) {
        if (!membershipStartDates.containsKey(organization)) {
            System.out.println("You need to join the organization first.");
        } else if (LocalDate.now().minusYears(1).isBefore(membershipStartDates.get(organization))) {
            System.out.println("You must be a member for at least a year to become head.");
        } else {
            setHeadOrganization(organization);
            System.out.println("You are now the head of " + organization);
        }
    }
    public void completeCourse(Course course, Mark mark) {
        completedCourses.put(course, mark);
    }
    public void earnCredits(int earnedCredits) {
        credits += earnedCredits;
        System.out.println("Total earned: " + credits);
        if (this.credits == 21) {
            System.out.println("You have completed your credits for the degree.");
        }
    }
    public void applyForInternship(String companyName) {
        System.out.println("Applied for an internship at " + companyName);
    }
    public boolean isEligibleForGraduation() {
        return this.getCredits() >= 21 && this.getYearOfStudy() == 4;
    }

	public Organizations getHeadOrganization() {
		return headOrganization;
	}

	public void setHeadOrganization(Organizations headOrganization) {
		this.headOrganization = headOrganization;
	}
	public School getSchool() {
        return school;
    }

	public EducationalProgram getEducationalProgram() {
        return educationalProgram;
    }

}
