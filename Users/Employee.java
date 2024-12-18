package Users;

import java.time.LocalDateTime;

import Enums.Languages;

class Employee extends User {
    private boolean isResearcher;
    private String salary;
    public Employee() {
    	super();
    }
    public Employee(String login, String password, String name, String surname, String id, Languages en, String phoneNumber, boolean isResearcher, String salary) {
        super(login, password, name, surname, id, en , phoneNumber);
        this.isResearcher = isResearcher;
        this.salary = salary;
    }
 
    public boolean isResearcher() {
        return isResearcher;
    }

    public void setIsResearcher(boolean isResearcher) {
        this.isResearcher = isResearcher;
    }

    public String getSalary() {
        return salary;
    }

    

    public void sendRequest() {
        System.out.println(getName() + " sent a request at " + LocalDateTime.now());
    }

    public void viewAllPapers() {
        System.out.println(getName() + " is viewing all research papers.");
    }

    public void conductResearch() {
        System.out.println(getName() + " is conducting research...");
    }

    public void publishPapers() {
        System.out.println(getName() + " published new research papers.");
    }
}
