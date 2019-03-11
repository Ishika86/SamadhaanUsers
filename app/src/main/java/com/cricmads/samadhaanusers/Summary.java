package com.cricmads.samadhaanusers;

public class Summary {
    String delayed, interest, rating, successful;

    public Summary() {
    }

    public Summary(String delayed, String interest, String rating, String successful) {
        this.delayed = delayed;
        this.interest = interest;
        this.rating = rating;
        this.successful = successful;
    }

    public String getDelayed() {
        return delayed;
    }

    public void setDelayed(String delayed) {
        this.delayed = delayed;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSuccessful() {
        return successful;
    }

    public void setSuccessful(String successful) {
        this.successful = successful;
    }
}
