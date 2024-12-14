package SystemParts;

import java.util.Map;

public class Report {
    private int totalStudents;
    private int totalTeachers;
    private int totalCourses;
    private Map<String, Integer> studentsPerCourse; 
    private Map<String, Double> averageGradesPerCourse;

    public Report() {}

   
    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public void setTotalTeachers(int totalTeachers) {
        this.totalTeachers = totalTeachers;
    }

    public void setTotalCourses(int totalCourses) {
        this.totalCourses = totalCourses;
    }

    public void setStudentsPerCourse(Map<String, Integer> studentsPerCourse) {
        this.studentsPerCourse = studentsPerCourse;
    }

    public void setAverageGradesPerCourse(Map<String, Double> averageGradesPerCourse) {
        this.averageGradesPerCourse = averageGradesPerCourse;
    }

    @Override
    public String toString() {
        return "Report{" +
               "Total Students=" + totalStudents +
               ", Total Teachers=" + totalTeachers +
               ", Total Courses=" + totalCourses +
               ", Students Per Course=" + studentsPerCourse +
               ", Average Grades Per Course=" + averageGradesPerCourse +
               '}';
    }
}
