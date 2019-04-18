package com.google.codeu.servlets;

import com.google.codeu.data.Datastore;
import com.google.codeu.data.Review;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        /** Get the Locations from the Datastore */

        // create a new review based on the form inputs
        String title = request.getParameter("title");
        String body = request.getParameter("body");
        Review review = new Review(title, body);
        datastore.storeReview(review);


    }

}
