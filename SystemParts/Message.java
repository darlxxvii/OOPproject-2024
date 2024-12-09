package SystemParts;

import java.time.LocalDateTime;

import Enums.Status;
import Enums.UrgencyLevel;
import Users.User;

public class Message {
    private User sender;            
    private User recipient;         
    private String content;        
    private LocalDateTime timestamp; 
    private Status status;   
    private UrgencyLevel priority; 

    public Message(User sender, User recipient, String content, UrgencyLevel priority) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.status = Status.UNREAD;
        this.priority = priority;
    }

    public void markAsRead() {
        this.status =Status.READ;
    }

    public boolean filterByPriority(UrgencyLevel priority) {
        return this.priority == priority;
    }

    public Status getStatus() {
        return status;
    }

    public UrgencyLevel getPriority() {
        return priority;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String toString() {
        return "Message from: " + sender.getName() +
               " to: " + recipient.getName() + 
               " [Urgency level: " + priority + ", Status: " + status + "] at " + timestamp + 
               " Content: " + content;
    }
}

