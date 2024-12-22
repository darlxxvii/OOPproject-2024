package Users;

import Database.LogDatabase;
import Enums.Languages;

import java.util.List;

public class Admin extends User {

    public Admin(String login, String password, String name, String surname, String id, Languages language, String phoneNumber) {
        super(login, password, name, surname, id, language, phoneNumber);
    }

    // Метод для просмотра всех логов
    public List<String> viewAllLogs() {
        List<String> logs = LogDatabase.getAllLogs(); // Получаем все логи
        System.out.println("Администратор просматривает логи:");
        for (String log : logs) {
            System.out.println(log); // Выводим логи на экран
        }
		return logs;
    }

    // Метод для создания пользователя
    public void createUser(User user) {
        logAction("Пользователь создан: " + user.getName()); // Логируем создание пользователя
    }

    // Метод для удаления пользователя
    public void removeUser(User user) {
        logAction("Пользователь удален: " + user.getName()); // Логируем удаление пользователя
    }
}
