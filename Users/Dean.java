package Users;

import java.util.ArrayList;
import java.util.List;

import Enums.UrgencyLevel;
import SystemParts.Complaint;

public class Dean {
    private static Dean instance;
    private List<String> facultyMembers;
    private List<String> studentRequests;
    private List<Complaint> complaints;

    private Dean() {
        facultyMembers = new ArrayList<>();
        studentRequests = new ArrayList<>();
        complaints = new ArrayList<>();
    }

    public static Dean getInstance() {
        if (instance == null) {
            instance = new Dean();
        }
        return instance;
    }

    public void addFacultyMember(String name) {
        facultyMembers.add(name);
        System.out.println("Added faculty member: " + name);
    }

    public void viewRequests() {
        System.out.println("Current student requests:");
        for (String request : studentRequests) {
            System.out.println("- " + request);
        }
    }

    public void approveStudentRequest(String request) {
        studentRequests.remove(request);
        System.out.println("Request approved: " + request);
    }

    public void receiveRequest(String studentName, String request) {
        studentRequests.add(request);
        System.out.println("Request added to the queue for processing: " + request);
    }
    public void handleRequest(String request) {
        if (studentRequests.remove(request)) {
            System.out.println("Request handled and removed from the queue: " + request);
        } else {
            System.out.println("Request not found in the queue: " + request);
        }
    }

    public void receiveComplaint(String teacherName, String complaintText, UrgencyLevel urgencyLevel) {
        complaints.add(new Complaint(teacherName, complaintText, urgencyLevel));
        System.out.println("Complaint received from " + teacherName + " with urgency: " + urgencyLevel);
    }

    public void viewComplaints() {
        System.out.println("List of complaints:");
        for (Complaint complaint : complaints) {
            System.out.println("- From " + complaint.getTeacherName() + ": " + complaint.getComplaintText() + " (Urgency: " + complaint.getUrgencyLevel() + ")");
        }
    }
    public void handleComplaint(String teacherName) {
        Complaint complaintToHandle = null;
        for (Complaint complaint : complaints) {
            if (complaint.getTeacherName().equals(teacherName)) {
                complaintToHandle = complaint;
                break;
            }
        }

        if (complaintToHandle != null) {
            complaints.remove(complaintToHandle);
            System.out.println("Complaint from " + teacherName + " handled and removed from the list.");
        } else {
            System.out.println("No complaint found from teacher: " + teacherName);
        }
    }
}
