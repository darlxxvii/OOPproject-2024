package SystemParts;

import java.time.LocalDateTime;

import Users.Teacher;

public class Lesson {
    private String topic;
    private Course course;
    private LocalDateTime date;
    private Teacher teacher;
    private String room;

    // Конструктор
    public Lesson(String topic, Course course, LocalDateTime date, Teacher teacher, String room) {
        this.topic = topic;
        this.course = course;
        this.date = date;
        this.teacher = teacher;
        this.room = room;
    }

    // Методы
    public void reschedule(LocalDateTime newDate) {
        this.date = newDate;
    }

    public void changeRoom(String newRoom) {
        this.room = newRoom;
    }
}