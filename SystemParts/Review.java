package SystemParts;

public class Review {

    private static int reviewCounter = 0; 
    private String reviewText; 
    private int rating;       
    private int reviewerId;     
    private boolean isAnonymous;  

    public Review(String reviewText, int rating, boolean isAnonymous) {
        this.reviewText = reviewText;
        this.rating = rating;
        this.reviewerId = ++reviewCounter;  
        this.isAnonymous = isAnonymous;  
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

    public boolean isAnonymous() {
        return isAnonymous;  
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setAnonymous(boolean isAnonymous) {
        this.isAnonymous = isAnonymous;  
    }
}
