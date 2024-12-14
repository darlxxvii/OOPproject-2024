package SystemParts;
import SystemParts.Mark;
import Users.Student;
import SystemParts.Course;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Transcript {
    private final Student student;

    public Transcript(Student student) {
        this.student = student;
    }

    public String generateTranscriptText() {
        StringBuilder transcript = new StringBuilder();
        transcript.append("Transcript for ").append(student.getName()).append("\n");
        transcript.append("Student ID: ").append(student.getStudentID()).append("\n");
        transcript.append("Degree Level: ").append(student.getDegreeLevel()).append("\n");
        transcript.append("GPA: ").append(student.getGPA()).append("\n\n");
        transcript.append("Courses:\n");

        for (Course course : student.getEnrolledCourses()) {
            Mark mark = student.getCourseMarks().get(course); 
            if (mark != null) {
                transcript.append(course.getName())
                          .append(" - Grade: ")
                          .append(mark.getGrade())
                          .append("\n");
            }
        }

        return transcript.toString();
    }

    public void saveTranscriptToFile(String filePath) {
        String transcriptText = generateTranscriptText();
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(transcriptText);
            System.out.println("Transcript saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving transcript: " + e.getMessage());
        }
    }
}
