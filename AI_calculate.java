package com.example.emad.splashscreen;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.logging.Handler;

public class AI_calculate extends AppCompatActivity {
    squad_calculation SC = new squad_calculation();
    Player p = new Player();

    AI_team AI=new AI_team();

    ArrayList<Player>squad_team1=new ArrayList<>();
    ArrayList<Player>squad_team2=new ArrayList<>();


    ArrayList<ArrayList<Player>> Initialized_array = new ArrayList<>();
    ArrayList<Player> AI_TEAM = new ArrayList<>();

    String MatchName;
    String Id;
    String UserName;

    private TransparentProgressDialog pd;
    private Handler h;
    private Runnable r;

    private RecyclerView recyclerView;
    private playerAdapter adapter;
    private  RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_calculate);

        Toast.makeText(this,"YAYYYYY I AM IN AI TEAM!! ",Toast.LENGTH_LONG);

        Intent intent = getIntent();
        MatchName = intent.getStringExtra("Teams");
        Id = intent.getStringExtra("Tid");

        UserName = intent.getStringExtra("User");
        mLayoutManager = new LinearLayoutManager(this);



        JSONTASK b = new JSONTASK();  //CHANGE THAT I EXECUTED ##CHHHHHH
        b.execute("http://cricapi.com/api/fantasySummary?unique_id=" + Id + "&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2");


    }



    public class JSONTASK extends AsyncTask<String, ArrayList<String>, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            //          doneSignal.countDown();
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

        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            String response_ = s;
            try {
                System.out.println(response_ + "      888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
                Initialized_array = SC.parsingNames_AI(response_, p);



            } catch (JSONException e) {
                e.printStackTrace();
            }



            squad_team1 = Initialized_array.get(0);
            squad_team2 = Initialized_array.get(1);

            //CHECK HERE FOR THE AI-TEAM!!!

            AI_TEAM= AI.createAiOdiTeam(squad_team1,squad_team2);


            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

            adapter = new playerAdapter(this, AI_TEAM);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);




        }


        }

    }



