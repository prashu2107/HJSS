public class Review {
    private String learnerName;
    private int rating;
    private String comments;

    public Review(String learnerName, int rating, String comments) {
        this.learnerName = learnerName;
        this.rating = rating;
        this.comments = comments;
    }

    public String getLearnerName() {
        return learnerName;
    }

    public int getRating() {
        return rating;
    }
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
        
    }
}
