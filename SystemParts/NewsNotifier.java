package Systemparts;

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
        News news = new News(topic, description);
        News.getAllNews().add(news);
        notifyObservers("New announcement: " + topic + " - " + description);
    }
}