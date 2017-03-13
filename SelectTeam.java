package com.example.emad.splashscreen;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import butterknife.OnClick;

import static android.R.attr.button;
import static android.R.attr.checked;

public class SelectTeam extends AppCompatActivity {
    MyDBHandler dbhandler;

    squad_calculation SC = new squad_calculation();

    TextView team1;
    TextView team2;
    String UserName = "";
    Boolean Check = true;
    public TextView a;
    ArrayList<Player> P1 = new ArrayList<Player>();
    ArrayList<Player> P2 = new ArrayList<Player>();
    ArrayList<String> Team11 = new ArrayList<>();
    ArrayList<String> Team22 = new ArrayList<>();
    ArrayList<String> TeamId = new ArrayList<>();
    Player p = new Player();
    ArrayList<Item_Player> data = new ArrayList<>();
    ArrayList<Item_Player> data11 = new ArrayList<>();
    //Player[] squad_team1 = new Player[70];
    //Player[] squad_team2 = new Player[70];

    ArrayList<Player>squad_team1=new ArrayList<>();
    ArrayList<Player>squad_team2=new ArrayList<>();
    Double budget=100.0;


    ArrayList<ArrayList<Player>> Initialized_array = new ArrayList<>();

    int count = 0;
    private TextView output;
    //Stats c = new Stats();

