package SystemParts;

import java.util.ArrayList;
import java.util.List;

import Enums.NewsStatus;
import Research.Researcher;

import java.util.*;

public class News {
    private String topic;
    private String description;
    private List<Comment> comments;
    private Boolean isPinned;
    private NewsStatus status; 
    private static List<News> allNews = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();
    
    public News() {
    	
    }

    public News(String topic, String description) {
        this.topic = topic;
        this.description = description;
        this.comments = new ArrayList<>();
        this.isPinned = false;
        this.status = NewsStatus.NEW;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
    
    public static void createAnnouncementForResearcherPaper(String researcherName, String paperTitle) {
        String topic = "Research Paper Published by " + researcherName;
        String description = "New research paper published: " + paperTitle;
        News announcement = new News(topic, description);
        announcement.pinTopic(); 
        allNews.add(announcement);
        System.out.println("Announcement created for researcher paper: " + paperTitle);
    }

    public static void createAnnouncementForTopCitedResearcher(List<Researcher> researchers) {
        if (researchers == null || researchers.isEmpty()) {
            System.out.println("No researchers available.");
            return;
        }

        Researcher topCitedResearcher = null;
        for (Researcher researcher : researchers) {
            if (topCitedResearcher == null || 
                Integer.compare(researcher.getTotalCitations(), topCitedResearcher.getTotalCitations()) > 0) {
                topCitedResearcher = researcher;
            }
        }

        if (topCitedResearcher != null) {
            String topic = "Top Cited Researcher in the University";
            String description = topCitedResearcher.getName() + " is the top cited researcher with " +
                                 topCitedResearcher.getTotalCitations() + " citations!";
            News announcement = new News(topic, description);
            allNews.add(announcement);
            System.out.println("Announcement created for top cited researcher: " + topCitedResearcher.getName());
        }
    }


    public static List<News> getAllNews() {
        return allNews;
    }

    public String getTopic() {
        return topic;
    }

    public String getDescription() {
        return description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Boolean getIsPinned() {
        return isPinned;
    }

    public NewsStatus getStatus() {
        return status;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(NewsStatus status) {
        this.status = status;
    }
    

    public void removeComment(Comment comment) {
        if (comments.remove(comment)) {
            System.out.println("Comment removed: " + comment.getContent());
        } else {
            System.out.println("Comment not found.");
        }
    }
    
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
    
    public void pinTopic() {
        this.isPinned = true;
        notifyObservers("The topic '" + topic + "' has been pinned.");
    }

    public void unpinTopic() {
        this.isPinned = false;
        notifyObservers("The topic '" + topic + "' has been unpinned.");
    }
    
    public static void createAnnouncement(String topic, String description, List<Observer> observers) {
        News announcement = new News(topic, description);
        announcement.pinTopic();
        allNews.add(announcement);

        for (Observer observer : observers) {
            observer.update(topic + " - " + description);
        }
    }

}
