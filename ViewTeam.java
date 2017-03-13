package com.example.emad.splashscreen;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
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
import java.util.concurrent.ExecutionException;

public class ViewTeam extends AppCompatActivity {
    MyDBHandler dbHandler;
    String team1, team2;
    String UserName = "";
    public TextView a;
    ArrayList<String> Team12 = new ArrayList<>();
    ArrayList<String> TeamId = new ArrayList<>();
    Player p = new Player();
    String[] data = {"0"};
    String[] data11 = {"0"};
    ArrayList<Player> squad_team1 = new ArrayList<>();
    ArrayList<Player> squad_team2 = new ArrayList<>();
    String Id = "";

    ArrayList<Player> P1=new ArrayList<>();
    URL_handler UR = new URL_handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_team);
        dbHandler = new MyDBHandler(this);
        dbHandler.createTeam();
        Intent intent = getIntent();
        /*MatchName = intent.getStringExtra("Teams");
        String Id = intent.getStringExtra("Tid");*/
        UserName = intent.getStringExtra("User");

        ArrayList<String> Mat = new ArrayList<>();
        Mat = dbHandler.getMatches();

        String reciever;

        Id = dbHandler.getId();//intent.getStringExtra("Tid");


        if (Mat.size() <= 0) {
            android.widget.Toast.makeText(getBaseContext(), "You haven't Selected Any Team Yet ", android.widget.Toast.LENGTH_LONG).show();
        } else {
            try {
                reciever = UR.execute("http://cricapi.com/api/fantasySummary?unique_id=" + Id + "&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2").get();










            }
            //ENDING TRY
            catch (ExecutionException e) {
                e.printStackTrace();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //b.execute("http://cricapi.com/api/fantasySummary?unique_id=1050227&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2");

    }


    public void get_points() {


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

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                String response2_ = s;
                ArrayList<ArrayList<Player>> from_URLHANDLER=new ArrayList<>();
                final JSONObject obj1;
                try {
                    obj1 = new JSONObject(response2_);
                System.out.println("I AM IN VIEW TEAM AB!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!))))))))))))))0000000000");

                final JSONObject test = obj1.getJSONObject("data");
                final JSONArray geodata1 = test.getJSONArray("team"); // getting squad
                System.out.println(geodata1 + "\n\n\n");
                final JSONObject team1_ = geodata1.getJSONObject(0);
                final JSONObject team2_ = geodata1.getJSONObject(1);
                team1 = team1_.getString("name");
                team2 = team2_.getString("name");
                }
                catch (JSONException e1) {
                    e1.printStackTrace();
                }

                from_URLHANDLER=UR.Action_on_response(response2_);
                squad_team1=from_URLHANDLER.get(0);
                squad_team2=from_URLHANDLER.get(1);

                    int n1 = squad_team1.size();

                    for(int i=0;i<n1;i++)
                    {
                        Team12.add(squad_team1.get(i).name);

                    }

                int n2 = squad_team2.size();
                    for(int i=0;i<n2;i++){
                        // System.out.println(i+" --");
                        //data[i] = squad_team1[i].name;//.Display_Player();//har player ki info print hogii!!
                        Team12.add(squad_team2.get(i).name);

                    }


                    //FantasyScore!
                final JSONObject obj2;
                try {
                    obj2 = new JSONObject(s);

                final JSONObject data = obj2.getJSONObject("data");

                    final JSONArray geodata3 = data.getJSONArray("batting"); // getting


                    final int n4 = geodata3.length();
                    for (int i = 0; i < n4; i++) {
                        final JSONObject person21 = geodata3.getJSONObject(i);
                        //System.out.println(i+" --i-->"+person);
                        String team_name21=person21.getString("title");
                        int r=i;
                        final JSONArray names_ = person21.getJSONArray("scores"); // player
                        System.out.println("####     ----------------------             "+team_name21);
                        Boolean inning_Check;
                        if (team_name21.contains(team1))
                        {  inning_Check = false;}
                        else
                        {inning_Check = true;}
                        /*=Sq.CheckTeam(team_name21);// INNING CHECK HORAHI HE K KON SI TEAM HE
                        *///

                        //System.out.println("\n---------------"+i+" INNINGS!! --------\n  ");													// array

                        if(inning_Check==true){	// SQUAD GET HORAHA HE
                            P1=squad_team2;
                            //Sq.Display_Squad(P1);
                        }

                        else{

                            P1=squad_team1;
                            //Sq.Display_Squad(P1);
                        }
                        final int n5 = names_.length();
                        for (int j = 0; j < n5; j++) {
                            final JSONObject person22 = names_.getJSONObject(j);
                            //System.out.println(j+" ->>  "+person2);
                            //CHeck for batsman "EXTRAS" "TOTAL"
                            String batsman_NAME=person22.getString("batsman");
                            if(batsman_NAME.equals("Total")){
                                String runs = person22.getString("R");

                                //System.out.println("Total Score " + runs);// TOTAL SCORE
                            }
                            else if(batsman_NAME.equals("Extras")){
                                String runs = person22.getString("R");
                                //System.out.println("Extras: " + runs);// TOTAL EXTRAS IN THE INNING
                            }
                            else{
                                batsman_NAME=batsman_NAME.substring(1);
                                //System.out.println(j+"--> "+batsman_NAME);
                                Player P=new Player();
                                int k;
                                for(k=0;k<70;k++){

                                    P= P1.get(k); //PLAYERS K NAAM COMPARE K RAHA HE
                                    if(batsman_NAME.contains("†")) {
                                        //System.out.println("wiccket keeper");
                                        int index= batsman_NAME.indexOf('†');
                                        batsman_NAME=batsman_NAME.replace('†',' ');
                                        batsman_NAME=batsman_NAME.trim();
                                    }
                                    if(batsman_NAME.contains("*")) {
                                        //System.out.println("captain");
                                        int index= batsman_NAME.indexOf('*');
                                        batsman_NAME=batsman_NAME.replace('*',' ');
                                        batsman_NAME=batsman_NAME.trim();
                                    }
                                    if(P.name.contains(batsman_NAME)){
                                        break;
                                    }

                                    else{	//P=P1[3];
                                        //break;
                                    }

                                }

                                String runs = person22.getString("R");
                                P.runs=Integer.parseInt(runs); //PARTICULAR PLAYER K RUNS WAGERA
                                //System.out.println("Runs " + runs);// individual runs
                                //System.out.println("New Runs "+ P.runs);
                                String balls = person22.getString("B");

                                P.balls=Integer.parseInt(balls);


                                //System.out.println("\n");
                                String SR = person22.getString("SR");
                                if(SR.equals("-")){
                                    P.SR=70.0;
                                    //PARTICULAR PLAYER Ka SR
                                }
                                else {
                                    P.SR=Double.parseDouble(SR);
                                }
                                //System.out.println("SR " + SR);
                                //System.out.println("\n");
                                P1.set(k, P);
                                //System.out.println("economy " + eco);
                            }
                        }
                        if (inning_Check == true)
                        {
                            squad_team2 = P1;
                        }
                        else
                        {
                            squad_team1 = P1;
                        }
                       // Sq.set_squad(P1, inning_Check); // UPDATES SQUAD WITH RUNS OF THE EACH PLAYER
                    }

                    //Bowling!

                    final JSONArray geodata4 = data.getJSONArray("bowling"); // getting

                    final int n6 = geodata4.length();
                    for(int i=0;i<n6;i++){
                        final JSONObject bowl1= geodata4.getJSONObject(i);

                        //System.out.println(bowl1);
                        String team_name22=bowl1.getString("title");
//					System.out.println("####                  "+team_name);

                       // Boolean inning_Check=Sq.CheckTeam(team_name);// INNING CHECK HORAHI HE K KON SI TEAM HE

                        Boolean inning_Check;
                        if (team_name22.contains(team1))
                        {  inning_Check = false;}
                        else
                        {inning_Check = true;}
                        //

                        //				System.out.println("\n---------------"+i+" INNINGS!! --------\n  ");													// array

                        if(inning_Check==true){	// SQUAD GET HORAHA HE
                            P1=squad_team1;
                            //Sq.Display_Squad(P1);
                        }

                        else{

                            P1=squad_team2;
                            //Sq.Display_Squad(P1);
                        }
                        final JSONArray inning=bowl1.getJSONArray("scores");


                        //System.out.println("\n---------------"+i+" INNINGS!! --------\n  ");													// array
                        final int inning_size = inning.length();
                        for (int j = 0; j < inning_size; ++j) {
                            final JSONObject bowler = inning.getJSONObject(j);
                            //System.out.println(j+" ->>  "+bowler);
                            String bowler_NAME=bowler.getString("bowler");
                            bowler_NAME=bowler_NAME.substring(1);
                            //System.out.println(j+"--> "+bowler_NAME);
                            Player P=new Player();
                            int k;
                            //System.out.println(bowler_NAME);
                            for(k=0;k<70;k++){

                                P= P1.get(k); //PLAYERS K NAAM COMPARE K RAHA HE
                                if(bowler_NAME.contains("†")) {
                                    //System.out.println("keeper");
                                    bowler_NAME=bowler_NAME.replace('†',' ');
                                    bowler_NAME=bowler_NAME.trim();
                                }
                                if(bowler_NAME.contains("*")) {
                                    //System.out.println("captain");
                                    int index= bowler_NAME.indexOf('*');
                                    bowler_NAME=bowler_NAME.replace('*',' ');
                                    bowler_NAME=bowler_NAME.trim();
                                }
                                //	System.out.println(bowler_NAME);
                                if(P.name.contains(bowler_NAME)){
                                    //System.out.println(k+"--> "+P.name);
                                    break;
                                }

                                else{	//P=P1[3];
                                    //break;
                                }

                            }
                            //System.out.println(j+"--> "+bowler_NAME);

                            //System.out.println("Runs " + runs);// individual runs
                            String wickets = bowler.getString("W");
                            P.wickets=Integer.parseInt(wickets);

                            //p[j].wickets=p[j].wickets+Integer.parseInt(wickets);
                            //System.out.println("Wickets " + wickets);//individual wickets
                            String Econ=bowler.getString("Econ");
                            P.economy=Double.parseDouble(Econ);
                            String overs = bowler.getString("O");
                            //	System.out.println(overs);
                            P.overs_=Double.parseDouble(overs);
                            String maidens = bowler.getString("M");
                            //System.out.println(overs);
                            P.maidens_=Integer.parseInt(maidens);
                            //	System.out.println("Economy " + Econ);//individual economy
                            P1.set(k, P);
                            System.out.println("\n");


                        }
                        //inning_Check = (!inning_Check);
                        if ((!inning_Check) == true)
                        {
                            squad_team2 = P1;
                        }
                        else
                        {
                            squad_team1 = P1;
                        }
                        //Sq.set_squad(P1, !inning_Check);
                    }


                    //Fielding:::!!!


                    final JSONArray geodata8 = data.getJSONArray("fielding"); // getting
                    final int n7 = geodata8.length();
                    for(int i=0;i<n7;i++){
                        final JSONObject field1= geodata8.getJSONObject(i);
                        //	System.out.println(field1+"\n");
                        final JSONArray inning=field1.getJSONArray("scores");
                        String team_name23=field1.getString("title");
                        //	System.out.println("####                  "+team_name);
                       // Boolean inning_Check=Sq.CheckTeam(team_name);// INNING CHECK HORAHI HE K KON SI TEAM HE
                        Boolean inning_Check;
                        if (team_name23.contains(team1))
                        {  inning_Check = false;}
                        else
                        {inning_Check = true;}

                        //

                        //System.out.println("\n---------------"+i+" INNINGS!! --------\n  ");													// array

                        if(inning_Check!=true){	// SQUAD GET HORAHA HE
                            P1=squad_team2;//Sq.return_squad(true);
                            //Sq.Display_Squad(P1);
                        }

                        else{

                            P1=squad_team1;//Sq.return_squad(false);
                            //Sq.Display_Squad(P1);
                        }
                        //System.out.println("\n---------------"+i+" INNINGS!! --------\n  ");													// array
                        final int inning_size = inning.length();

                        for (int j = 0; j < inning_size; ++j) {
                            final JSONObject fielder = inning.getJSONObject(j);

                            //System.out.println(j+" ->>  "+fielder);
                            String fielder_NAME=fielder.getString("name");
                            fielder_NAME=fielder_NAME.substring(1);
                            //System.out.println(j+"--> "+fielder_NAME);
                            Player P=new Player();
                            int k;
                            for(k=0;k<70;k++){

                                P= P1.get(k); //PLAYERS K NAAM COMPARE K RAHA HE
                                if(fielder_NAME.contains("†")) {
                                    //System.out.println("keeper");
                                    fielder_NAME=fielder_NAME.replace('†',' ');
                                    fielder_NAME=fielder_NAME.trim();
                                }
                                if(fielder_NAME.contains("*")) {
                                    //System.out.println("captain");
                                    int index= fielder_NAME.indexOf('*');
                                    fielder_NAME=fielder_NAME.replace('*',' ');
                                    fielder_NAME=fielder_NAME.trim();
                                }
                                if(P.name.contains(fielder_NAME)){
                                    //System.out.println(k+"--> "+P.name);
                                    break;
                                }

                                else{	//P=P1[3];
                                    //break;
                                }

                            }

                            //	System.out.println(j+" ->>  "+fielder_NAME);
                            int catch_=fielder.getInt("catch");
                            //System.out.println("catches "+catch_);
                            P.catches=catch_;
                            int stumped=fielder.getInt("stumped");
                            //System.out.println("catches "+catch_);
                            P.stumping=stumped;

                            P1.set(k, P);
                        }

                        //Sq.set_squad(P1, !inning_Check);
                        if ((!inning_Check) == true)
                        {
                            squad_team2 = P1;
                        }
                        else
                        {
                            squad_team1 = P1;
                        }
                        //calculateOdiPoints(P1);
                        calculatet20Points(P1);
                    }
                    ArrayList <String> Points = new ArrayList<>();


                    ArrayList<String> Teams = new ArrayList<>();
                    Teams = dbHandler.getTeamPlayers(UserName);
                    for (int i=0;i<Teams.size();i++)
                    {
                            for (int j=0;j<35;j++)
                            {
                                if (Teams.get(i).contains(P1.get(j).name))
                                {
                                    Points.add(Integer.toString(P1.get(j).points));
                                    break;
                                }
                            }

                    }

                    final ListView listV2 = (ListView)findViewById(R.id.Points);
                    final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(ViewTeam.this, R.layout.blayout,Points);
                    adapter2.notifyDataSetChanged();
                    //final ListView listV = (ListView)findViewById(R.id.TeamView);
                    //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewTeam.this, R.layout.blayout,Teams);
                    //adapter.notifyDataSetChanged();
                    ArrayList<String> Matches = new ArrayList<>();
                    Matches = dbHandler.getMatches();
                    final ListView listV1 = (ListView)findViewById(R.id.MatchView);
                    final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(ViewTeam.this, R.layout.blayout,Matches);
                    adapter1.notifyDataSetChanged();
                    final android.app.ProgressDialog progressDialog = new android.app.ProgressDialog(ViewTeam.this);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Getting Team Players... Sabr Kijye");
                    progressDialog.show();


                    // TODO: Implement your own authentication logic here.

                    //Display Authentication Dialog for about 60 seconds
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run()
                                {

                                    //adapter.notifyDataSetChanged();
                                    progressDialog.dismiss();
                                }
                            }, 60000);

                    //listV.setAdapter(adapter);
                    //listV.setItemsCanFocus(true);

                    listV1.setAdapter(adapter1);
                    listV1.setItemsCanFocus(true);

                    listV2.setAdapter(adapter2);
                    listV2.setItemsCanFocus(true);




                  /*  data = new String[Team12.size()];
                    data11 = new String[Team12.size()];

                    for (int i=0;i< Team12.size();i++)
                    {
                        data[i] = Team12.get(i).toString();// + "      " + PlayerType.get(i).toString();
                    }
                    */




                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }






    private static void calculatet20Points(ArrayList<Player> p) {

        int t = p.size();
        for (int i = 0; i < t; i++) {
            if (p.get(i).balls < 0) {
                p.get(i).runs = 0;

            }
            if (p.get(i).runs == 0) {
                if (p.get(i).balls > 0) {
                    p.get(i).points = p.get(i).points - 10;
                }
                if (p.get(i).balls == 0) {
                    p.get(i).points = p.get(i).points - 20;
                }
            }

            p.get(i).points = p.get(i).points + p.get(i).runs;

            if (p.get(i).runs >= 50 && p.get(i).runs < 100) {
                p.get(i).points = p.get(i).points + 25;
            }
            if (p.get(i).runs >= 100) {
                p.get(i).points = p.get(i).points + 50;
            }
            if (p.get(i).runs >= 50 && p.get(i).runs < 75) {
                p.get(i).points = p.get(i).points + 30;
            }
            if (p.get(i).runs >= 75 && p.get(i).runs < 100) {
                p.get(i).points = p.get(i).points + 40;
            }
            if (p.get(i).runs > 15) {
                if (p.get(i).SR >= 250) {
                    p.get(i).points = p.get(i).points + 50;
                }
                if (p.get(i).SR >= 200.0 && p.get(i).SR < 250) {
                    // p[i].points=p[i].points+40;
                }
                /*
                if (p[i].SR>=175.0 && p[i].SR<200.0)
                {
                    p[i].points=p[i].points+30;
                }
                if (p[i].SR>=150.0 && p[i].SR<175.0)
                {
                    p[i].points=p[i].points+20;
                }
                if (p[i].SR>=125.0 && p[i].SR<150.0)
                {
                    p[i].points=p[i].points+10;
                }
                if (p[i].SR>=75.0 && p[i].SR<100.0)
                {
                    p[i].points=p[i].points-10;
                }
                if (p[i].SR<75.0)
                {
                    p[i].points=p[i].points-20;
                }
            }
            if (p[i].runs<0)
            {
                p[i].points=p[i].points+0;
            }
            p[i].points=p[i].points+(p[i].wickets*20);
            if (p[i].wickets==3)
            {
                p[i].points=p[i].points+30;
            }
            if (p[i].wickets==4)
            {
                p[i].points=p[i].points+40;
            }
            if (p[i].wickets>4)
            {
                p[i].points=p[i].points+50;
            }

            if (p[i].overs_>=2.00)
            {
                if (p[i].economy<=5.00)
                {
                    p[i].points=p[i].points+30;
                }
                if (p[i].economy<=6.00 && p[i].economy>5.00)
                {
                    p[i].points=p[i].points+20;
                }
                if (p[i].economy<=7.00 && p[i].economy>6.00)
                {
                    p[i].points=p[i].points+10;
                }
                if (p[i].economy<=9.00 && p[i].economy>8.00)
                {
                    p[i].points=p[i].points-10;
                }
                if (p[i].economy<=10.00 && p[i].economy>9.00)
                {
                    p[i].points=p[i].points-20;
                }
                if (p[i].economy>10.00)
                {
                    p[i].points=p[i].points-25;
                }
            }
            p[i].points=p[i].points+((p[i].catches)*10);
            p[i].points=p[i].points+((p[i].stumping*15));
            p[i].points=p[i].points+((p[i].maidens_*10));
        }
        for (int i=0;i<12;i++){
            p[i].Display_Player();
        }*/
            }
        }
    }


    public void backmain(View v)
    {
        Intent intent = new Intent(this,WelcomeMenu.class);
        startActivity(intent);
        finish();
    }
}
