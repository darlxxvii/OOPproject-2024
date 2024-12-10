package Systemparts;

import Users.Student;

import java.util.*;

public class Course {
    private String id;
    private String name;
    private Integer credits;
    private String major;
    private List<Teacher> instructors;
    private List<Student> students;
    private List<Lesson> lessons;
    private Map<Student, Mark> studentMarks;

    public Course(String id, String name, Integer credits, String major) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.major = major;
        this.instructors = new ArrayList<>();
        this.students = new ArrayList<>();
        this.lessons = new ArrayList<>();
        this.studentMarks = new HashMap<>();
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void addStudent(Student student) {
        students.add(student);
        studentMarks.put(student, new Mark(student, this));
    }

    public void removeStudent(Student student) {
        students.remove(student);
        studentMarks.remove(student);
    }

    public void assignInstructor(Teacher teacher) {
        instructors.add(teacher);
    }

    public void removeTeacher(Teacher teacher) {
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

    public void putAttestationMarks(Student student, double firstAttestation, double secondAttestation) {
        Mark mark = studentMarks.get(student);
        if (mark != null) {
            mark.setAttestationMarks(firstAttestation, secondAttestation);
            //System.out.println("Attestation marks set for " + student.getName() + " in " + name);
        } else {
            System.out.println("Student not enrolled in this course.");
        }
    }

    public void putFinalExamMark(Student student, double finalExam) {
        Mark mark = studentMarks.get(student);
        if (mark != null) {
            mark.setFinalExamMark(finalExam);
            //System.out.println("Final exam mark set for " + student.getName() + " in " + name);
        } else {
            System.out.println("Student not enrolled in this course.");
        }
    }

    public Mark getStudentMark(Student student) {
        return studentMarks.get(student);
    }
}