package com.example.emad.splashscreen;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by HP Pavilion 13 on 2/27/2017.
 */

public class squad_calculation {
    public static String responseBody;
    static Squad Sq;// INITIATING SQUAD PROJECT ----------------------


    //THIS IS NOT THE FUNCTION BEING CALLED!!
    protected String PlayerNames(String... params) {            //PLAYER NAMES!!

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();


        } catch (MalformedURLException e) {
            System.out.println("GAYA INTERNET1\n");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("GAYA INTERNET2\n");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("GAYA INTERNET3\n");
                e.printStackTrace();
            }
        }
        return null;
        /*
        HttpClient httpClient = new DefaultHttpClient();
        BasicHttpContext localContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet(
                // 1050229
                "http://cricapi.com/api/fantasySquad?unique_id=1050229&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2");
        String text = null;
        try {
            HttpResponse response = httpClient.execute(httpGet, localContext);

            HttpEntity entity = response.getEntity();

            responseBody = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
            // text = getASCIIContentFromEntity(entity);

        } catch (Exception e) {
            System.out.println("GAYA INTERNET\n");
            return e.getLocalizedMessage();
        }
        System.out.println("1...PlayerNames\n");
        return responseBody;
        */
    }

    public static ArrayList<ArrayList<Player>> parsingNames(String response_, Player p)              // PARSING NAMES
            throws JSONException, NullPointerException {
        String response2 = response_;
       // System.out.println(response2);

        // response_='{'+response_;
        final JSONObject obj1 = new JSONObject(response2);
       System.out.println(obj1);
       // System.out.println("ss");
        final JSONObject test = obj1.getJSONObject("data");

        final JSONArray geodata1 = test.getJSONArray("team"); // getting squad
        System.out.println(geodata1 + "\n\n\n");
        final JSONObject team1 = geodata1.getJSONObject(0);
        final JSONObject team2 = geodata1.getJSONObject(1);
        System.out.println( team1);
        System.out.println(team2);

        // Squad Sq= new Squad();//INITIATING SQUAD PROJECT
        // ----------------------
        ArrayList<ArrayList<Player>> the_return = new ArrayList<>();
        Sq = new Squad();
        the_return = Sq.get_squad(team1, team2);
        return the_return;


		/*
		 * final int n1 = geodata1.length(); for (int i = 0; i < n1; ++i) {
		 * final JSONObject person = geodata1.getJSONObject(i); final JSONArray
		 * names_ = person.getJSONArray("players"); // player // array final int
		 * n2 = names_.length(); for (int j = 0; j < n2; ++j) { final JSONObject
		 * person2 = names_.getJSONObject(j); p.name =
		 * person2.getString("name"); //System.out.println(p.name);// individual
		 * names //id = person2.getString("pid"); //response2_ = Stats(id);
		 * //parseStats(response2_, p);
		 *
		 * }
		 */
        // System.out.println("2...ParsingNames\n");
    }


    public static ArrayList<ArrayList<Player>> parsingNames_AI(String response_, Player p)              // PARSING NAMES
            throws JSONException, NullPointerException {

                String response2 = response_;
                // System.out.println(response2);

                // response_='{'+response_;
                final JSONObject obj1 = new JSONObject(response2);
                System.out.println(obj1);
                // System.out.println("ss");
                final JSONObject test = obj1.getJSONObject("data");

                final JSONArray geodata1 = test.getJSONArray("team"); // getting squad
                System.out.println(geodata1 + "\n\n\n");
                final JSONObject team1 = geodata1.getJSONObject(0);
                final JSONObject team2 = geodata1.getJSONObject(1);
                System.out.println(team1);
                System.out.println(team2);

                // Squad Sq= new Squad();//INITIATING SQUAD PROJECT
                // ----------------------
                ArrayList<ArrayList<Player>> the_return = new ArrayList<>();
                Sq = new Squad();
                the_return = Sq.get_AI_squad(team1, team2);
                return the_return;

 }

    public ArrayList<ArrayList<String>> get_formation(ArrayList<String> SP){

        ArrayList<String> OnlyBowlers = new ArrayList<>();
        ArrayList<String> OnlyAlrounder = new ArrayList<>();
        ArrayList<String> OnlyBatsman = new ArrayList<>();
        ArrayList<String> OnlyWicketKeeper = new ArrayList<>();

       ArrayList<String> SelectedBowlers=SP;

        for (int i = 0; i < SelectedBowlers.size(); i++) {
            if (SelectedBowlers.get(i).contains("Bowler") || SelectedBowlers.get(i).contains("bowling") || SelectedBowlers.get(i).contains("Bowling")) {
                OnlyBowlers.add(SelectedBowlers.get(i).toString());
            }

            if (SelectedBowlers.get(i).contains("allrounder") || SelectedBowlers.get(i).contains("Allrounder") || SelectedBowlers.get(i).contains("All-Rounder") || SelectedBowlers.get(i).contains("Bowling allrounder")) {
                OnlyAlrounder.add(SelectedBowlers.get(i).toString());
            }

            if (SelectedBowlers.get(i).contains("batsman") || SelectedBowlers.get(i).contains("Batsman")) {
                OnlyBatsman.add(SelectedBowlers.get(i).toString());
            }

            if (SelectedBowlers.get(i).contains("Wicketkeeper") || SelectedBowlers.get(i).contains("WicketKeeper") || SelectedBowlers.get(i).contains("Wicketkeeper batsman")) {
                OnlyWicketKeeper.add(SelectedBowlers.get(i).toString());
            }

        }
        ArrayList<ArrayList<String>> return_obj=new ArrayList<>();
        return_obj.add(OnlyBowlers);
        return_obj.add(OnlyAlrounder);
        return_obj.add(OnlyBatsman);
        return_obj.add(OnlyWicketKeeper);

        return return_obj;
    }




}
