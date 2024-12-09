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

    public News(String topic, String description) {
        this.topic = topic;
        this.description = description;
        this.comments = new ArrayList<>();
        this.isPinned = false;
        this.status = NewsStatus.NEW;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        sortCommentsByTimestamp();
    }

    private void sortCommentsByTimestamp() {
        comments.sort(Comparator.comparing(Comment::getTimestamp).reversed());
    }

    public void pinTopic() {
        this.isPinned = true;
    }

    public void unpinTopic() {
        this.isPinned = false;
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
        Researcher topCitedResearcher = researchers.stream()
            .max(Comparator.comparingInt(Researcher::getCitationCount))
            .orElse(null);

        if (topCitedResearcher != null && topCitedResearcher.getCitationCount() >= CITATION_THRESHOLD) {
            String topic = "Top Cited Researcher in the University";
            String description = topCitedResearcher.getName() + " is the top cited researcher with " +
                                 topCitedResearcher.getCitationCount() + " citations!";
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

}