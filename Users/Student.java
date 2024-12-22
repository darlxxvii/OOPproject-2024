package Users;

import Enums.CompletionStatus;
import Enums.DegreeLevel;
import Enums.EducationalProgram;
import Enums.Organizations;
import Enums.Schools;
import Enums.UrgencyLevel;
import Research.ResearchPaper;
import Research.Researcher;
import SystemParts.Course;
import SystemParts.DiplomaProject;
import SystemParts.Mark;
import SystemParts.Observer;
import SystemParts.Request;
import SystemParts.Review;
import SystemParts.School;
import SystemParts.Transcript;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Student extends User implements Researcher, Observer {
	private static int counter = 1;
    private final String studentID;
    private final int enrollmentYear;
    private final DegreeLevel degreeLevel;
    private EducationalProgram educationalProgram;
    private Schools school;
    private int credits;
    private double GPA;
    private final Map<Course, Mark> courseMarks;
    protected Map<String, Integer> completedCourses;
    private Transcript transcript;
    private boolean isResearcher;
    private int yearOfStudy;  
    private final List<String> activities;
    private final Map<String, LocalDate> organizations;
    private DiplomaProject diplomaProject;
    private Map<Organizations, LocalDate> membershipStartDates;
    private Organizations headOrganization;
    private List<ResearchPaper> researchPapers;
	private CompletionStatus completionStatus;
    
    public Student(String name, String surname, String email, int enrollmentYear, DegreeLevel degreeLevel, Schools school, EducationalProgram educationalProgram) {
    	super(name, surname, email);
        this.enrollmentYear = enrollmentYear;
        this.setYearOfStudy(1);
        this.GPA = 0.0;
        this.credits = 0;
        this.degreeLevel = degreeLevel;
        this.setEducationalProgram(educationalProgram);
        this.setSchool(school);
        this.studentID = generateUniqueID();
        this.courseMarks = new HashMap<Course, Mark>();
        this.completedCourses = new HashMap<>();
        this.activities = new ArrayList<>();
        this.organizations = new HashMap<>();
        this.setMembershipStartDates(new HashMap<>());
        this.setHeadOrganization(null);
        this.researchPapers = new ArrayList<>();
        this.isResearcher = degreeLevel == DegreeLevel.MASTER || degreeLevel == DegreeLevel.PHD;
    }

    @Override
	protected String generateUniqueID() {
        String yearPrefix = String.valueOf(enrollmentYear).substring(2);
        String degreeCode = switch (degreeLevel) {
            case BACHELOR -> "BD";
            case MASTER -> "MD";
            case PHD -> "PD";
        };
        String uniquePart = String.format("%06d", counter++);
        return yearPrefix + degreeCode + uniquePart;
    }

    public void earnCredits(int credits) {
        this.credits += credits;
        System.out.println(getName() + " earned " + credits + " credits. Total: " + this.credits);
    }
    
    public void completeCourse(String courseName, int creditValue) {
        if (!completedCourses.containsKey(courseName)) {
            completedCourses.put(courseName, creditValue);
            earnCredits(creditValue);
        } else {
            System.out.println(getName() + " has already completed " + courseName);
        }
    }
    
    public double calculateGPA() {
        double totalPoints = 0;
        int totalCourses = 0;
        for (Mark mark : courseMarks.values()) {
            totalPoints += mark.getGradePoint();
            totalCourses++;
        }
        this.GPA = totalCourses > 0 ? totalPoints / totalCourses : 0.0;
        return GPA;
    }

    public void registerForCourse(Course course) {
        if (courseMarks.containsKey(course)) {
            System.out.println("Already registered for the course: " + course.getName());
            return;
        }
        courseMarks.put(course, new Mark(this, course, 0.0, new Date()));
        System.out.println("Registered for course: " + course.getName());
    }

    
    public void dropCourse(Course course) {
        courseMarks.remove(course);
        System.out.println("Dropped course: " + course.getName());
    }
    
    public void viewInfoAboutTeacher(Course course) {
        course.viewInstructorsInfo();  
    }

    public void viewMarks(Course course) {
        Mark mark = courseMarks.get(course);
        if (mark != null) {
            System.out.println("Mark for " + course.getName() + ": " + mark.getGrade());
        }
    }

    public void rateTeacher(Teacher teacher, String comment, int rating) {
        if (rating < 1 || rating > 5) {
            System.out.println("Rating must be between 1 and 5.");
            return;
        }
        Review review = new Review(comment, rating, true); 
        teacher.addReview(review);
        System.out.println("Your anonymous review has been added for " + teacher.getName());
    }
    
    public boolean isEligibleForGraduation() {
        return credits >= degreeLevel.getRequiredCredits() && checkAdditionalRequirements();
    }
    
    protected boolean checkAdditionalRequirements() {
        return true;
    }
    
    public void sendRequest(String name, String surname, String email, Request request) {
        Dean.getInstance(name, surname, email).receiveRequest(getName(), request);
    }
    public void addActivity(String activity) {
        activities.add(activity);
        System.out.println("Added activity: " + activity);
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
    
    public void update(String message) {
        System.out.println(getName() + " received notification: " + message);
    }
    
    public void applyForInternship(String companyName) {
        System.out.println("Applied for an internship at " + companyName);
    }
    
    public void assignDiplomaProject(DiplomaProject diplomaProject) {
        this.diplomaProject = diplomaProject;
        System.out.println("Assigned diploma project: " + diplomaProject.getProjectTitle());
    }
    
    public void changeEducationalProgram(EducationalProgram newProgram) {
        if (!school.isProgramAvailable(newProgram)) {
            throw new IllegalArgumentException("Program not available at the specified school.");
        }
        this.educationalProgram = newProgram;
    }
    //methods for Diploma project
    public void startDiplomaProject() {
        if (degreeLevel == DegreeLevel.BACHELOR && getYearOfStudy() == 4) {
            this.diplomaProject = new DiplomaProject("Bachelor's Thesis: " + getName());
            this.setCompletionStatus(CompletionStatus.IN_PROGRESS);
            System.out.println("Started Bachelor's diploma project: " + diplomaProject.getProjectTitle());
        } else if (degreeLevel == DegreeLevel.MASTER || degreeLevel == DegreeLevel.PHD) {
            this.diplomaProject = new DiplomaProject("Graduate Thesis: " + getName());
            this.setCompletionStatus(CompletionStatus.IN_PROGRESS);
            System.out.println("Started Graduate diploma project: " + diplomaProject.getProjectTitle());
        } else {
            System.out.println("Diploma project is not applicable for this degree level yet.");
        }
    }
    
    public void submitForReview() {
        if (diplomaProject != null && diplomaProject.getCompletionStatus() == CompletionStatus.IN_PROGRESS) {
            diplomaProject.setCompletionStatus(CompletionStatus.REVIEW_PENDING);
            System.out.println(getName() + " has submitted their project for review: " + diplomaProject.getProjectTitle());
        } else {
            System.out.println("Project must be in progress before submitting for review.");
        }
    }

    public void defendProject(boolean success) {
        if (diplomaProject != null && diplomaProject.getCompletionStatus() == CompletionStatus.REVIEW_PENDING) {
            diplomaProject.setCompletionStatus(success ? CompletionStatus.DEFENDED : CompletionStatus.FAILED);
            System.out.println(getName() + " has " + (success ? "successfully defended" : "failed to defend") + " their project: " + diplomaProject.getProjectTitle());
        } else {
            System.out.println("Project must be under review before it can be defended.");
        }
    }
    //Researcher Methods
    @Override
    public void conductResearch(String topic) {
        if (!isResearcher) {
            System.out.println(getName() + " is not a researcher and cannot conduct research.");
            return;
        }
        System.out.println(getName() + " is conducting research on the topic: " + topic);
        addActivity("Research on: " + topic);
    }

    @Override
    public void publishPaper(ResearchPaper paper) {
        if (!isResearcher) {
            System.out.println(getName() + " is not a researcher and cannot publish papers.");
            return;
        }
        if (researchPapers == null) {
            researchPapers = new ArrayList<>();
        }
        researchPapers.add(paper);
        System.out.println(getName() + " has published a research paper titled: " + paper.getTitle());
    }

    @Override
    public List<ResearchPaper> getPublishedPapers() {
        if (!isResearcher) {
            System.out.println(getName() + " is not a researcher and has no published papers.");
            return new ArrayList<>();
        }
        return researchPapers;
    }

    @Override
    public int calculateHIndex() {
        if (!isResearcher) {
            System.out.println(getName() + " is not a researcher and has no H-Index.");
            return 0;
        }
        Map<String, Integer> citationCounts = new HashMap<>();
        for (ResearchPaper paper : researchPapers) {
            citationCounts.put(paper.getTitle(), paper.getCitations());
        }
        List<Integer> sortedCitations = new ArrayList<>(citationCounts.values());
        sortedCitations.sort((a, b) -> b - a);
        int hIndex = 0;
        for (int i = 0; i < sortedCitations.size(); i++) {
            if (sortedCitations.get(i) >= i + 1) {
                hIndex = i + 1;
            } else {
                break;
            }
        }
        return hIndex;
    }

    @Override
    public int getTotalCitations() {
        if (!isResearcher) {
            System.out.println(getName() + " is not a researcher and has no citations.");
            return 0;
        }
        int totalCitations = 0;
        for (ResearchPaper paper : researchPapers) {
            totalCitations += paper.getCitations();
        }
        return totalCitations;
    }

    // Additional method so student to become a researcher
    public void becomeResearcher() {
        if (isResearcher) {
            System.out.println(getName() + " is already a researcher.");
            return;
        }
        isResearcher = true;
        System.out.println(getName() + " has chosen to become a researcher.");
    }
    
    public void stopBeingResearcher() {
        if (!isResearcher) {
            System.out.println(getName() + " is not a researcher.");
            return;
        }
        isResearcher = false;
        System.out.println(getName() + " has chosen to stop being a researcher.");
    }
    //getters and setters
    public String getStudentID() {
        return studentID;
    }

    public double getGPA() {
        return GPA;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public DegreeLevel getDegreeLevel() {
        return degreeLevel;
    }

	public int getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	public boolean isResearcher() {
        return isResearcher;
    }

    public void setResearcher(boolean isResearcher) {
        this.isResearcher = isResearcher;
    }

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	} 
	
	public Map<Course, Mark> getCourseMarks() {
        return courseMarks;
    }
	public Map<String, Integer> getCompletedCourses() {
	    return completedCourses;
	}

	public void setCompletedCourses(Map<String, Integer> completedCourses) {
	    this.completedCourses = completedCourses;
	}

	public Transcript getTranscript() {
	    return transcript;
	}

	public void setTranscript(Transcript transcript) {
	    this.transcript = transcript;
	}

	public EducationalProgram getEducationalProgram() {
		return educationalProgram;
	}

	public void setEducationalProgram(EducationalProgram educationalProgram) {
		this.educationalProgram = educationalProgram;
	}

	public Schools getSchool() {
		return school;
	}

	public void setSchool(Schools school) {
		this.school = school;
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Student.counter = counter;
	}

	public DiplomaProject getDiplomaProject() {
		return diplomaProject;
	}

	public void setDiplomaProject(DiplomaProject diplomaProject) {
		this.diplomaProject = diplomaProject;
	}

	public Map<String, LocalDate> getOrganizations() {
		return organizations;
	}

	public List<String> getActivities() {
		return activities;
	}

	public Organizations getHeadOrganization() {
		return headOrganization;
	}

	public void setHeadOrganization(Organizations headOrganization) {
		this.headOrganization = headOrganization;
	}

	public Map<Organizations, LocalDate> getMembershipStartDates() {
		return membershipStartDates;
	}

	public void setMembershipStartDates(Map<Organizations, LocalDate> membershipStartDates) {
		this.membershipStartDates = membershipStartDates;
	}
	public CompletionStatus getCompletionStatus() {
		return completionStatus;
	}


	public void setCompletionStatus(CompletionStatus completionStatus) {
		this.completionStatus = completionStatus;
	} 
}
/*this.enrolledCourses = new ArrayList<Course>();
public void viewCourses() {
for (Course course : enrolledCourses) {
    System.out.println(course.getName());
}

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
    
    protected String generateUniqueID() {
        return "S" + UUID.randomUUID().toString().substring(0, 8);
    }
}*/
