package com.google.codeu.servlets;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.io.IOException;
import java.util.Scanner;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Returns bubble tea data as a JSON array, e.g. [{"lat": 38.4404675, "lng": -122.7144313}]
 */
@WebServlet("/MapsServlet")
public class MapsServlet extends HttpServlet{

    JsonArray bubbleTeaArray;

    @Override
    public void init() {
        bubbleTeaArray = new JsonArray();
        Gson gson = new Gson();
        Scanner scanner = new Scanner (getServletContext().getResourceAsStream("/WEB-INF/MapsServlet.csv"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] cells = line.split("(?x)   " +
                    ",          " +   // Split on comma
                    "(?=        " +   // Followed by
                    "  (?:      " +   // Start a non-capture group
                    "    [^\"]* " +   // 0 or more non-quote characters
                    "    \"     " +   // 1 quote
                    "    [^\"]* " +   // 0 or more non-quote characters
                    "    \"     " +   // 1 quote
                    "  )*       " +   // 0 or more repetition of non-capture group (multiple of 2 quotes will be even)
                    "  [^\"]*   " +   // Finally 0 or more non-quotes
                    "  $        " +   // Till the end  (This is necessary, else every comma will satisfy the condition)
                    ")          "     // End look-ahead
            );

            String name = cells[5];
            String address = cells[1];
            double lat = Double.parseDouble(cells[3]);
            double lng = Double.parseDouble(cells[4]);

            System.out.println("name" + name);
            System.out.println("address" + address);
            System.out.println("lat" + lat);
            System.out.println("lng" + lng);
            System.out.println();

            bubbleTeaArray.add(gson.toJsonTree(new BubbleTeaLocation(
                    name, address, lat, lng)));
        }

        scanner.close();
    }

    @Override
    public void doGet(
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.getOutputStream().println(bubbleTeaArray.toString());
    }

    private static class BubbleTeaLocation {

        String name;
        String address;
        double lat;
        double lng;

        private BubbleTeaLocation(String name, String address, double lat, double lng) {
            this.name = name;
            this.address = address;
            this.lat = lat;
            this.lng = lng;
        }

    }


}
