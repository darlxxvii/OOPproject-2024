package Users;

import java.util.*;
import java.io.*;
import java.util.List;

import Enums.Languages;

import java.util.ArrayList;
import java.time.LocalDateTime;

class Admin extends User {
    private List<User> userLog;

    public Admin(String login, String password, String name, String surname, String id, Languages en, String phoneNumber) {
        super(login, password, name, surname, id, en, phoneNumber);
        this.userLog = loadLog();
    }

    public void createUser(User user) {
        userLog.add(user);
        logAction("User created: " + user.getName() + " at " + LocalDateTime.now());
        saveLog();
    }

    public void removeUser(User user) {
        if (userLog.contains(user)) {
            userLog.remove(user);
            logAction("User removed: " + user.getName() + " at " + LocalDateTime.now());
            saveLog();
        } else {
            logAction("Attempted to remove a non-existent user.");
        }
    }

    public List<User> seeAllUsers() {
        logAction("Admin viewed all users at " + LocalDateTime.now());
        return userLog;
    }

    public void addResearcher(Employee employee) {
        if (employee.isResearcher()) {
            createUser(employee);
            logAction("Researcher added: " + employee.getName() + " at " + LocalDateTime.now());
        } else {
            logAction("Attempted to add a non-researcher as a researcher.");
        }
    }

    public void updateUser(User user) {
        logAction("Admin updated user: " + user.getName());
        saveLog();
    }

    private void logAction(String message) {
        System.out.println(message);
    }

    private void saveLog() {
        try (FileOutputStream fos = new FileOutputStream("user_log.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(userLog);
        } catch (IOException e) {
            System.err.println("Error saving log: " + e.getMessage());
        }
    }

    private List<User> loadLog() {
        try (FileInputStream fis = new FileInputStream("user_log.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
