package Research;

import java.util.List;

import Enums.CitationFormat;

public class ResearchPaper {
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

    @Override
    public String toString() {
        return String.format("Title: %s, Author: %s, Citations: %d, Date: %s, Pages: %d, Keywords: %s",
                title, author, citations, date, pages, keywords);
    }

}


