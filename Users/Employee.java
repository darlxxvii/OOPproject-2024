package Users;

import java.time.LocalDateTime;

import Enums.Languages;

public class Employee extends User {
    private boolean isResearcher;  // Флаг, является ли сотрудник исследователем
    private String salary;  // Зарплата сотрудника

    // Конструктор для создания сотрудника
    public Employee(String login, String password, String name, String surname, String id, Languages en, String phoneNumber, boolean isResearcher, String salary) {
        super(login, password, name, surname, id, en, phoneNumber);
        this.isResearcher = isResearcher;  // Устанавливаем флаг, является ли сотрудник исследователем
        this.salary = salary;  // Устанавливаем зарплату
    }
     public void becomeResearcher() {
        if (isResearcher) {
            System.out.println(this.getName() + " is already a researcher.");
            return;
        }
        isResearcher = true;
        this.researcherHelper = new ResearcherHelper(); // Ensure researcherHelper is initialized
        System.out.println(getName() + " has chosen to become a researcher.");
    }
    // Метод для отправки запроса
    public void sendRequest() {
        logAction(getName() + " отправил запрос в " + LocalDateTime.now());  // Логируем отправку запроса
    }

    // Метод для просмотра всех научных работ
    public void viewAllPapers() {
        logAction(getName() + " просматривает все научные работы.");  // Логируем просмотр всех научных работ
    }

    // Метод для проведения исследования
    public void conductResearch() {
        logAction(getName() + " проводит исследование...");  // Логируем начало исследования
    }

    // Метод для публикации научных работ
    public void publishPapers() {
        logAction(getName() + " опубликовал новые научные работы.");  // Логируем публикацию научных работ
    }

    // Геттеры и сеттеры для дополнительных полей (необязательно, если они не нужны в дальнейшем)
    public boolean isResearcher() {
        return isResearcher;  // Возвращаем статус исследователя
    }

    public void setResearcher(boolean researcher) {
        isResearcher = researcher;  // Устанавливаем статус исследователя
    }

    public String getSalary() {
        return salary;  // Возвращаем зарплату
    }

    public void setSalary(String salary) {
        this.salary = salary;  // Устанавливаем зарплату
    }
}
