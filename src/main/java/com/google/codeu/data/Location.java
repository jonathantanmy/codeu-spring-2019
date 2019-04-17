package com.google.codeu.data;

import java.util.UUID;

public class Location {
    public String name;
    public String description;
    private String imageUrl;
    private String imageLabels;
    private UUID id;


    public Location(String name, String description, String imageUrl, String imageLabels) {
        this(UUID.randomUUID(),name, description, imageUrl, imageLabels);
    }
    public Location(UUID uuid, String name, String description, String imageUrl, String imageLabels) {
        this.id= uuid;
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

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public UUID getID() {
        return id;
    }


}
