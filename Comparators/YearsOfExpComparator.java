package Comparators;

import java.util.Comparator;

import Users.Teacher;

public class YearsOfExpComparator implements Comparator<Teacher> {

	@Override
	public int compare(Teacher t1, Teacher t2) {
		return Double.compare(t1.getYearsOfExperience(), t2.getYearsOfExperience());
	}

}
