package Users;

import java.time.LocalDateTime;
import java.util.regex.Pattern;
import Enums.Languages;
import Database.LogDatabase;
import Database.Loggable;

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

    protected static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$"; // Паттерн для пароля
    protected static final String PHONE_PATTERN = "^\\+?[0-9]{10,15}$"; // Паттерн для номера телефона

    public User(String login, String password, String name, String surname, String email, Languages language, String phoneNumber) {
        this.id = generateUniqueID(); // Генерируем уникальный ID для пользователя
        this.login = login;
        validatePassword(password); // Проверка пароля
        validatePhoneNumber(phoneNumber); // Проверка номера телефона
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.language = language;
        this.phoneNumber = phoneNumber;
        this.isAuthenticated = false; // По умолчанию пользователь не авторизован
    }

    private String generateUniqueID() {
        return "U" + (idCounter++); // Генерация уникального ID
    }

    // Метод для валидации пароля
    public void validatePassword(String password) {
        if (!Pattern.matches(PASSWORD_PATTERN, password)) {
            throw new IllegalArgumentException("Пароль должен содержать хотя бы 8 символов, одну заглавную букву, одну цифру и один специальный символ.");
        }
    }

    // Метод для валидации номера телефона
    public void validatePhoneNumber(String phoneNumber) {
        if (!Pattern.matches(PHONE_PATTERN, phoneNumber)) {
            throw new IllegalArgumentException("Неверный формат номера телефона.");
        }
    }

    @Override
    public void logAction(String action) {
        String logEntry = LocalDateTime.now() + ": " + action; // Формируем лог с текущим временем
        LogDatabase.addLog(logEntry); // Добавляем лог в LogDatabase
    }

    // Метод для авторизации пользователя
    public boolean logIn(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            this.isAuthenticated = true; // Пользователь авторизован
            this.lastLogin = LocalDateTime.now(); // Записываем время последнего входа
            logAction("Пользователь " + name + " вошел в систему в " + lastLogin); // Логируем действие
            return true;
        }
        return false;
    }

    // Метод для выхода пользователя
    public void logOut() {
        this.isAuthenticated = false; // Пользователь выходит
        logAction("Пользователь " + name + " вышел из системы в " + LocalDateTime.now()); // Логируем действие
    }

    // Метод для просмотра профиля пользователя
    public String viewProfile() {
        return "ID: " + id + "\nИмя: " + name + "\nEmail: " + email + "\nТелефон: " + phoneNumber + "\nЯзык: " + language + "\nПоследний вход: " + (lastLogin != null ? lastLogin : "Не был в системе");
    }

    // Метод для обновления профиля
    public void updateProfile(String name, String email) {
        this.name = name;
        this.email = email;
        logAction("Пользователь " + name + " обновил свой профиль."); // Логируем действие
    }

    // Метод для обновления номера телефона
    public void updatePhoneNumber(String newPhoneNumber) {
        validatePhoneNumber(newPhoneNumber);  // Проверяем корректность нового номера
        this.phoneNumber = newPhoneNumber; // Обновляем номер телефона
        logAction("Пользователь " + name + " обновил номер телефона на " + newPhoneNumber); // Логируем действие
    }

    // Метод для обновления пароля
    public void updatePassword(String newPassword) {
        validatePassword(newPassword); // Проверяем корректность нового пароля
        this.password = newPassword; // Обновляем пароль
        logAction("Пользователь " + name + " обновил свой пароль."); // Логируем действие
    }

    public String getName() {
        return name; // Возвращаем имя пользователя
    }
}
