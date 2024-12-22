package Users;

import Enums.DegreeLevel;
import Enums.Positions;
import Enums.UrgencyLevel;
import Research.Researcher;
import Research.ResearcherHelper;
import SystemParts.Course;
import SystemParts.Mark;
import SystemParts.Review;
import Research.ResearchPaper;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Employee implements Researcher {
    private Positions position;
    private int yearsOfExperience;
    private List<Course> courses = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();
    private List<ResearchPaper> publishedPapers = new ArrayList<>();
    private boolean isResearcher;
	private ResearcherHelper researcherHelper; 
	private DegreeLevel graduatedDegreeLevel;
	
    
    public Teacher(String name, String surname, String email,  Positions position, int yearsOfExperience,DegreeLevel graduatedDegreeLevel) {
        super(name, surname, email);
        this.position = position;
        this.yearsOfExperience = yearsOfExperience;
        this.id = generateUniqueID();
        this.graduatedDegreeLevel = graduatedDegreeLevel;
        if (position == Positions.PROFESSOR) {
            this.isResearcher = true;
        } else {
            this.isResearcher = false;
        }
        if (isResearcher) {
            this.researcherHelper = new ResearcherHelper(); 
        }
    }
    public void sendComplaint(String complaintText, UrgencyLevel urgencyLevel) {
        Dean.getInstance(getName(), getSurname(), getEmail()).receiveComplaint(getName(), complaintText, urgencyLevel);
    }
	public void viewInfoAboutTeacher() {
		System.out.println("Teacher Name: " + getName() + " " + getSurname());
        System.out.println("Position: " + position);
        System.out.println("Years of Experience: " + yearsOfExperience);
        System.out.println("Courses Taught:");
        for (Course course : courses) {
            System.out.println("- " + course.getName());
        }
        System.out.println("Student Reviews:");
        for (Review review : reviews) {
            System.out.println("- " + review.getText());
        }
	}
	public void assignCourse(Course course) {
        courses.add(course);
        System.out.println("Course assigned to " + getName() + ": " + course.getName());
    }
	
    public void addReview(Review review) {
        reviews.add(review);
        System.out.println("Review added for " + getName() + ": " + review.getText());
    }
    public void putMark(Student student, Course course, Mark mark) {
        if (!courses.contains(course)) {
            System.out.println("This teacher does not teach the course: " + course.getName());
            return;
        }

        if (!student.getCourseMarks().containsKey(course)) {
            System.out.println("Student is not enrolled in the course: " + course.getName());
            return;
        }

        student.getCourseMarks().put(course, mark);
        System.out.println("Mark " + mark.getGrade() + " has been assigned to " + student.getName() + " for course " + course.getName());
        
    }
    //Researcher methods
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
    //getters and setters

    public Positions getPosition() {
        return position;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Review> getReviews() {
        return reviews;
    }
    public DegreeLevel getGraduatedDegreeLevel() {
        return graduatedDegreeLevel;
    }
}
