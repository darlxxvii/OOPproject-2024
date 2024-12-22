import java.time.LocalDateTime;
import java.util.regex.Pattern;
import java.util.logging.Logger;

public class User implements Loggable {
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

    private static final Logger logger = Logger.getLogger(User.class.getName()); // Логгер

    protected static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";
    protected static final String PHONE_PATTERN = "^\\+?[0-9]{10,15}$";

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

    private String generateUniqueID() {
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

    @Override
    public void logAction(String action) {
        logger.info(action);
    }

    public boolean logIn(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            this.isAuthenticated = true;
            this.lastLogin = LocalDateTime.now();
            logAction("User " + name + " logged in at " + lastLogin);
            return true;
        }
        return false;
    }

    public void logOut() {
        this.isAuthenticated = false;
        logAction("User " + name + " logged out at " + LocalDateTime.now());
    }

    public String viewProfile() {
        return "ID: " + id + "\nName: " + name + "\nEmail: " + email + "\nPhone: " + phoneNumber + "\nPreferred Language: " + language + "\nLast Login: " + (lastLogin != null ? lastLogin : "Not logged in");
    }

    public void updateProfile(String name, String email) {
        this.name = name;
        this.email = email;
        logAction("User " + name + " updated their profile.");
    }

    public String getName() {
        return name;
    }
}
