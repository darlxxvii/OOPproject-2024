package Comparators;

import java.util.Comparator;

import Users.Student;

public class GPAComparator implements Comparator<Student>{
	public int compareTo(Student s1, Student s2) {
	return Double.compare(s2.getGpa(), s1.getGpa());
}
}
