package com.google.codeu.data;

import java.util.UUID;

public class Location {
    private String name;
    private String description;
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

    public UUID getID() {
        return id;
    }

    public String getImageURL() {
        return imageUrl;
    }

    public String getImageLabels() {
        return imageLabels;
    }


}
