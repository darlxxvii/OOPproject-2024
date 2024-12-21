package Users;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Vector;

public class Database {
    private Vector<Course> courses = new Vector<>();
    private Vector<User> users = new Vector<>();
    private Vector<Student> students = new Vector<>();
    private Vector<Teacher> teachers = new Vector<>();
    private Vector<Manager> managers = new Vector<>();
    private Vector<Admin> admins = new Vector<>();
    private Vector<Organization> organizations = new Vector<>();
    private Vector<News> news = new Vector<>();
    private Vector<SMS> sms = new Vector<>();
    private Vector<LogFile> logfiles = new Vector<>();
    private Vector<Login> logins = new Vector<>();

    private static final String LOG_FILE = "logs.txt";

    public Database() {
      
    }

    //log actions into the TXT file
    public synchronized void logAction(String action) {
        String timestampedAction = LocalDateTime.now() + " - " + action;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(timestampedAction);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    //example methods for interacting
    public void addUser(User user) {
        users.add(user);
        logAction("User added: " + user.getName());
    }

    public void removeUser(User user) {
        if (users.remove(user)) {
            logAction("User removed: " + user.getName());
        } else {
            logAction("Attempt to remove non-existent user: " + user.getName());
        }
    }

    public Vector<User> getUsers() {
        logAction("All users retrieved");
        return users;
    }

    public void addCourse(Course course) {
        courses.add(course);
        logAction("Course added: " + course.getName());
    }

    public void removeCourse(Course course) {
        if (courses.remove(course)) {
            logAction("Course removed: " + course.getName());
        } else {
            logAction("Attempt to remove non-existent course: " + course.getName());
        }
    }

    public Vector<Course> getCourses() {
        logAction("All courses retrieved");
        return courses;
    }


}
