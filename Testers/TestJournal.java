package SystemParts;
import java.util.ArrayList;
import java.util.List;

import Enums.DegreeLevel;
import Enums.EducationalProgram;
import Enums.Positions;
import Enums.Schools;
import Users.Student;
import Users.Teacher;
import Users.User;
import Pattern.EmailNotificationStrategy;
import Pattern.NotificationStrategy;
import Pattern.SMSNotificationStrategy;
import Pattern.SystemMessageNotificationStrategy;
import Research.ResearchPaper;

public class TestJournal {
    public static void main(String[] args) {
        NotificationStrategy emailStrategy = new EmailNotificationStrategy();
        NotificationStrategy smsStrategy = new SMSNotificationStrategy();
        NotificationStrategy systemMessageStrategy = new SystemMessageNotificationStrategy();

        Journal journal = new Journal("Tech Innovations", emailStrategy);

        User student1 = new Student("Alice", "Johnson", "alice.johnson@example.com", 2, DegreeLevel.BACHELOR, Schools.SITE, EducationalProgram.IS);
        User student2 = new Student("Bob", "Smith", "bob.smith@example.com", 2, DegreeLevel.BACHELOR, Schools.SITE, EducationalProgram.ITM);

        journal.addSubscriber(student1);
        journal.addSubscriber(student2);

        ResearchPaper paper1 = new ResearchPaper("Quantum Computing Advances", "Bob", 200, "18.11.24", 15, List.of("Quantum Computing", "Quantum Mechanics", "Qubit", "Entanglement"));
        journal.addResearchPaper(paper1);

        System.out.println("\n--- Notifying subscribers (Email) ---");
        journal.notifySubscribers(paper1); 

        journal.setNotificationStrategy(smsStrategy);  

        ResearchPaper paper2 = new ResearchPaper("Advances in Artificial Intelligence", "Alice", 150, "15.10.24", 12, List.of("AI", "Machine Learning", "Neural Networks"));
        System.out.println("\n--- Notifying subscribers (SMS) ---");
        journal.notifySubscribers(paper2); 


        journal.setNotificationStrategy(systemMessageStrategy);  


        User teacher1 = new Teacher("Dr. Sarah", "Williams", "dr.sarah.williams@example.com", Positions.PROFESSOR, 10, DegreeLevel.PHD);
        journal.addSubscriber(teacher1);


        ResearchPaper paper3 = new ResearchPaper("Renewable Energy Innovations", "Charlie", 95, "01.12.24", 18, List.of("Renewable Energy", "Solar Power", "Wind Energy"));
        System.out.println("\n--- Notifying subscribers (System Message) ---");
        journal.notifySubscribers(paper3);  
    }
}