    ImageView Bowlers;
    ListView listview = null;
    ArrayList<String> Teams = new ArrayList<>();//creating new generic arraylist
    ArrayList<String> SelectedBowlers = new ArrayList<>();
    ArrayList<Player>selected=new ArrayList<>();
    public int number_of_players = 0;
    int index = 0;
    ArrayList<String> PlayerType = new ArrayList<>();
    ArrayList<String> PlayerId = new ArrayList<>();
    ArrayList<String> PlayerId1 = new ArrayList<>();
    ArrayList<String> PlayerInfo = new ArrayList<>();
    String MatchName = "";
    String Id = "";
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_team);
        dbhandler = new MyDBHandler(this);
        // hideSoftKeyboard();


        dialog = new Dialog(this);
        //TextView budget=(TextView)findViewById(R.id.Budget);//Budget on Crichet Ball


        Bowlers = (ImageView) findViewById(R.id.bowlers);
        //output = (TextView)findViewById(R.id.emad);
        listview = new ListView(this);
        Intent intent = getIntent();
        MatchName = intent.getStringExtra("Teams");
        Id = intent.getStringExtra("Tid");

        UserName = intent.getStringExtra("User");



        System.out.println("\n\n\n #######3API Key that Provides Available squad of a team: ");
        System.out.println(Id + "      888888888888888888888888888888888888888888888888888888888888888888888888888888888888");


        JSONTask b = new JSONTask();  //CHANGE THAT I EXECUTED ##CHHHHHH
        b.execute("http://cricapi.com/api/fantasySummary?unique_id=" + Id + "&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2");
            }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, AI_or_selectTeam.class);
        i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
        finish();
    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            System.out.println("!!!!!!!!!!!!!!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~`HIDING MY KEYBOARD");
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }


    public class JSONTask extends AsyncTask<String, String, String> {


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
        protected void onPreExecute() {
            super.onPreExecute();


        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            String response_ = s;
            try {
                System.out.println(response_ + "      888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
                Initialized_array = SC.parsingNames(response_, p);  //getting names of the chosen series
                final JSONObject obj1 = new JSONObject(response_);
                // System.out.println(obj1);
                // System.out.println("ss");
                final JSONObject test = obj1.getJSONObject("data");

                final JSONArray geodata1 = test.getJSONArray("team"); // getting squad
                System.out.println(geodata1 + "\n\n\n");
                final JSONObject team1 = geodata1.getJSONObject(0);
                final JSONObject team2 = geodata1.getJSONObject(1);


                TextView team1_=(TextView)findViewById(R.id.team1);
                TextView team2_=(TextView)findViewById(R.id.team2);
                team1_.setText(team1.getString("name"));
                team2_.setText(team2.getString("name"));



            } catch (JSONException e) {
                e.printStackTrace();
            }

            squad_team1.clear();
            squad_team2.clear();

            squad_team1 = Initialized_array.get(0);
            squad_team2 = Initialized_array.get(1);
            int n = squad_team1.size();
            /*for(int i=0;i<n;i++){
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%NOW I AM IN SELECT TEAM HENCE MOVING FORMWARD MASHALALLAH "+i+" %%%%%%%%%%%%%%%");
                System.out.println(squad_team1.get(i).name);

            }*/



            int n1 = squad_team1.size();
            for (int i = 0; i < n1; i++) {
                Player p = squad_team1.get(i);
                Item_Player P=new Item_Player(p.name,p.type,p.cost);

                data.add( P);

            }

            int n2 = squad_team2.size();
            for (int i = 0; i < n2; i++) {
                Player p = squad_team2.get(i);
                Item_Player P=new Item_Player(p.name,p.type,p.cost);

                data11.add(P);

            }
            //STARTING POST-EXECUTE WALA CODE -----------------------------------------------------------------------------

            dialog.setContentView(R.layout.dialouge);
            dialog.setTitle("Submit your selected Team");

            final ListView listV = (ListView) findViewById(R.id.Players);
            final customAdapter adapter = new customAdapter(SelectTeam.this, R.layout.clayout, data);
            final customAdapter adapter1 = new customAdapter(SelectTeam.this, R.layout.clayout, data11);
            final ListView listV2 = (ListView) findViewById(R.id.Team2Players);
            adapter1.notifyDataSetChanged();
            adapter.notifyDataSetChanged();

            final TextView budget_T=(TextView)findViewById(R.id.budget);


            listV.setAdapter(adapter);
            listV.setItemsCanFocus(true);
            listV2.setAdapter(adapter1);
            listV2.setItemsCanFocus(true);

            listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CheckedTextView ctv = (CheckedTextView) view;
                    Item_Player the_player=data.get(position);
                    Player p1=new Player();
                    p1.name=the_player.name;
                    p1.cost=the_player.cost;
                    p1.type=the_player.type;
                    // android.widget.Toast.makeText(getBaseContext(), "Item Clicked!", android.widget.Toast.LENGTH_LONG).show();
                    String selectedbowler = ((TextView) view).getText().toString();
                    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHH "+p1.name+p1.cost+p1.type);

                    //  Item_Player it_p=(Item_Player) view.getTag();


                    if (selected.contains(p1)) {
                        SelectedBowlers.remove(selectedbowler);
                        ctv.setChecked(false);
                        // view.setBackgroundColor(Color.TRANSPARENT);
                        //listV.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);
                    }
                    else {
                        if (selected.size() < 11) {
                            // Toast.makeText(SelectTeam.this,"Added PLayer!! ",Toast.LENGTH_SHORT).show();
                            number_of_players++;
                            ctv.setChecked(true);
                            // view.setBackgroundColor(Color.BLUE);
                            // listV.setBackgroundColor(Color.BLUE);
                            budget=budget-p1.cost;

                            selected.add(p1);
                            budget_T.setText(budget.toString());
                            System.out.println("Player from team1 selected,,,,,,,, name added is: "+selectedbowler);
                            String Bowlers = "";
                            for (String Bowler : SelectedBowlers) {
                                Bowlers += "-" + Bowler + "\n";
                            }
                            android.widget.Toast.makeText(getBaseContext(), "You selected this formation: " + Integer.toString(number_of_players) + Bowlers, android.widget.Toast.LENGTH_LONG).show();


                        } if(selected.size() == 11) {
                            Toast.makeText(SelectTeam.this, "You have reached your Team Limit! ", Toast.LENGTH_SHORT).show();
                            //Button submit=new Button();
                            Button cancel1 = (Button) dialog.findViewById(R.id.cancel);
                            cancel1.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    dialog.dismiss();
                                }
                            });
                            Button save1 = (Button) dialog.findViewById(R.id.save);
                            save1.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    userCreated(v);
                                }
                            });
                            dialog.show();
                        }
                    }


                }
            });

            listV2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CheckedTextView ctv = (CheckedTextView) view;
                    // android.widget.Toast.makeText(getBaseContext(), "Item Clicked!", android.widget.Toast.LENGTH_LONG).show();
                    String selectedbowler = ((TextView) view).getText().toString();
                    Item_Player the_player=data11.get(position);
                    Player p1=new Player();
                    p1.name=the_player.name;
                    p1.cost=the_player.cost;
                    p1.type=the_player.type;
                    // android.widget.Toast.makeText(getBaseContext(), "Item Clicked!", android.widget.Toast.LENGTH_LONG).show();
                    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHH "+p1.name+p1.cost+p1.type);

                    //view= adapter.getView(position,view,parent);
                    //Item_Player it_p=(Item_Player) view.getTag();

                   System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHH"+selectedbowler);

                    if (selected.contains(p1)) {
                        SelectedBowlers.remove(selectedbowler);
                        ctv.setChecked(false);
                        android.widget.Toast.makeText(getBaseContext(), "Already chosen " + Integer.toString(number_of_players) + ". " + Bowlers, android.widget.Toast.LENGTH_LONG).show();

                        // view.setBackgroundColor(Color.TRANSPARENT);
                        //listV.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);
                    }
                    else {
                        if (selected.size() < 11) {
                            //Toast.makeText(SelectTeam.this,"Adding player! ",Toast.LENGTH_SHORT).show();
                            number_of_players++;
                          //  selected.add(it_p);
                            //listV.setItemChecked(position,true);
                            ctv.setChecked(true);
                            budget=budget-p1.cost;
                            //view.setBackgroundColor(Color.BLUE);
                            // listV.setBackgroundColor(Color.BLUE);
                            selected.add(p1);
                            budget_T.setText(budget.toString());
                            System.out.println("Player from team1 selected ,,,,,,,, name added is: "+selectedbowler);
                            String Bowlers = "";
                            for (String Bowler : SelectedBowlers) {
                                Bowlers += "-" + Bowler + "\n";
                            }
                            android.widget.Toast.makeText(getBaseContext(), "You selected this formation: " + Integer.toString(number_of_players) + ". " + Bowlers, android.widget.Toast.LENGTH_LONG).show();


                        }
                        if(selected.size() == 11){
                            Toast.makeText(SelectTeam.this, "You have reached your Team Limit! ", Toast.LENGTH_SHORT).show();
                            Button cancel1 = (Button) dialog.findViewById(R.id.cancel);
                            cancel1.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    dialog.dismiss();
                                }
                            });
                            Button save1 = (Button) dialog.findViewById(R.id.save);
                            save1.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    userCreated(v);
                                }
                            });
                            dialog.show();

                        }
                    }

                }
            });

        }

        public void userCreated(View v) {
            for (int i=0;i<=11;i++){

            }
            if (selected.isEmpty()) {
                android.widget.Toast.makeText(getBaseContext(), "Khaali hoon bhai, kya kar raha hai?", android.widget.Toast.LENGTH_LONG).show();
            }


            a = (TextView) findViewById(R.id.teamsquad);
            String TeamName = a.getText().toString();


            if (TeamName.equals("") || TeamName == null) {
                android.widget.Toast.makeText(getBaseContext(), "Please Enter a Team Name (At the Top!)", android.widget.Toast.LENGTH_LONG).show();
            }


            boolean CheckName;
            if (TeamName != null || !TeamName.equals("")) {
                CheckName = true; // TeamName is given by star
                System.out.println("@@@@@@@@@@@@@@@@@@@@@ YYOU HAVE ENTERED YOUR TEAM NAME!!!! ");

            } else {
                CheckName = false;
            }
           if (selected.size() > 0 && selected.size() < 11 ) {
                android.widget.Toast.makeText(getBaseContext(), "You have selected" + selected.size() + " Players.. Select " + (11 - SelectedBowlers.size()) + " Players to complete your team", android.widget.Toast.LENGTH_LONG).show();
            }

            if(selected.size() > 0 && selected.size() == 11 && CheckName) {

                android.widget.Toast.makeText(getBaseContext(), "I'm not empty :) I passed this stage", android.widget.Toast.LENGTH_LONG).show();
                for(int i=0;i<selected.size();i++) {
                    System.out.println(i+" @@@@@@@@@@@@@@@@@@@@@ YOU HAVE SELECTED 11 PLAYERS ,,,,,, AB AAP INTENT MEN JAANE LAGE HEN     " + selected.get(i));
                }



                ArrayList<Player> OnlyBowlers = new ArrayList<>();
                ArrayList<Player> OnlyAlrounder = new ArrayList<>();
                ArrayList<Player> OnlyBatsman = new ArrayList<>();
                ArrayList<Player> OnlyWicketKeeper = new ArrayList<>();
                dbhandler.createTeam();
                for (int j = 0; j < selected.size(); j++) {
                    dbhandler.setTeam(UserName, TeamName, MatchName, Id, selected.get(j).name);
                }

                //ArrayList<ArrayList<String>> getting=new ArrayList<>();
                //getting=SC.get_formation(selected);//function in Squad_Calculation Class

                //OnlyBowlers=getting.get(0);
                //OnlyAlrounder=getting.get(1);
                //OnlyBatsman=getting.get(2);
                //OnlyWicketKeeper=getting.get(3);



                for (int i = 0; i < selected.size(); i++) {
                    System.out.println(i+"   I am in for loop    "+selected.get(i).type);


                    if (selected.get(i).type.contains("Bowler") || selected.get(i).type.contains("bowling") || selected.get(i).type.contains("Bowling")) {
                        OnlyBowlers.add(selected.get(i));
                        System.out.println("in bowlers");
                    }

                    if (selected.get(i).type.contains("allrounder") || selected.get(i).type.contains("Allrounder") || selected.get(i).type.contains("All-Rounder") || selected.get(i).type.contains("Bowling allrounder")) {
                        OnlyAlrounder.add(selected.get(i));
                        System.out.println("in Allrounder");
                    }

                    if (selected.get(i).type.contains("batsman") || selected.get(i).type.contains("Batsman")) {
                        OnlyBatsman.add(selected.get(i));
                        System.out.println("in bowlers");
                    }

                    if (selected.get(i).type.contains("Wicketkeeper") || selected.get(i).type.contains("WicketKeeper") || selected.get(i).type.contains("Wicketkeeper batsman")) {
                        OnlyWicketKeeper.add(selected.get(i));
                        System.out.println("in wicketkeeper");
                    }

                }




                //System.out.println("LALALALALALALALA__BOWLERSSSS->  " + OnlyBowlers.get(0));
                //System.out.println("LALALALALALALLALALAL__BAtsman->  " + OnlyAlrounder.get(0));
                //System.out.println("LALALALALALALLALALALALAL__Allrounder->  " + OnlyBatsman.get(0));
                //System.out.println("LALALALALLALALALAALALA__Wicket Keeper->  " + OnlyWicketKeeper.get(0));
                Intent intent = new Intent(SelectTeam.this, Team.class);
                intent.putExtra("Tid", Id);
                intent.putExtra("User", UserName);
                intent.putExtra("TeamName", TeamName);
                intent.putExtra("batsman",new PlayerWrapper(OnlyBatsman));
                intent.putExtra("bowler",new PlayerWrapper(OnlyBowlers));
                intent.putExtra("batsman",new PlayerWrapper(OnlyBatsman));
                intent.putExtra("allrounder",new PlayerWrapper(OnlyAlrounder));
                intent.putExtra("wicketKeeper",new PlayerWrapper(OnlyWicketKeeper));

                //START FROM HERE!!

                startActivity(intent);
                //finish();
            }
            else {
                android.widget.Toast.makeText(getBaseContext(), "Khali hoon bhai kya kar raha hai?", android.widget.Toast.LENGTH_LONG).show();
            }

        }







        public void ShowFormation(View view) {
         /*if(SelectedBowlers.isEmpty())
        {
            android.widget.Toast.makeText(getBaseContext(), "Khaali hoon bhai, kya kar raha hai?", android.widget.Toast.LENGTH_LONG).show();
        }
            else*/
       /* if(SelectedBowlers.size() > 0)
        {

            android.widget.Toast.makeText(getBaseContext(), "I'm not empty :) I passed this stage", android.widget.Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, DisplayTeam.class);
            ArrayList <String> OnlyBowlers = new ArrayList<>();

            for (int i = 0;i< SelectedBowlers.size();i++)
            {
                if (SelectedBowlers.get(i).contains("Bowler") || SelectedBowlers.get(i).contains("bowling") || SelectedBowlers.get(i).contains("Bowling"))
                {
                    OnlyBowlers.add(SelectedBowlers.get(i).toString());
                }
            }

            intent.putStringArrayListExtra("Teams", OnlyBowlers);
            startActivity(intent);
            finish();
        }
        else {
            android.widget.Toast.makeText(getBaseContext(), "Khaali hoon bhai, kya kar raha hai?", android.widget.Toast.LENGTH_LONG).show();
        }*/
        }
    }
}