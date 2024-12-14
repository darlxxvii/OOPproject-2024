package Systemparts;


public class Mark {
    private Student student;
    private Course course;
    private double firstAttestation;
    private double secondAttestation;
    private double finalExam;
    private double total;

    public Mark(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.firstAttestation = 0;
        this.secondAttestation = 0;
        this.finalExam = 0;
        this.total = 0;
    }

    public void setAttestationMarks(double firstAttestation, double secondAttestation) {
        this.firstAttestation = firstAttestation;
        this.secondAttestation = secondAttestation;
    }

    public boolean canTakeFinalExam() {
        double attestationTotal = firstAttestation + secondAttestation;
        return attestationTotal >= 30;
    }

    public void setFinalExamMark(double finalExam) {
        if (canTakeFinalExam()) {
            this.finalExam = finalExam;
            calculateTotal();
        } else {
            System.out.println("Cannot set final exam mark — student is not eligible.");
        }
    }

    // Calculate total mark
    public double calculateTotal() {
        this.total = firstAttestation + secondAttestation + finalExam;
        return total;
    }

    public String getGrade() {
        if (total >= 95) return "A (GPA: 4.0)";
        else if (total >= 90) return "A- (GPA: 3.7)";
        else if (total >= 85) return "B+ (GPA: 3.3)";
        else if (total >= 80) return "B (GPA: 3.0)";
        else if (total >= 75) return "B- (GPA: 2.7)";
        else if (total >= 70) return "C+ (GPA: 2.3)";
        else if (total >= 65) return "C (GPA: 2.0)";
        else if (total >= 60) return "C- (GPA: 1.7)";
        else if (total >= 55) return "D+ (GPA: 1.3)";
        else if (total >= 50) return "D (GPA: 1.0)";
        else if (total >= 30) return "FX (GPA: 0.5)";
        else return "F (GPA: 0.0)";
    }

    public double getFirstAttestation() {
        return firstAttestation;
    }

    public double getSecondAttestation() {
        return secondAttestation;
    }

    public double getFinalExam() {
        return finalExam;
    }

    public double getTotal() {
        return total;
    }
}