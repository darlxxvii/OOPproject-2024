package SystemParts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FAQ {

    public static class QuestionAnswer {
        private String question;
        private String answer;

        public QuestionAnswer(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }

        public String getQuestion() {
            return question;
        }

        public String getAnswer() {
            return answer;
        }

        @Override
        public String toString() {
            return "Q: " + question + "\nA: " + answer;
        }
    }

    private List<QuestionAnswer> faqList = new ArrayList<>();
    private List<String> unansweredQuestions = new ArrayList<>();

    public FAQ() {
        initializeSampleFAQs();
    }

    private void initializeSampleFAQs() {
        faqList.add(new QuestionAnswer("How to register for a course?", "You can register through your personal account."));
        faqList.add(new QuestionAnswer("How to make a schedule", "Go to the discipline registration section."));
        faqList.add(new QuestionAnswer("How to pay the Student Fee?", "You can pay the fee on kbtu.endownment website."));
        faqList.add(new QuestionAnswer("Working hours of the dean's office?", "8:00 - 18:00, Monday to Sunday"));
        faqList.add(new QuestionAnswer("How to avoid the Retake", "Study hard."));
    }

    public void displayFAQs() {
        for (int i = 0; i < faqList.size(); i++) {
            System.out.println((i + 1) + ". " + faqList.get(i).getQuestion());
        }
    }

    public void getAnswer(int index) {
        if (index >= 0 && index < faqList.size()) {
            System.out.println(faqList.get(index).getAnswer());
        } else {
            System.out.println("Invalid question number.");
        }
    }

    public void saveUnansweredQuestion(String question) {
        unansweredQuestions.add(question);
        System.out.println("Your question has been saved. The manager will answer it later.");
    }

    public void startFAQSession() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                displayFAQs();
                System.out.println("Select a question number to get the answer (or enter 0 to exit): ");

                int choice = Integer.parseInt(reader.readLine());

                if (choice == 0) {
                    System.out.println("Thanks for using the FAQ!");
                    break;
                }

                if (choice > 0 && choice <= faqList.size()) {
                    getAnswer(choice - 1);
                    System.out.println("\nWas this answer helpful? (yes/no)");
                    String feedback = reader.readLine().toLowerCase();

                    if (feedback.equals("yes")) {
                        System.out.println("Glad I could help!\n");
                    } else if (feedback.equals("no")) {
                        System.out.println("Please enter your question:");
                        String newQuestion = reader.readLine();
                        saveUnansweredQuestion(newQuestion);
                    } else {
                        System.out.println("Invalid input. Returning to the list.\n");
                    }
                } else {
                    System.out.println("Incorrect choice. Try again.\n");
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<String> getUnansweredQuestions() {
        return unansweredQuestions;
    }
}
