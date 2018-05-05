package com.sabre.academy.uj.ff.services.flights.sources;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * Singleton class to communicate with Sabre Geo Code API
 */
public class GeoCodeCalculator {
    private static HashMap<HashSet<String>, Double> distances;
    private  static GeoCodeCalculator instance = null;
    private double latitude;
    private double longitude;

    /**
     * This method returns the instance of GeoCodeCalculator class.
     */
    public static  GeoCodeCalculator getInstance(){
        if(instance == null) {
            new Timer().scheduleAtFixedRate(TokenTimerTask.getInstance(), 0,604000 * 1000);   //w milisekundach
            instance = new GeoCodeCalculator();
            distances = new HashMap<>();
        }
        return instance;
    }

    /**
     * This method retururns coordinates
     * @return true if result is correct, else false
     */
    private boolean checkCoordinates(String id){
        HttpResponse<JsonNode> responseGeoCode;
        try {
            responseGeoCode = Unirest.post("https://api.test.sabre.com/v1/lists/utilities/geocode/locations")
                    .header("Authorization", "bearer " + TokenTimerTask.getAccessToken())
                    .header("Content-Type", "application/json")
                    .header("Cache-Control", "no-cache")
                    .header("Postman-Token", "96ec33e8-5f3d-26f8-23b9-3c57ed23aa34")
                    .body("[{\n    \"GeoCodeRQ\": {\n        \"PlaceById\": {\n            \"Id\": \"" + id + "\",\n            \"BrowseCategory\": {\n                \"name\": \"AIR\"\n            }\n        }\n    }\n}]")
                    .asJson();
        } catch (UnirestException e) {
            //System.out.println("PROBLEM1");
           return false;
        }

        try {
            JSONObject js = responseGeoCode.getBody().getObject().getJSONArray("Results")
                    .getJSONObject(0)
                    .getJSONObject("GeoCodeRS")
                    .getJSONArray("Place")
                    .getJSONObject(0);
            this.latitude = (double)js.get("latitude");
            this.longitude = (double)js.get("longitude");
        } catch (JSONException e) {
            //System.out.println("PROBLEM2");
            return false;
        }
        return true;
    }
    /**
     * This method calculate the distance between two airports from coordinates
     * @return number of miles between two airports
     */
    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return 3959.87 * c;
    }
    /**
     * This method check if distance between airports is already calculated, if it isnt run calculation
     * @return proper number of miles or -1.0 if airports codes are wrong
     */
    public double mileCalculatorBrain(String departureAirportCode, String arrivalAirportCode){
        double lat1;
        double lat2;
        double lon1;
        double lon2;
        Double result = distances.get(new HashSet<>(Arrays.asList(departureAirportCode, arrivalAirportCode)));
        //System.out.println("SPRAWDZAM CZY JUZ JEST LICZBA MIL JUZ: " + result);
        if(result == null){
            if(checkCoordinates(departureAirportCode)){
                lat1 = this.latitude;
                lon1 = this.longitude;
                if(checkCoordinates(arrivalAirportCode)){
                    lat2 = this.latitude;
                    lon2 = this.longitude;
                    result = haversine(lat1, lon1, lat2, lon2);
                    //System.out.println("DANE LOTNISK: lat1=" + lat1 +" lon1= " + lon1 +" lat2=" + lat2 + " lon2=" + lon2);
                    distances.putIfAbsent(new HashSet<>(Arrays.asList(departureAirportCode, arrivalAirportCode)), result);
                    //System.out.println("Hashmapa: " + distances.toString());
                }
                else
                    result = -1.0;
            }
            else
                result = -1.0;
        }
        //System.out.println("TYLE MIL WYLICZYLEM: " + result);
        return result;
    }
}
