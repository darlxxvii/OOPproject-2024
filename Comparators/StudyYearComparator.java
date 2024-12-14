package Comparators;

import java.util.Comparator;
import Users.Student;

public class StudyYearComparator implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.getYearOfStudy(), s2.getYearOfStudy());
    }
}
