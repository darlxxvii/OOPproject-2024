package Users;

import java.util.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Enums.CourseType;
import Enums.EducationalProgram;
import Enums.Languages;
import Enums.ManagerType;
import Enums.Schools;
import Enums.Status;
import Enums.UrgencyLevel;
import Pattern.SortingContext;
import Research.ResearchPaper;
import Research.Researcher;
import SystemParts.Comment;
import SystemParts.Course;
import SystemParts.Mark;
import SystemParts.Message;
import SystemParts.News;
import SystemParts.Report;
import SystemParts.Request;
import Comparators.GPAComparator;
import Comparators.StudentNameComparator;
import Comparators.YearsOfExpComparator;
import Comparators.TeacherNameComparator;
import Comparators.DegreeLevelComparator;


public class Manager extends Employee {
	private Map<String, Student> studentsByName; 
	private ManagerType managerType;
    private List<Researcher> researchers;
    private List<News> allNews;
    private List<Student> students = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();
    private SortingContext<Student> studentSortingContext;
    private SortingContext<Teacher> teacherSortingContext;
    private List<Request> requests;
    private List<Message> messages = new ArrayList<>();
    private List<ResearchPaper> publishedPapers = new ArrayList<>();
    
    
    public Manager(String name, String surname, String email, ManagerType managerType) {
        super(name, surname, email);
        this.managerType = managerType;
		this.availableCourses = new ArrayList<>();
		this.studentRegistrations = new HashMap<>();
        this.researchers = new ArrayList<>();
        this.allNews = new ArrayList<>();
        this.studentSortingContext = new SortingContext<>();
        this.teacherSortingContext = new SortingContext<>();
        this.requests = new ArrayList<>();
    }

    public ManagerType getManagerType() {
        return managerType;
    }

    public void setManagerType(ManagerType managerType) {
        this.managerType = managerType;
    }
    
    private final List<Course> availableCourses;
    private Map<Student, List<Course>> studentRegistrations = new HashMap<>();

    public Manager() {
        this.availableCourses = new ArrayList<>();
        this.studentRegistrations = new HashMap<>();
    }

    public void addCourse(Course course, EducationalProgram program, int yearOfStudy) {
        availableCourses.add(course);
        System.out.println("Course added: " + course.getName() +
                           " | Program: " + program.getFullName() +
                           " | Year: " + yearOfStudy);
    }
    

    public void registerStudentForCourse(Student student, Course course) {
        if (student != null && course != null) {
            student.addCourse(course);  
            course.addStudent(student); 
        }
    }

    public void displayAvailableCourses() {
        System.out.println("Available Courses:");
        for (Course course : availableCourses) {
            System.out.println("- " + course.getName() + " (" + course.getCredits() + " credits)");
        }
    }

    public void displayStudentCourses(Student student) {
        List<Course> courses = studentRegistrations.get(student);
        if (courses == null || courses.isEmpty()) {
            System.out.println("Student is not registered for any courses.");
            return;
        }

        System.out.println("Courses for " + student.getName() + ":");
        for (Course course : courses) {
            System.out.println("- " + course.getName());
        }
    }
    
    public void assignCourseToTeacher(Teacher teacher, Course course) {
        if (teacher.getCourses().contains(course)) {
            System.out.println(teacher.getName() + " is already assigned to the course: " + course.getName());
            return;
        }

        teacher.assignCourse(course);
        System.out.println("Course " + course.getName() + " has been assigned to " + teacher.getName());
    }
    
    public Report generateAcademicReport(List<Student> students, List<Teacher> teachers, List<Course> courses) {
        Report report = new Report();
        
        report.setTotalStudents(students.size());
        
        report.setTotalTeachers(teachers.size());
        
        report.setTotalCourses(courses.size());
        
        Map<String, Integer> studentsPerCourse = new HashMap<>();
        for (Course course : courses) {
            int studentCount = (int) students.stream()
                .filter(student -> student.getEnrolledCourses().contains(course))
                .count();
            studentsPerCourse.put(course.getName(), studentCount);
        }
        report.setStudentsPerCourse(studentsPerCourse);
        
        Map<String, Double> averageGradesPerCourse = new HashMap<>();
        for (Course course : courses) {
            double totalGrades = 0;
            int gradeCount = 0;
            
            for (Student student : students) {
                if (student.getEnrolledCourses().contains(course)) {
                    Mark mark = student.getCourseMarks().get(course);
                    if (mark != null) {
                        totalGrades += mark.calculateTotal();
                        gradeCount++;
                    }
                }
            }
            
            double averageGrade = (gradeCount > 0) ? totalGrades / gradeCount : 0;
            averageGradesPerCourse.put(course.getName(), averageGrade);
        }
        report.setAverageGradesPerCourse(averageGradesPerCourse);
        
        return report;
    }
    
    public Course findCourseByName(String courseName) {
        for (Course course : availableCourses) {
            if (course.getName().equalsIgnoreCase(courseName)) {
                return course;
            }
        }
        return null;  
    }

