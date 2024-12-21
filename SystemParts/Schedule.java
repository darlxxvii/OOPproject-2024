package SystemParts;

import Users.Teacher;
import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
    private String id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
    private String type;
    private Course course;

    public Schedule(String id, LocalDate date, LocalTime startTime, LocalTime endTime, String location, String type) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public boolean isToday() {
        return this.date.equals(LocalDate.now());
    }

    @Override
    public String toString() {
        return "Schedule: " + type + " on " + date + " from " + startTime + " to " + endTime +
                " at " + location;
    }
}