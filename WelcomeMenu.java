package com.example.emad.splashscreen;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import org.json.*;

class HttpCon {
    public String responseBody;
    protected String keyTest() {
        HttpClient httpClient = new DefaultHttpClient();
        BasicHttpContext localContext = new BasicHttpContext();
        ///api/cricketScore?unique_id=1030217
        HttpGet httpGet = new HttpGet("http://cricapi.com/api/cricket");
        String text = null;
        try {
            HttpResponse response = httpClient.execute(httpGet, localContext);


            HttpEntity entity = response.getEntity();

            responseBody = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
            //text = getASCIIContentFromEntity(entity);


        } catch (Exception e) {
            return e.getLocalizedMessage();
        }


        return responseBody;
    }
    /*public static void main(String args[]) throws UnsupportedEncodingException, JSONException
    {
        HttpCon t= new HttpCon();
        String response_;

        response_=t.keyTest();
        final JSONObject obj = new JSONObject(response_);
        final JSONArray geodata = obj.getJSONArray("data");
        final int n = geodata.length();
        for (int i = 0; i < n; ++i) {
            final JSONObject person = geodata.getJSONObject(i);
            System.out.println(person.getString("title"));

        }

        //System.out.println(response_);
    }*/
}


public class WelcomeMenu extends ActionBarActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    int counter = 0;
    ArrayList<String> Teams = new ArrayList<>();//creating new generic arraylist
    ArrayList<String> TeamId = new ArrayList<>();//creating new generic arraylist
    String UserName = "";

   /* @BindView(R.id.button) android.support.v7.widget.AppCompatButton _Button;
    @BindView(R.id.button2) android.support.v7.widget.AppCompatButton _Button1;
    @BindView(R.id.button3) android.support.v7.widget.AppCompatButton _Button2; */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Star's Messages will be Displayed Here", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();

        UserName = getIntent().getStringExtra("User");

        ButterKnife.bind(this);

        android.widget.LinearLayout b = (android.widget.LinearLayout) findViewById(R.id.Linear);
        new JSONTask().execute("http://cricapi.com/api/cricket?&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2");

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
            final JSONObject obj;
            try {
                obj = new JSONObject(s);
                final JSONArray geodata = obj.getJSONArray("data");
                final int n = geodata.length();
                Teams.add("Abdul v Rida ");
                TeamId.add("1075989");
                for (int i = 0; i < n; ++i) {
                    final JSONObject person = geodata.getJSONObject(i);
                    System.out.println(person.getString("title"));


                    Teams.add(person.getString("title"));
                    TeamId.add(person.getString("unique_id"));
                    /*if (person.getString("title").contains("Pakistan") || person.getString("title").contains("England") || person.getString("title").contains("Australia") || person.getString("title").contains("South Africa") || person.getString("title").contains("New Zealand") || person.getString("title").contains("India") || person.getString("title").contains("Sri Lanka") || person.getString("title").contains("Bangladesh") || person.getString("title").contains("West Indies") || person.getString("title").contains("Afghanistan"))
                    {
                        Teams.add(person.getString("title"));
                        TeamId.add(person.getString("unique_id"));

                    }*/

                    //output.append(person.getString("title") + "\n");
                }
                //Teams.add("Pak V Win");
            } catch (JSONException e) {


                e.printStackTrace();
            }
           //s Teams.add("Pakistan V West Indies");
            String[] data = new String[Teams.size()];
            for (int i = 0;i< Teams.size();i++)
            {
                data[i] = Teams.get(i).toString();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(WelcomeMenu.this, R.layout.blayout,data);
            final ListView listV = (ListView)findViewById(R.id.ListView);
            listV.setAdapter(adapter);
            listV.setItemsCanFocus(true);
            listV.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                   // android.widget.Toast.makeText(getBaseContext(), "Item Clicked!", android.widget.Toast.LENGTH_LONG).show();

                  //  Intent intent = new Intent(WelcomeMenu.this,SelectTeam.class);//TeamPlayers.java
                    Intent intent = new Intent(WelcomeMenu.this,AI_or_selectTeam.class);
                    //android.support.v7.widget.AppCompatButton b = (android.support.v7.widget.AppCompatButton)findViewById(R.id.button);


                    TextView b = (TextView) view;
                    String Text = b.getText().toString();
                    String id2 = "";
                    for (int i=0;i<TeamId.size();i++)
                    {
                        if (Text.equals(Teams.get(i).toString()))
                        {
                            id2 = TeamId.get(i).toString();
                        }
                    }
                    intent.putExtra("User",UserName);
                    intent.putExtra("Teams", Text);
                    intent.putExtra("Tid",id2);
                    startActivity(intent);
                    finish();
                }
            });




        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

               // Intent i = new Intent(this, WelcomeMenu.class);
               // i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
               // startActivity(i);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.Logout) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.CreatedTeam) {
            // Handle the Leaderboard action
           // Intent intent = new Intent(this,CreatedTeam.class);
            //startActivity(intent);
            //finish();
        } else if (id == R.id.TeamId) {
            Intent intent = new Intent(this,ViewTeam.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.TeamUpdate) {
            Intent intent = new Intent(this,modify_team.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.AboutId) {

        }else if (id == R.id.Private) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
/*
    public void onClick(View v)
    {
        switch(v.getId()){
            case (R.id.button):

                Intent intent = new Intent(this,Team.class);
                android.support.v7.widget.AppCompatButton b = (android.support.v7.widget.AppCompatButton)findViewById(R.id.button);
                String Text = b.getText().toString();
                intent.putExtra("Teams", Text);
                startActivity(intent);
                finish();
                break;
            case (R.id.button2):
                Intent intent1 = new Intent(this,Team.class);
                android.support.v7.widget.AppCompatButton b1 = (android.support.v7.widget.AppCompatButton)findViewById(R.id.button2);
                String Text1 = b1.getText().toString();
                intent1.putExtra("team", Text1);
                startActivity(intent1);
                finish();
                break;
            case (R.id.button3):
                Intent intent2 = new Intent(this,Team.class);
                android.support.v7.widget.AppCompatButton b2 = (android.support.v7.widget.AppCompatButton)v;
                String Text2 = b2.getText().toString();
                intent2.putExtra("Teams", Text2);
                startActivity(intent2);
                finish();
                break;

        }

    }*/
}
