package SystemParts;
import Enums.UrgencyLevel;

public class Complaint {
    private String teacherName;
    private String complaintText;
    private UrgencyLevel urgencyLevel;

    public Complaint(String teacherName, String complaintText, UrgencyLevel urgencyLevel) {
        this.teacherName = teacherName;
        this.complaintText = complaintText;
        this.urgencyLevel = urgencyLevel;
    }

	public String getTeacherName() {
        return teacherName;
    }

    public String getComplaintText() {
        return complaintText;
    }

    public UrgencyLevel getUrgencyLevel() {
        return urgencyLevel;
    }
}
