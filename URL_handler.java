package com.example.emad.splashscreen;

import android.os.AsyncTask;

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
 * Created by HP Pavilion 13 on 3/3/2017.
 */

public class URL_handler extends AsyncTask<String,String,String> {
    @Override
    protected String doInBackground(String... params) {


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
            e.printStackTrace();
        } catch (IOException e) {
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
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);


    }

    protected ArrayList<ArrayList<Player>> Action_on_response(String response2_) {
        final JSONObject obj1;
        System.out.println("I AM IN Action on response-URL HANDLER AB!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!))))))))))))))0000000000");

        ArrayList<ArrayList<Player>> to_return=new ArrayList<>();
        String team1;
        String team2;
        ArrayList<Player> squad_team1 = new ArrayList<>();
        ArrayList<Player> squad_team2 = new ArrayList<>();
        try {
            obj1 = new JSONObject(response2_);

            final JSONObject test = obj1.getJSONObject("data");
            final JSONArray geodata1 = test.getJSONArray("team"); // getting squad
            System.out.println(geodata1 + "\n\n\n");
            final JSONObject team1_ = geodata1.getJSONObject(0);
            final JSONObject team2_ = geodata1.getJSONObject(1);
            team1 = team1_.getString("name");
            team2 = team2_.getString("name");
                    /*// Country names----------------
                    System.out.println(team1+"-------------\n");*/


            //Putting Players in Array---------------
            final JSONArray players1 = team1_.getJSONArray("players");
            final JSONArray players2 = team2_.getJSONArray("players");
            //System.out.println(players1);
            //System.out.println(players2);

            int n1 = players1.length();


            //PlayerType.add("All-Rounder");
            String id = "";
            for (int i = 0; i < n1; i++) {    //Team1 k players initiate horahe hen
                final JSONObject player = players1.getJSONObject(i);
                Player p = new Player();
                //p.ID = player.getString("pid");
                p.name = player.getString("name");
                squad_team1.add(p);


            }

            int n2 = players2.length();
            for (int i = 0; i < n2; i++) {    //Team2 k players initiate horahe hen
                final JSONObject player1 = players2.getJSONObject(i);
                Player p = new Player();
                //p.ID = player1.getString("pid");
                p.name = player1.getString("name");
                squad_team2.add(p);

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
        to_return.add(squad_team1);
        to_return.add(squad_team2);

        return to_return;

    }
}

