package com.google.codeu.servlets;

import com.google.appengine.api.datastore.Entity;
import com.google.codeu.data.Datastore;
import com.google.codeu.data.Location;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/review")
public class ReviewServlet extends HttpServlet {

    private Datastore datastore;

    @Override
    public void init() {
        datastore = new Datastore();
    }



    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // location being reviewed
        Entity location = datastore.getLocation(""); // Argument to be changed upon creation of Location Feed
        Collection<Location.Review> reviews = (Collection<Location.Review>) location.getProperty("reviews");

        // create a new review based on the form inputs
        String title = request.getParameter("title");
        String body = request.getParameter("body");
        Location.Review review = new Location.Review(title, body);
        reviews.add(review);

        // Update the reviews of the location
        location.setProperty("reviews", reviews);

        // Print the updated list of reviews
        Collection<Location.Review> allReviews = (Collection<Location.Review>) location.getProperty("reviews");
        for (Location.Review rev: allReviews) {
            System.out.println(rev.title);
            System.out.println(rev.body);
        }
    }

}
