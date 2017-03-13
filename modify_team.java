package com.example.emad.splashscreen;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class modify_team extends AppCompatActivity {
   /* MyDBHandler dbHandler;
    String team1, team2;
    String UserName = "";
    public TextView a;
    ArrayList<String> Team12 = new ArrayList<>();
    ArrayList<String> TeamId = new ArrayList<>();
    Player p = new Player();
    String[] data = {"0"};
    String[] data11 = {"0"};
    Player[] squad_team1 = new Player[70];
    Player[] squad_team2 = new Player[70];
    String Id = "";

    Player[] P1;
*/
   public String[] selectedTeam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_team);
/*
        dbHandler = new MyDBHandler(this);
        dbHandler.createTeam();
        Intent intent = getIntent();

        UserName = intent.getStringExtra("User");

        ArrayList<String> Mat = new ArrayList<>();
        Mat = dbHandler.getMatches();
*/


        Bundle b=this.getIntent().getExtras();
        selectedTeam=b.getStringArray("key");

        final ListView listV = (ListView)findViewById(R.id.team);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(modify_team.this, R.layout.activity_list_box,selectedTeam);

        listV.setAdapter(adapter);
        listV.setItemsCanFocus(true);



/*
        Id = dbHandler.getId();//intent.getStringExtra("Tid");
        P1 = new Player[70];
        JSONTask b=new JSONTask();
        if (Mat.size() <= 0)
        {
            android.widget.Toast.makeText(getBaseContext(), "You haven't Selected Any Team Yet ", android.widget.Toast.LENGTH_LONG).show();
        }
        else {
            b.execute("http://cricapi.com/api/fantasySummary?unique_id=" + Id + "&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2");
        }
        //b.execute("http://cricapi.com/api/fantasySummary?unique_id=1050227&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2");



        final ListView listV = (ListView)findViewById(R.id.team);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(modify_team.this, R.layout.activity_list_box,);




    }

    class JSONTask extends AsyncTask<String,String,String>
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

*/
    }
}



