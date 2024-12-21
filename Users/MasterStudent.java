package Users;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;

import Enums.DegreeLevel;
import Enums.EducationalProgram;
import Research.ResearchPaper;
import Research.Researcher;
import SystemParts.School;

public class MasterStudent extends Student implements Researcher {
    private int credits;
    private int yearOfStudy;
    private List<ResearchPaper> publishedPapers;

    public MasterStudent(String name, String surname, String email, int enrollmentYear, DegreeLevel degreeLevel, School school, EducationalProgram educationalProgram) {
        super(name, surname, email, enrollmentYear, DegreeLevel.MASTER, school, educationalProgram);
        this.publishedPapers = new ArrayList<>();
    }

    public void earnCredits(int earnedCredits) {
        credits += earnedCredits;
        System.out.println("Total earned: " + credits);
        if (credits == 18) {
            System.out.println("You have completed your credits for the degree.");
        }
    }

    public boolean isEligibleForGraduation() {
        return credits >= 18 && yearOfStudy == 2;
    }

    // Researcher methods
    @Override
    public void conductResearch(String topic) {
        System.out.println(getName() + " is conducting research on " + topic);
    }

    @Override
    public void publishPaper(ResearchPaper paper) {
        publishedPapers.add(paper);
        System.out.println(getName() + " published a paper: " + paper.getTitle());
    }

    @Override
    public List<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }

    @Override
    public int calculateHIndex() {
        // Сортируем опубликованные работы по количеству цитирований в порядке убывания
        List<Integer> citations = publishedPapers.stream()
                .map(ResearchPaper::getCitations)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());

        int hIndex = 0;
        for (int i = 0; i < citations.size(); i++) {
            if (citations.get(i) >= (i + 1)) {
                hIndex = i + 1; // Находим наибольшее h, для которого работает условие
            } else {
                break;
            }
        }
        return hIndex;
    }

    @Override
    public int getTotalCitations() {
        // Суммируем все цитирования опубликованных статей
        return publishedPapers.stream().mapToInt(ResearchPaper::getCitations).sum();
    }
}
