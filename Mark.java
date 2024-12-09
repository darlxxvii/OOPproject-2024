package SystemParts;

import users.Student;

public class Mark {
    private Student student;
    private Course course;
    private double firstAttestation;
    private double secondAttestation;
    private double finalExam;
    private double total;

    // Конструктор
    public Mark(Student student, Course course, double firstAttestation, double secondAttestation, double finalExam) {
        this.student = student;
        this.course = course;
        this.firstAttestation = firstAttestation;
        this.secondAttestation = secondAttestation;
        this.finalExam = finalExam;
        calculateTotal();
    }

    // Метод для подсчета общей оценки
    public double calculateTotal() {
        this.total = firstAttestation + secondAttestation + finalExam;
        return total;
    }

    public String getGrade() {
        if (total >= 95) {
            return "A";
        } else if (total >= 90) {
            return "A-";
        } else if (total >= 85) {
            return "B+";
        } else if (total >= 80) {
            return "B";
        } else if (total >= 75) {
            return "B-";
        } else if (total >= 70) {
            return "C+";
        } else if (total >= 65) {
            return "C";
        } else if (total >= 60) {
            return "C-";
        } else if (total >= 55) {
            return "D+";
        } else if (total >= 50) {
            return "D";
        } else if (total >= 30) {
            return "FX";
        } else {
            return "F";
        }
    }

    public void updateMark(String type, double value) {
        switch (type.toLowerCase()) {
            case "first":
                this.firstAttestation = value;
                break;
            case "second":
                this.secondAttestation = value;
                break;
            case "final":
                this.finalExam = value;
                break;
            default:
                System.out.println("Invalid mark type");
                return;
        }
        calculateTotal();
    }

    // Геттер для total
    public double getTotal() {
        return total;
    }
}