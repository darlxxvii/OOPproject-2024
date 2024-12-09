package SystemParts;

import Users.Student;
import Users.Teacher;

import java.util.*;

public class Course {
    private String id;
    private String name;
    private Integer credits;
    private String major;
    private List<Teacher> instructors;
    private List<Student> students;
    private List<Lesson> lessons;

    // Конструктор
    public Course(String id, String name, Integer credits, String major) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.major = major;
        this.teachers = new ArrayList<>();
        this.students = new ArrayList<>();
        this.lessons = new ArrayList<>();
    }

    // Геттеры
    public String getName() {
        return name;
    }

    // Методы
    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void assignTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }
}
