package Users;

import java.util.ArrayList;
import java.util.List;

import Enums.UrgencyLevel;
import SystemParts.Complaint;
import SystemParts.Request;

public class Dean extends Employee{
    private static Dean instance;
    private List<String> facultyMembers;
    private List<Request> studentRequests;
    private List<Complaint> complaints;
   
    private Dean(String name, String surname, String email) {
        super(name, surname, email);
        this.id = generateUniqueID();
        facultyMembers = new ArrayList<>();
        studentRequests = new ArrayList<>();
        complaints = new ArrayList<>();
    }
    public static Dean getInstance(String name,String surname, String email) {
        if (instance == null) {
            instance = new Dean(name,surname,email);
        }
        return instance;
    }

    public void addFacultyMember(String name) {
        facultyMembers.add(name);
        System.out.println("Added faculty member: " + name);
    }

    public void viewRequests() {
        if (studentRequests.isEmpty()) {
            System.out.println("No student requests at the moment.");
            return;
        }
        System.out.println("Current student requests:");
        for (Request request : studentRequests) {
            System.out.println("- Request from " + request.getSender().getName() + ": " + request.getContent() +
                    (request.isSigned() ? " (Signed by: " + request.getSignedBy() + ")" : " (Not signed)"));
        }
    }

    public void approveStudentRequest(String request) {
        studentRequests.remove(request);
        System.out.println("Request approved: " + request);
    }

    public void receiveRequest(String studentName, Request request) {
        studentRequests.add(request);
        System.out.println("Request added to the queue for processing: " + request.getContent());
    }


    public void handleRequest(Request request) {
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
        if (complaints.isEmpty()) {
            System.out.println("No complaints at the moment.");
            return;
        }
        System.out.println("List of complaints:");
        for (Complaint complaint : complaints) {
            System.out.println("- From " + complaint.getTeacherName() + ": " + complaint.getComplaintText() +
                    " (Urgency: " + complaint.getUrgencyLevel() + ")");
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
    public void handleComplaintByUrgency() {
        complaints.sort((c1, c2) -> c2.getUrgencyLevel().compareTo(c1.getUrgencyLevel()));
        System.out.println("Complaints sorted by urgency:");
        for (Complaint complaint : complaints) {
            System.out.println(complaint.getTeacherName() + ": " + complaint.getComplaintText() + " (Urgency: " + complaint.getUrgencyLevel() + ")");
        }
    }

}
