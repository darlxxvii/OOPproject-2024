package Research;

import java.util.ArrayList;
import java.util.List;

public class ResearchProject {
    private String topic;
    private List<ResearchPaper> publishedPapers;
    private List<Researcher> participants;

    public ResearchProject(String topic) {
        this.topic = topic;
        this.publishedPapers = new ArrayList<>();
        this.participants = new ArrayList<>();
    }


    public String getTopic() {
        return topic;
    }

    public List<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }

    public List<Researcher> getParticipants() {
        return participants;
    }

    public void addResearchPaper(ResearchPaper paper) {
        if (paper != null) {
            publishedPapers.add(paper);
            System.out.println("Research paper added: " + paper.getTitle());
        } else {
            throw new IllegalArgumentException("Paper cannot be null.");
        }
    }

    public void addParticipant(Researcher researcher) {
        if (researcher != null) {
            participants.add(researcher);
            System.out.println("Researcher added to the project: " + researcher);
        } else {
            throw new IllegalArgumentException("Only valid researchers can join the project.");
        }
    }

    public void printPublishedPapers() {
        System.out.println("Published papers for project '" + topic + "':");
        publishedPapers.forEach(System.out::println);
    }

    public void joinProject(Researcher researcher) {
        if (researcher != null) {
            addParticipant(researcher);
            System.out.println(researcher + " has joined the project.");
        } else {
            throw new IllegalArgumentException("Only researchers can join the project.");
        }
    }

    public void conductResearch() {
        System.out.println("Conducting research on topic: " + topic);
        for (Researcher researcher : participants) {
            researcher.conductResearch(topic);
        }
    }

    public int getTotalCitations() {
        return publishedPapers.stream().mapToInt(ResearchPaper::getCitations).sum();
    }


    @Override
    public String toString() {
        return String.format("Research Project: %s, Participants: %d, Published Papers: %d, Total Citations: %d",
                topic, participants.size(), publishedPapers.size(), getTotalCitations());
    }
}
