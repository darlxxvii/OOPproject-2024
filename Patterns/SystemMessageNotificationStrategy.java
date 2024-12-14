package Patterns;

public class SystemMessageNotificationStrategy implements NotificationStrategy{

	@Override
	public void sendNotification(String subscriberName, String journalName, String paperTitle) {
		 System.out.println("Sending message to " + subscriberName + ": New paper '" + paperTitle + "' published in " + journalName);
		
	}

}
