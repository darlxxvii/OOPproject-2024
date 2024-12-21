package Users;


import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import Enums.Languages;

public abstract class User implements Serializable {
    private static int idCounter = 1; 
    private String id; 
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private Languages language;
    private String phoneNumber;
    private LocalDateTime lastLogin;
    private boolean isAuthenticated;

    
    protected static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";
    protected static final String PHONE_PATTERN = "^\\+?[0-9]{10,15}$";
    public User() {
    	
    }
    public User(String name, String surname,String email) {
    	this.name=name;
    	this.surname=surname;
    	this.email=email;
        this.id = generateUniqueID();
    }
    public User(String login, String password, String name, String surname, String email, Languages language, String phoneNumber) {
        this.id = generateUniqueID();
        this.login = login;
        validatePassword(password);
        validatePhoneNumber(phoneNumber);
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.language = language;
        this.phoneNumber = phoneNumber;
        this.isAuthenticated = false;
    }
    
    protected String generateUniqueID() {
        return "U" + (idCounter++);
    }
 
    public void validatePassword(String password) {
        if (!Pattern.matches(PASSWORD_PATTERN, password)) {
            throw new IllegalArgumentException("Password must have at least 8 characters, one uppercase, one number, and one special character.");
        }
    }

    
    public void validatePhoneNumber(String phoneNumber) {
        if (!Pattern.matches(PHONE_PATTERN, phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number format.");
        }
    }

 
    public boolean logIn(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            this.isAuthenticated = true;
            this.lastLogin = LocalDateTime.now();
            return true;
        }
        return false;
    }

    
    public void logOut() {
        this.isAuthenticated = false;
    }

 
    public void sendMessage(User receiver, String message) {
        if (receiver != null && receiver != this) {
            System.out.println("Message sent to " + receiver.getName() + ": " + message);
        } else {
            System.out.println("Invalid receiver.");
        }
    }

    
    public void changeLanguage(Languages lang) {
        this.language = lang;
    }

    
    public String viewProfile() {
        return "ID: " + id +
                "\nName: " + name +
                "\nEmail: " + email +
                "\nPhone: " + phoneNumber +
                "\nPreferred Language: " + language +
                "\nLast Login: " + (lastLogin != null ? lastLogin : "Not logged in");
    }

   
    public void updateProfile(String name, String email) {
        this.name = name;
        this.email = email;
    }

  
    public boolean isLoggedIn() {
        return isAuthenticated;
    }

    
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public int getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Languages getLanguage() {
        return language;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        validatePhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        validatePassword(password);
        this.password = password;
    }

    public void setLanguage(Languages language) {
        this.language = language;
    }
}
