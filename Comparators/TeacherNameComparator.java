package Comparators;

import java.util.Comparator;

import Users.Teacher;

public class TeacherNameComparator implements Comparator<Teacher> {
    public int compare(Teacher t1, Teacher t2) {
        return t1.getName().compareToIgnoreCase(t2.getName());
    }
}
