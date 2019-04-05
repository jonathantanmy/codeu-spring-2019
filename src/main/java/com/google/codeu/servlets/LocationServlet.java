package com.google.codeu.servlets;
import com.google.codeu.data.Datastore;
import com.google.codeu.data.Location;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/locationServlet")
public class LocationServlet extends HttpServlet {

    private Datastore datastore;


    @Override
    public void init() {
        datastore = new Datastore();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // read form fields
        String name = request.getParameter("name-input");
        String description = request.getParameter("description-input");

        Location location = new Location(name, description, "", "");
        datastore.storeLocation(location);

    }

}