package Users;

import Enums.DegreeLevel;
import Enums.UrgencyLevel;
import SystemParts.Course;
import SystemParts.Mark;
import SystemParts.Review;
import SystemParts.Transcript;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Student extends User{
	private static int counter = 1; 
    private String studentID;
    private HashMap<Course, Mark> courseMarks = new HashMap<>();
    private List<Course> enrolledCourses = new ArrayList<>();
    private int enrollmentYear;
    private Transcript transcript;
    private boolean isResearcher;
    private double GPA;
    private int yearOfStudy;  
    protected int credits;
    private DegreeLevel degreeLevel;

    public Student(String name, String surname, String email, int enrollmentYear, DegreeLevel degreeLevel) {
    	super(name, surname, email);
        this.enrollmentYear = enrollmentYear;
        this.setYearOfStudy(1); 
        this.isResearcher = false; 
        this.GPA = 0.0;
        this.credits = 0;
        this.degreeLevel = degreeLevel;

        this.studentID = generateStudentId(enrollmentYear, degreeLevel);
    }

    public Student(String studentID, String name, String surname, String email) {
    	super(name,surname,email);
    	this.studentID=studentID;
	}

	private String generateStudentId(int enrollmentYear, DegreeLevel degreeLevel) {
        String yearPrefix = String.valueOf(enrollmentYear).substring(2);
        String degreeCode;

        switch (degreeLevel) {
            case BACHELOR:
                degreeCode = "BD";
                break;
            case MASTER:
                degreeCode = "MD";
                break;
            case PHD:
                degreeCode = "PD";
                break;
            default:
                throw new IllegalArgumentException("Invalid degree level");
        }

        String uniquePart = String.format("%06d", counter++);
        return yearPrefix + degreeCode + uniquePart;
    }

    public void calculateGPA() {
        double totalPoints = 0;
        int totalCourses = 0;
        for (Mark mark : courseMarks.values()) {
            totalPoints += mark.getGradePoint();
            totalCourses++;
        }
        this.GPA = totalCourses > 0 ? totalPoints / totalCourses : 0.0;
    }

    public void viewCourses() {
        for (Course course : enrolledCourses) {
            System.out.println(course.getName());
        }
    }

    public void registerForCourse(Course course) {
        enrolledCourses.add(course);
        System.out.println("Registered for course: " + course.getName());
        courseMarks.put(course, new Mark(this, course, 0.0, new Date())); 
    }
    public void dropCourse(Course course) {
        enrolledCourses.remove(course);
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
    public abstract void earnCredits(int credits);
    public abstract boolean isEligibleForGraduation();
    public void sendRequest(String request) {
        Dean.getInstance().receiveRequest(getName(), request);
    }
    
    public String getStudentID() {
        return studentID;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
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

	public String getName() {
		return getName();
	}
	public Map<Course, Mark> getCourseMarks() {
        return courseMarks;
    }
}
