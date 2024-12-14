package Comparators;

import java.util.Comparator;

import Users.Teacher;

public class TeacherSalaryComparator implements Comparator<Teacher> {

	@Override
	public int compare(Teacher t1, Teacher t2) {
		return Double.compare(t1.getSalary(), t2.getSalary());
	}

}
