package SystemParts;

import java.util.ArrayList;
import java.util.List;

public class NewsNotifier {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void createNews(String topic, String description) {
        SystemParts.News news = new SystemParts.News(topic, description);
        SystemParts.News.getAllNews().add(news);
        notifyObservers("New announcement: " + topic + " - " + description);
    }
}
