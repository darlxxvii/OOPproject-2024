package SystemParts;

import java.time.LocalDateTime;

import Users.User;

public class Comment {
    private User author;  
    private String content; 
    private LocalDateTime timestamp; 
    
    public Comment() {
    	
    }

    public Comment(User author, String content) {
        this.author = author;
        this.content = content;
        this.timestamp = LocalDateTime.now(); 
    }

    public void leaveComment(String content) {
        this.content = content;  
        this.timestamp = LocalDateTime.now(); 
        System.out.println("Comment by " + author.getName() + " at " + timestamp + ": " + content);
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
