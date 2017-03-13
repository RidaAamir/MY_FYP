package com.example.emad.splashscreen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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

public class TeamPlayers extends AppCompatActivity {
    private TextView txtTemp;
    ArrayList<String> SelectedPlayers = new ArrayList<>();
    Player p= new Player();
    Player[] squad_team1 = new Player[40];
    Player[] squad_team2 = new Player[40];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_players);
        txtTemp =(TextView) findViewById(R.id.teamsquad);
        Intent intent = getIntent();
        String strTemp = intent.getStringExtra("Teams");
        String Tid = intent.getStringExtra("Tid");
        txtTemp.setText(strTemp);
        new JSONTask().execute("http://cricapi.com/api/fantasySquad?unique_id="+Tid+"&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2");

    }

    public class JSONTask extends AsyncTask<String,String,String>
    {


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

                while((line = reader.readLine()) != null)
                {
                    buffer.append(line);
                }
                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(connection != null) {
                    connection.disconnect();
                }
                try {
                    if(reader != null)
                    {
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
            String response2_ = s;

            for(int i=0;i<40;i++){
                squad_team1[i]=new Player();
                squad_team2[i]=new Player();
            }
            String team1,team2;
            // response_='{'+response_;
            final JSONObject obj1;
            try {
                obj1 = new JSONObject(response2_);
                final JSONArray geodata1 = obj1.getJSONArray("squad"); // getting squad
                System.out.println(geodata1+"\n\n\n");
                final JSONObject team1_=geodata1.getJSONObject(0);
                final JSONObject team2_=geodata1.getJSONObject(1);
                team1=team1_.getString("name");
                team2=team2_.getString("name");
                // Country names----------------
                System.out.println(team1+"-------------\n");
                System.out.println(team2 + "-------------\n");

                //Putting Players in Array---------------
                final JSONArray players1=team1_.getJSONArray("players");
                final JSONArray players2=team2_.getJSONArray("players");
                //System.out.println(players1);

                //System.out.println(players2);

                int n1=players1.length();

                ArrayList<String> Team11 = new ArrayList<>();
                ArrayList<String> Team22 = new ArrayList<>();
                for(int i=0;i<n1;i++) {    //Team1 k players initiate horahe hen
                    final JSONObject player = players1.getJSONObject(i);
                    squad_team1[i].setName(player.getString("name"));
                    p = squad_team1[i];
                    String id = player.getString("pid");
                }
                int n2=players2.length();
                for(int i=0;i<n2;i++) {    //Team2 k players initiate horahe hen
                    final JSONObject player1 = players2.getJSONObject(i);
                    squad_team2[i].setName(player1.getString("name"));
                    p = squad_team2[i];
                    String id1 = player1.getString("pid");
                }
                int n3 = squad_team1.length;
                for(int i=0;i<n1;i++){
                    // System.out.println(i+" --");
                    //data[i] = squad_team1[i].name;//.Display_Player();//har player ki info print hogii!!
                    Team11.add(squad_team1[i].name);

                }

                for(int i=0;i<n2;i++){
                    // System.out.println(i+" --");
                    //data[i] = squad_team1[i].name;//.Display_Player();//har player ki info print hogii!!
                    Team22.add(squad_team2[i].name);

                }
                String[] data = new String[Team11.size()];
                String[] data2=  new String[Team22.size()];

                for (int i=0;i< Team11.size();i++)
                {
                    data[i] = Team11.get(i).toString();
                }

                for (int i=0;i< Team22.size();i++)
                {
                    data2[i] = Team22.get(i).toString();
                }
/*
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(TeamPlayers.this, R.layout.blayout,data);
                final ListView listV = (ListView)findViewById(R.id.Players);
                listV.setAdapter(adapter);
                listV.setItemsCanFocus(true);
                listV.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        String selectedplayer = ((TextView)view).getText().toString();

                        if (SelectedPlayers.contains(selectedplayer))
                        {
                            SelectedPlayers.remove(selectedplayer);
                        }
                        else
                        {
                            listV.setItemChecked(position,true);
                            SelectedPlayers.add(selectedplayer);
                        }
                    }
                });
*/
               /* ArrayAdapter<String> adapter1 = new ArrayAdapter<>(TeamPlayers.this, R.layout.blayout,data2);
                final ListView listV2 = (ListView) findViewById(R.id.Team2Players);
                listV2.setAdapter(adapter1);
                Log.d("2nd Adapter","\n");
                listV2.setItemsCanFocus(true);
                listV2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        String selectedplayer = ((TextView)view).getText().toString();

                        if (SelectedPlayers.contains(selectedplayer))
                        {
                            SelectedPlayers.remove(selectedplayer);
                        }
                        else
                        {
                            listV2.setItemChecked(position,true);
                            SelectedPlayers.add(selectedplayer);
                        }
                    }
                });


*/


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    public void ShowFormation(View view)
    {
        String Bowlers = "";
        for (String Bowler:SelectedPlayers)
        {
            Bowlers += "-"+Bowler + "\n";
        }
        android.widget.Toast.makeText(getBaseContext(), "You selected this formation: " + Bowlers, android.widget.Toast.LENGTH_LONG).show();
    }


    public void SelectTeam(View view)
    {
        Intent intent = new Intent(this,WelcomeMenu.class);
        startActivity(intent);
        finish();
    }
}
