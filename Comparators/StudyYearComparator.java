package Comparators;

import java.util.Comparator;
import Users.Student;

public class StudyYearComparator implements Comparator<Student> {
    @Override
    public int compareTo(Student s1, Student s2) {
        return Integer.compare(s1.getStudyYear(), s2.getStudyYear());
    }
}
