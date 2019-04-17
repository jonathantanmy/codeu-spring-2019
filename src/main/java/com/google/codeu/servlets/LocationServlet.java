package com.google.codeu.servlets;
import com.google.codeu.data.Datastore;
import com.google.codeu.data.Location;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.util.List;


@WebServlet("/location")
public class LocationServlet extends HttpServlet {

    private Datastore datastore;


    @Override
    public void init() {
        datastore = new Datastore();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // read form fields
        String name = request.getParameter("location-name");
        String description = request.getParameter("description");

        Location location = new Location(name, description, "default", "default");
        datastore.storeLocation(location);
        response.sendRedirect("/locationfeed.html");
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
