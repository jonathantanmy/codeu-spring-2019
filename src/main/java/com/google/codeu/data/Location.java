package com.google.codeu.data;

import java.util.Collection;
import java.util.UUID;

public class Location {
    public String name;
    public String description;
    public String imageUrl;
    public String imageLabels;
    private String id;
    public Collection<Review> reviews;

    // Helper class to hold reviews.
    public static class Review {
        public String title;
        public String body;

        public Review(String title, String body) {
            this.title = title;
            this.body = body;
        }

    }

    public Location(String name, String description, String imageUrl, String imageLabels) {
        this(UUID.randomUUID(),name, description, imageUrl, imageLabels);
    }
    public Location(UUID uuid, String name, String description, String imageUrl, String imageLabels) {
        this.id= uuid.toString();
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.imageLabels = imageLabels;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getID() {
        return id;
    }

    public String getImageURL() {
        return imageUrl;
    }

    public String getImageLabels() {
        return imageLabels;
    }


    // removes a review from a collection
    public void deleteReview(Review review) {
        reviews.remove(review);
    }

    // removes all reviews from a collection
    public  void deleteAllReviews(Review review) {
        reviews.clear();
    }

    // Returns all reviews
    public Collection<Review> getReviews() { return reviews; }

}
