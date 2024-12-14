package Patterns;

public class EmailNotificationStrategy implements NotificationStrategy{

	@Override
	public void sendNotification(String subscriberName, String journalName, String paperTitle) {
		 System.out.println("Sending email to " + subscriberName + ": New paper '" + paperTitle + "' published in " + journalName);
	}
	
}
