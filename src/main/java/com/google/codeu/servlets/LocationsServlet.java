package com.google.codeu.servlets;
import com.google.codeu.data.Datastore;
import com.google.codeu.data.Location;

import java.io.IOException;
import com.google.gson.Gson;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/locations")
public class LocationsServlet extends HttpServlet {

    private Datastore datastore;


    @Override
    public void init() {
        datastore = new Datastore();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        List<Location> locations = datastore.getLocations();
        Gson gson = new Gson();
        String json = gson.toJson(locations);
        response.getOutputStream().println(json);
    }

}
