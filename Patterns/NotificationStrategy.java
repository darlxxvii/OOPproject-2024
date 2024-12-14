package Patterns;

public interface NotificationStrategy {
	void sendNotification(String subscriberName, String journalName, String paperTitle);
}
