package SystemParts;

import Users.Student;
import Users.Teacher;
import java.util.*;

public class Course {

    public enum CourseType {
        MAJOR,
        MINOR,
        ELECTIVE
    }

    private String id;
    private String name;
    private Integer credits;
    private String school;
    private CourseType type;
    private List<Teacher> instructors;
    private List<Lesson> lessons;
    private Map<Student, Mark> studentMarks;

    public Course(String id, String name, Integer credits, String school, CourseType type) {
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
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

    public Mark getStudentMark(Student student) {
        return studentMarks.get(student);
    }
}
