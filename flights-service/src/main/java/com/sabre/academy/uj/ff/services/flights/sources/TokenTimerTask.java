package com.sabre.academy.uj.ff.services.flights.sources;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;

import java.util.TimerTask;
/**
 * Singleton class to hold Sabre Token Access and update it after given time
 */
public class TokenTimerTask extends TimerTask{
    private static String accessToken;
    private static TokenTimerTask instance = null;
    /**
     * This method run a timer Task to to update Token Access before expire
     */
    private TokenTimerTask(){}

    /**
     * This method provides Singleton Instance
     */
    public static TokenTimerTask getInstance(){
        if(instance == null)
            instance = new TokenTimerTask();
        return instance;
    }
    private HttpResponse<JsonNode> getToken() throws UnirestException {
         return Unirest.post("https://api-crt.cert.havail.sabre.com/v2/auth/token")
                .header("Authorization", "Basic VmpFNk9YTnZkbkkyYURaNWFHZHhZVEZqZGpwRVJWWkRSVTVVUlZJNlJWaFU6YjFKeVVEWnpPRkE9")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("grant_type", "client_credentials")
                .header("Cache-Control", "no-cache")
                .header("Postman-Token", "2d760806-aef2-060a-668b-a6d5e0635645")
                .asJson();

    }

    /**
     * Getter to access token
     */
    public static String getAccessToken() {
        return accessToken;
    }

    @Override
    /**
     * This method run a timer Task to to update Token Access before expire
     */
    public void run() {
        try{
            accessToken = getToken().getBody().getObject().getString("access_token");
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("TO MÓJ TOKEN: " +  accessToken);
    }
}
