package SystemParts;

import java.util.ArrayList;
import java.util.List;

import Patterns.NotificationStrategy;
import Research.ResearchPaper;
import Users.User;

public class Journal {
	private String name;
	private List<ResearchPaper> researchPapers;
	private List<User> subscribers;
	private NotificationStrategy notificationStrategy; 
	
	public Journal() {
		
	}
	
	public Journal(String name, NotificationStrategy notificationStrategy) {
        this.name = name;
        this.researchPapers = new ArrayList<>();
        this.subscribers = new ArrayList<>();
        this.notificationStrategy = notificationStrategy;
    }
	
	public void addResearchPaper(ResearchPaper paper) {
		researchPapers.add(paper);
}
	public void addSubscriber(User user) {
        if (!subscribers.contains(user)) {
            subscribers.add(user);
            System.out.println(user.getName() + " subscribed to " + name);
        }
	}
	
	public void removeSubscriber(User user) {
        subscribers.remove(user);
        System.out.println(user.getName() + " unsubscribed from " + name);
    }

    private void notifySubscribers(ResearchPaper paper) {
        for (User user : subscribers) {
            notificationStrategy.sendNotification(user.getName(), name, paper.getTitle());
        }
    }
    
    public String getName() {
        return name;
    }

    public List<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

}
