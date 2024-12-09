package SystemParts;

public class Review {

    private static int reviewCounter = 0; 
    private String reviewText; 
    private int rating;       
    private int reviewerId;     
    public Review(String reviewText, int rating) {
        this.reviewText = reviewText;
        this.rating = rating;
        this.reviewerId = ++reviewCounter;  
    }

    public String getText() {
        return reviewText; 
    }

    public int getRating() {
        return this.rating;
    }

    public int getReviewerId() {
        return reviewerId;  
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

}


