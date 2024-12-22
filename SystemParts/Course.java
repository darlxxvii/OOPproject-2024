package SystemParts;

import Users.Student;
import Users.Teacher;
import Enums.CourseType;
import Enums.Schools;
import java.util.*;

public class Course {

    private String id;
    private String name;
    private Integer credits;
    private CourseType type;
    private Schools school;
    private List<Teacher> instructors;
    private List<Lesson> lessons;
    private Map<Student, Mark> studentMarks;

    public Course(String id, String name, Integer credits, Schools school, CourseType type) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.school = school;
        this.type = type;
        this.instructors = new ArrayList<>();
        this.lessons = new ArrayList<>();
        this.studentMarks = new HashMap<>();
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Schools getSchool() {
        return school;
    }

    public void setSchool(Schools school) {
        this.school = school;
    }

    public void assignInstructor(Teacher teacher) {
        instructors.add(teacher);
    }

    public void removeInstructor(Teacher teacher) {
        instructors.remove(teacher);
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
    }

    public Set<Teacher> getInstructors() {
        return new HashSet<>(instructors);
    }

    public Set<Lesson> getLessons() {
        return new HashSet<>(lessons);
    }

    public void addStudent(Student student) {
        studentMarks.put(student, new Mark(student, this));
    }

    public void removeStudent(Student student) {
        studentMarks.remove(student);
    }

    public void viewInstructorsInfo() {
        for (Teacher teacher : instructors) {
            teacher.viewInfoAboutTeacher();
            System.out.println();
        }
    }

    public void putAttestationMarks(Student student, double firstAttestation, double secondAttestation) {
        Mark mark = studentMarks.get(student);
        if (mark != null) {
            mark.setAttestationMarks(firstAttestation, secondAttestation);
        } else {
            System.out.println("Student is not registered for the course");
        }
    }

    public void putFinalExamMark(Student student, double finalExam) {
        Mark mark = studentMarks.get(student);
        if (mark != null) {
            mark.setFinalExamMark(finalExam);
        } else {
            System.out.println("Student is not registered for the course");
        }
    }

    public void recordRetake(Student student) {
        Mark mark = studentMarks.get(student);
        if (mark != null) {
            mark.addRetake();
            if (mark.getRetakeCount() >= 3) {
                System.out.println("Student has been banned");
                removeStudent(student);
            } else {
                System.out.println("Student has retake count: " + mark.getRetakeCount());
            }
        } else {
            System.out.println("Student is not registered for this course.");
        }
    }


    public Mark getStudentMark(Student student) {
        return studentMarks.get(student);
    }
}
