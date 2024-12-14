package Users;

import Enums.Positions;
import Enums.UrgencyLevel;
import Research.Researcher;
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

    public Teacher(String id, String name, String surname, Positions position, int yearsOfExperience) {
        super(id, name, surname);
        this.position = position;
        this.yearsOfExperience = yearsOfExperience;

        if (position == Positions.PROFESSOR) {
            this.isResearcher = true;
        } else {
            this.isResearcher = false;
        }
    }

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

    public void assignCourse(Course course) {
        courses.add(course);
        System.out.println("Course assigned to " + getName() + ": " + course.getName());
    }

    public List<Review> getReviews() {
        return reviews;
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

        if (!student.getEnrolledCourses().contains(course)) {
            System.out.println("Student is not enrolled in the course: " + course.getName());
            return;
        }

        student.getCourseMarks().put(course, mark);
        System.out.println("Mark " + mark.getGrade() + " has been assigned to " + student.getName() + " for course " + course.getName());
        
    }
    // Методы Researcher
    @Override
    public void conductResearch(String topic) {
        if (!isResearcher) {
            throw new IllegalStateException("This teacher is not a researcher.");
        }
        System.out.println(getName() + " is conducting research on: " + topic);
    }

    @Override
    public void publishPaper(ResearchPaper paper) {
        if (!isResearcher) {
            throw new IllegalStateException("This teacher is not a researcher.");
        }
        publishedPapers.add(paper);
        System.out.println(getName() + " published a research paper: " + paper.getTitle());
    }

    @Override
    public List<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }

    @Override
    public int calculateHIndex() {
        int hIndex = 0;
        for (ResearchPaper paper : publishedPapers) {
            if (paper.getCitations() >= hIndex + 1) {
                hIndex++;
            }
        }
        return hIndex;
    }

    public void sendComplaint(String complaintText, UrgencyLevel urgencyLevel) {
        Dean.getInstance().receiveComplaint(getName(), complaintText, urgencyLevel);
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

}
