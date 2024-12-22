package Research;

import Enums.CitationFormat;
import java.io.*;
import java.util.List;

public class ResearchPaper implements Serializable {
    private static final long serialVersionUID = 1L;  // Ensures backward compatibility in case the class changes
    
    private String title;
    private String author;
    private int citations;
    private String date;
    private int pages;
    private List<String> keywords;

    public ResearchPaper(String title, String author, int citations, String date, int pages, List<String> keywords) {
        this.title = title;
        this.author = author;
        this.citations = citations;
        this.date = date;
        this.pages = pages;
        this.keywords = keywords;
    }
    
    public String getCitationFormat(CitationFormat format) {
        switch (format) {
            case BIBTEX:
                return String.format("@article{%s, author={%s}, title={%s}, year={%s}}",
                        title.replaceAll("\\s+", "_"), author, title, date.split("-")[0]);
            case PLAIN_TEXT:
                return String.format("%s. \"%s\" (%s).", author, title, date.split("-")[0]);
            default:
                return "Invalid format";
        }
    }

    // Method to save the object to a file using serialization
    public void serializeToFile(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(this);  // Serialize the current object
            System.out.println("Research paper saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error serializing the object: " + e.getMessage());
        }
    }

    public static ResearchPaper deserializeFromFile(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (ResearchPaper) in.readObject();  // Deserialize the object
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing the object: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("Title: %s, Author: %s, Citations: %d, Date: %s, Pages: %d, Keywords: %s",
                title, author, citations, date, pages, keywords);
    }

    // Getter methods

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getCitations() {
        return citations;
    }

    public String getDate() {
        return date;
    }

    public int getPages() {
        return pages;
    }

    public List<String> getKeywords() {
        return keywords;
    }

}
