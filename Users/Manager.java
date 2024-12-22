package Users;

import java.util.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import Comparators.TeacherSalaryComparator;
import Comparators.TeacherNameComparator;
import Comparators.DegreeLevelComparator;


public class Manager extends Employee {
	private Map<String, Student> studentsByName; 
	private ManagerType managerType;
    private List<Researcher> researchers;
    private List<News> allNews;
    private List<Student> students;
    private List<Teacher> teachers;
    private SortingContext<Student> studentSortingContext;
    private SortingContext<Teacher> teacherSortingContext;
    private List<Request> requests;
    
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

    public Manager(String login, String password, String name, String surname, String id, Languages language, String phoneNumber, boolean isResearcher, double salary, ManagerType managerType) {
        super(login, password, name, surname, id, language, phoneNumber, isResearcher, salary);
        this.managerType = managerType;
		this.availableCourses = new ArrayList<>();
		this.studentRegistrations = new HashMap<>();
    }

    public ManagerType getManagerType() {
        return managerType;
    }

    public void setManagerType(ManagerType managerType) {
        this.managerType = managerType;
    }
    
    private final List<Course> availableCourses;
    private final Map<Student, List<Course>> studentRegistrations;

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
        if (!student.getEnrolledCourses().contains(course)) {
            student.getEnrolledCourses().add(course);  // Добавляем курс в список
            System.out.println(student.getName() + " has been registered for course: " + course.getName());
        }
    }

//    public void registerStudentForCourse(Student student, Course course) {
//        if (!availableCourses.contains(course)) {
//            System.out.println("Course not found: " + course.getName());
//            return;
//        }
//
//        if (student.getCourseMarks().containsKey(course)) {
//            System.out.println("Student already registered for course: " + course.getName());
//            return;
//        }
//
//        student.registerForCourse(course);
//        studentRegistrations.computeIfAbsent(student, k -> new ArrayList<>()).add(course);
//        System.out.println("Student " + student.getName() + " registered for course: " + course.getName());
//    }

    public void approveRegistration(Student student, Course course) {
        List<Course> registeredCourses = studentRegistrations.get(student);
        if (registeredCourses == null || !registeredCourses.contains(course)) {
            System.out.println("Student has not registered for course: " + course.getName());
            return;
        }

        System.out.println("Registration approved for student " + student.getName() +
                           " in course: " + course.getName());
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
                        totalGrades += mark.getGradePoint();
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
        return null;  // Return null if the course is not found
    }
    
//    public void addStudent(Student student) {
//        studentsByName.put(student.getName(), student);
//    }
//
//    // Method to find a student by name
//    public Student findStudentByName(String studentName) {
//        return studentsByName.get(studentName);  // Retrieves the student directly from the map
//    }


    public void registerResearcher(Researcher researcher) {
        researchers.add(researcher);
    }

   
    public void publishResearchPaper(Researcher researcher, ResearchPaper paper) {
        researcher.publishPaper(paper);
        News.createAnnouncementForResearcherPaper(researcher.getName(), paper.getTitle());
        System.out.println("Research paper announcement created for: " + researcher.getName());
    }

    public void generateAnnouncementForTopCitedResearcher() {
        News.createAnnouncementForTopCitedResearcher(researchers);
    }

    public void createPinnedResearchNews(String description) {
        News researchNews = new News("Research", description);
        researchNews.pinTopic(); 
        allNews.add(researchNews);
        System.out.println("Pinned Research news created: " + description);
    }

    public void displayAllNews() {
        for (News news : allNews) {
            System.out.println(news.getTopic() + ": " + news.getDescription());
        }
        
        public void addStudent(Student student) {
            students.add(student);
        }

        public void addTeacher(Teacher teacher) {
            teachers.add(teacher);
        }
        public void sortStudentsByGPA() {
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

        public void sortTeachersBySalary() {
            teacherSortingContext.sort(teachers, new TeacherSalaryComparator());
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
}
