/*
 * Copyright 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.codeu.data;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** Provides access to the data stored in Datastore. */
public class Datastore {

  private DatastoreService datastore;

  public Datastore() {
    datastore = DatastoreServiceFactory.getDatastoreService();
  }

  /** Stores the Message in Datastore. */
  public void storeMessage(Message message) {
    Entity messageEntity = new Entity("Message", message.getId().toString());
    messageEntity.setProperty("user", message.getUser());
    messageEntity.setProperty("text", message.getText());
    messageEntity.setProperty("timestamp", message.getTimestamp());
    messageEntity.setProperty("recipient", message.getRecipient());
    messageEntity.setProperty("sentimentScore", message.getSentimentScore());

    if(message.getImageUrl() != null) {
        messageEntity.setProperty("imageUrl", message.getImageUrl());
    }

    messageEntity.setProperty("imageLabels", message.getImageLabels());

    datastore.put(messageEntity);
  }

    /** Stores the Location in Datastore. */
    public void storeLocation(Location location) {
        Entity locationEntity = new Entity("Location", location.getID().toString());
        locationEntity.setProperty("name", location.getName());
        locationEntity.setProperty("description", location.getDescription());
        locationEntity.setProperty("imageUrl", location.getImageURL());
        locationEntity.setProperty("imageLabels", location.getImageLabels());
        locationEntity.setProperty("reviews", location.getReviews());
        locationEntity.setProperty("idString", location.getID());

        datastore.put(locationEntity);
    }

    /** Retrieves Location Entity with specific id in Datastore. */
    public Entity getLocation(String id) {

        Query.Filter idFilter = new Query.FilterPredicate("idString", FilterOperator.EQUAL, id);
        Query query = new Query("location").setFilter(idFilter);
        PreparedQuery results = datastore.prepare(query);
        return results.asSingleEntity();
    }

    /**
   * Gets messages addressed to a specific recipient.
   *
   * @return a list of messages where the user is the recipient instead of the author, or empty list if user has never posted a
   *     message. List is sorted by time descending.
   */
  public List<Message> getMessages(String recipient) {
    List<Message> messages = new ArrayList<>();

    Query query =
        new Query("Message")
            .setFilter(new Query.FilterPredicate("recipient", FilterOperator.EQUAL, recipient))
            .addSort("timestamp", SortDirection.DESCENDING);
    PreparedQuery results = datastore.prepare(query);

    for (Entity entity : results.asIterable()) {

        readMessage(entity, messages, recipient);

    }

    return messages;
  }

  /**
   * Fetches the messages for all users.
   *
   * @return a list of messages posted by all users, or empty list if there are no messages. List
   * is sorted by time descending.
   */
  public List<Message> getAllMessages(){
    List<Message> messages = new ArrayList<>();

    Query query = new Query("Message")
            .addSort("timestamp", SortDirection.DESCENDING);
    PreparedQuery results = datastore.prepare(query);

    for (Entity entity : results.asIterable()) {

        String user = (String) entity.getProperty("user");
        readMessage(entity, messages, user);

    }

    return messages;
  }

  /** Returns the total number of messages for all users. */
  public int getTotalMessageCount(){
    Query query = new Query("Message");
    PreparedQuery results = datastore.prepare(query);
    return results.countEntities(FetchOptions.Builder.withLimit(1000));
  }

  /* Helper function that encapsulates the redundant segments of the getMessages
   and getAllMessages functions; reads Messages.
   */
  public void readMessage(Entity entity, List<Message> messages, String user) {
    try {
      String idString = entity.getKey().getName();
      UUID id = UUID.fromString(idString);
      String text = (String) entity.getProperty("text");
      long timestamp = (long) entity.getProperty("timestamp");
      String recipient = (String) entity.getProperty("recipient");
      float sentimentScore = (entity.getProperty("sentimentScore") != null) ?
                ((Double) entity.getProperty("sentimentScore")).floatValue() : 0;
      String imageUrl = (String) entity.getProperty("imageUrl");
      String imageLabels = (String) entity.getProperty("imageLabels");
      Message message = new Message(id, user, text, timestamp, recipient,sentimentScore, imageUrl, imageLabels);
      messages.add(message);
    } catch (Exception e) {
      System.err.println("Error reading message.");
      System.err.println(entity.toString());
      e.printStackTrace();
    }
  }
  /** Stores the User in Datastore. */
 public void storeUser(User user) {
  Entity userEntity = new Entity("User", user.getEmail());
  userEntity.setProperty("email", user.getEmail());
  userEntity.setProperty("aboutMe", user.getAboutMe());
  datastore.put(userEntity);
 }

 /**
  * Returns the User owned by the email address, or
  * null if no matching User was found.
  */
 public User getUser(String email) {

  Query query = new Query("User")
    .setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, email));
  PreparedQuery results = datastore.prepare(query);
  Entity userEntity = results.asSingleEntity();
  if(userEntity == null) {
   return null;
  }

  String aboutMe = (String) userEntity.getProperty("aboutMe");
  User user = new User(email, aboutMe);

  return user;
 }

  public List<UserMarker> getMarkers() {
    List<UserMarker> markers = new ArrayList<>();

    Query query = new Query("UserMarker");
    PreparedQuery results = datastore.prepare(query);

    for (Entity entity : results.asIterable()) {
        try {
          double lat = (double) entity.getProperty("lat");
          double lng = (double) entity.getProperty("lng");
          String content = (String) entity.getProperty("content");

          UserMarker marker = new UserMarker(lat, lng, content);
          markers.add(marker);
        } catch (Exception e) {
          System.err.println("Error reading marker.");
          System.err.println(entity.toString());
          e.printStackTrace();
        }
    }
    return markers;
  }


  public void storeMarker(UserMarker marker) {
    Entity markerEntity = new Entity("UserMarker");
    markerEntity.setProperty("lat", marker.getLat());
    markerEntity.setProperty("lng", marker.getLng());
    markerEntity.setProperty("content", marker.getContent());
    datastore.put(markerEntity);
  }
}