    public void publishPaper(ResearchPaper paper) {
        ((List<ResearchPaper>) this.publishedPapers).add(paper);
    }

    public void registerResearcher(Researcher researcher) {
        this.researchers.add(researcher); // Ensure `researchers` is initialized as a List
    }

    public void generateAnnouncementForTopCitedResearcher() {
        Researcher topCitedResearcher = null;
        int maxCitations = 0;

        for (Researcher researcher : researchers) {
            int totalCitations = researcher.getTotalCitations();
            if (totalCitations > maxCitations) {
                maxCitations = totalCitations;
                topCitedResearcher = researcher;
            }
        }

        if (topCitedResearcher != null) {
            System.out.println("Announcement created for top cited researcher: " + topCitedResearcher.getName());
        }
    }
    private List<String> news = new ArrayList<>();

    public void createPinnedResearchNews(String researchNews) {
        String pinnedNews = "Pinned Research news created: " + researchNews;
        news.add(pinnedNews);
    }
//
//    public void displayAllNews() {
//        for (String newsItem : news) {
//            System.out.println(newsItem);
//        }
//    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void sortStudentsByGPA() {
        for (Student student : students) {
            student.updateGPA(); 
        }
        studentSortingContext.sort(students, new GPAComparator());
    }

        public void sortStudentsByName() {
            studentSortingContext.sort(students, new StudentNameComparator());
        }

        public void sortStudentsByDegreeLevel() {
            studentSortingContext.sort(students, new DegreeLevelComparator());
        }

        public void sortTeachersByName() {
            teacherSortingContext.sort(teachers, new TeacherNameComparator());
        }

        public void sortTeachersByYears() {
            teacherSortingContext.sort(teachers, new YearsOfExpComparator());
        }

        public void displayStudents() {
            for (Student student : students) {
                System.out.println(student);
            }
        }

        public void displayTeachers() {
            for (Teacher teacher : teachers) {
                System.out.println(teacher);
            }
}
        public void addRequest(Request request) {
            this.requests.add(request);
            System.out.println("Request added: " + request.getContent());
        }

        public void viewRequests() {
            if (requests.isEmpty()) {
                System.out.println("No requests to display.");
            } else {
                for (Request request : requests) {
                    System.out.println("Request from: " + request.getSender().getName());
                    System.out.println("Content: " + request.getContent());
                    System.out.println("Signed: " + (request.isSigned() ? "Yes, by " + request.getSignedBy() : "No"));
                    System.out.println("-----------------------");
                }
            }
        }
        public void signRequest(Request request, Dean dean) {
            request.signRequest(dean);
        }
    

    @Override
    public String toString() {
        return "Manager {" +
                "ID=" + getId() +
                ", Name=" + getName() +
                ", Surname=" + getSurname() +
                ", Email=" + getEmail() +
                ", Phone=" + getPhoneNumber() +
                ", Manager Type=" + managerType +
                '}';
    }
	
	public void sendMessage(User sender, User recipient, String content, UrgencyLevel priority) {
		Message newMessage = new Message(sender, recipient, content, priority);
		messages.add(newMessage);
		System.out.println("Message sent from " + sender.getName() + " to " + recipient.getName());
	}

	public List<Message> viewMessagesByStatus(Status status) {
		List<Message> filteredMessages = new ArrayList<>();
		for (Message message : messages) {
			if (message.getStatus() == status) {
				filteredMessages.add(message);
			}
		}
		return filteredMessages;
	}

	public List<Message> filterMessagesByPriority(UrgencyLevel priority) {
		List<Message> filteredMessages = new ArrayList<>();
		for (Message message : messages) {
			if (message.filterByPriority(priority)) {
				filteredMessages.add(message);
			}
		}
		return filteredMessages;
	}

	public List<Message> viewAllMessages() {
		return new ArrayList<>(messages);
	}

	public void markAllMessagesAsRead() {
		for (Message message : messages) {
			message.markAsRead();
		}
		System.out.println("All messages have been marked as read.");
	}

	public void markMessageAsRead(Message message) {
		message.markAsRead();
		System.out.println("Message from " + message.getSender().getName() + " to " + message.getRecipient().getName()
				+ " has been marked as read.");
	}

	public void deleteMessage(Message message) {
		messages.remove(message);
		System.out.println("Message from " + message.getSender().getName() + " to " + message.getRecipient().getName()
				+ " has been deleted.");
	}

	public List<Message> viewMessagesByRecipient(User recipient) {
		List<Message> filteredMessages = new ArrayList<>();
		for (Message message : messages) {
			if (message.getRecipient().equals(recipient)) {
				filteredMessages.add(message);
			}
		}
		return filteredMessages;
	}

	public List<Message> viewMessagesBySender(User sender) {
		List<Message> filteredMessages = new ArrayList<>();
		for (Message message : messages) {
			if (message.getSender().equals(sender)) {
				filteredMessages.add(message);
			}
		}
		return filteredMessages;
	}

	public List<Message> viewMessages(Status status, UrgencyLevel priority) {
		List<Message> filteredMessages = new ArrayList<>();
		for (Message message : messages) {
			if (message.getStatus() == status && message.getPriority() == priority) {
				filteredMessages.add(message);
			}
		}
		return filteredMessages;
	}
	
	public void addCommentToNews(News newsItem, Comment comment) {
        newsItem.addComment(comment);
        System.out.println("Comment added to news: " + newsItem.getTopic());
    }

    public void removeCommentFromNews(News newsItem, Comment comment) {
        newsItem.removeComment(comment);
        System.out.println("Comment removed from news: " + newsItem.getTopic());
    }

    public void displayAllNews() {
    	for (News news : news) {
            System.out.println("Topic: " + news.getTopic());
            System.out.println("Description: " + news.getDescription());
            System.out.println("Comments:");
            for (Comment comment : news.getComments()) {
                System.out.println("- " + comment.getAuthor().getName() + ": " + comment.getContent());
            }
            System.out.println("-----");
        }
    }
    public void logCourseDetails(Course course, EducationalProgram program, int yearOfStudy) {
        System.out.println(getName() + " added course: " + course.getName() + " | Program: " + program.getFullName() + " | Year: " + yearOfStudy);
    }

    public void logStudentRegistration(Student student, Course course) {
        System.out.println(getName() + " registered student " + student.getName() + " for course: " + course.getName());
    }

    public void logCourseAssignment(Teacher teacher, Course course) {
        System.out.println(getName() + " assigned course " + course.getName() + " to teacher " + teacher.getName());
    }

    public void logReportGeneration(List<Student> students, List<Teacher> teachers, List<Course> courses) {
        System.out.println(getName() + " generated academic report with " + students.size() + " students, " + teachers.size() + " teachers, and " + courses.size() + " courses.");
    }

    public void logCourseFound(Course course) {
        System.out.println(getName() + " found course: " + course.getName());
    }

    public void logPaperPublished(ResearchPaper paper) {
        System.out.println(getName() + " published paper: " + paper.getTitle());
    }

    public void logResearcherRegistered(Researcher researcher) {
        System.out.println(getName() + " registered researcher: " + researcher.getName());
    }

    public void logAnnouncementForTopCitedResearcher(Researcher topCitedResearcher) {
        System.out.println(getName() + " created announcement for top cited researcher: " + topCitedResearcher.getName());
    }

    public void logPinnedResearchNews(String researchNews) {
        System.out.println(getName() + " created pinned research news: " + researchNews);
    }

    public void logStudentAdded(Student student) {
        System.out.println(getName() + " added student: " + student.getName());
    }

    public void logTeacherAdded(Teacher teacher) {
        System.out.println(getName() + " added teacher: " + teacher.getName());
    }

    public void logSortStudentsByGPA() {
        System.out.println(getName() + " sorted students by GPA.");
    }

    public void logSortStudentsByName() {
        System.out.println(getName() + " sorted students by name.");
    }

    public void logSortStudentsByDegreeLevel() {
        System.out.println(getName() + " sorted students by degree level.");
    }

    public void logSortTeachersByName() {
        System.out.println(getName() + " sorted teachers by name.");
    }

    public void logSortTeachersByYears() {
        System.out.println(getName() + " sorted teachers by years of experience.");
    }

    public void logDisplayAllStudents() {
        System.out.println(getName() + " displayed all students.");
    }

    public void logDisplayAllTeachers() {
        System.out.println(getName() + " displayed all teachers.");
    }

    public void logRequestAdded(Request request) {
        System.out.println(getName() + " added request: " + request.getContent());
    }

    public void logRequestView(Request request) {
        System.out.println(getName() + " viewed request from: " + request.getSender().getName());
    }

    public void logRequestSigned(Request request) {
        System.out.println(getName() + " signed request: " + request.getContent());
    }

    public void logMessageSent(User sender, User recipient, UrgencyLevel priority) {
        System.out.println(getName() + " sent message from " + sender.getName() + " to " + recipient.getName() + " with priority: " + priority);
    }

    public void logAllMessagesMarkedAsRead() {
        System.out.println(getName() + " marked all messages as read.");
    }

    public void logMessageMarkedAsRead(Message message) {
        System.out.println(getName() + " marked message from " + message.getSender().getName() + " to " + message.getRecipient().getName() + " as read.");
    }

    public void logMessageDeleted(Message message) {
        System.out.println(getName() + " deleted message from " + message.getSender().getName() + " to " + message.getRecipient().getName());
    }

    public void logCommentAddedToNews(News newsItem) {
        System.out.println(getName() + " added comment to news: " + newsItem.getTopic());
    }

    public void logCommentRemovedFromNews(News newsItem) {
        System.out.println(getName() + " removed comment from news: " + newsItem.getTopic());
    }

   
}

}
