package SystemParts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Users.Teacher;

public class Lesson {
    private String id;
    private String topic;
    private Course course;
    private LocalDateTime date;
    private Teacher teacher;
    private String room;

    public Lesson(String id, String topic, Course course, LocalDateTime date, Teacher teacher, String room) {
        this.id = id;
        this.topic = topic;
        this.course = course;
        this.date = date;
        this.teacher = teacher;
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic){
        this.topic = topic;
    }

    public Course getCourse(){
        return course;
    }

    public void setCourse(Course course){
        this.course = course;
    }

    public LocalDateTime getDate(){
        return date;
    }

    public void setDate(LocalDateTime date){
        this.date = date;
    }

    public Teacher getTeacher(){
        return teacher;
    }

    public void setTeacher(Teacher teacher){
        this.teacher = teacher;
    }

    public String getRoom(){
        return room;
    }

    public void setRoom(String room){
        this.room = room;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return  "Lesson ID: " + id + "\n" +
                "Topic: " + topic + "\n" +
                "Date: " + date.format(formatter) + "\n" +
                "Room: " + room + "\n";
    }
}
