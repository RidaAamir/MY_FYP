package com.example.emad.splashscreen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Team extends AppCompatActivity {
    ImageView Allrounder;
    ImageView Batsmen;
    ImageView Bowlers;
    ImageView Wicketkeepers;

    String UserName = "";

    ListView listview = null;
    ListView listview1 = null;
    ListView listview2= null;
    ListView listview3 = null;

    ArrayList<String> SelectedBowlers = new ArrayList<>();
    ArrayList<String> SelectedBowlers1 = new ArrayList<>();
    ArrayList<String> SelectedBowlers2 = new ArrayList<>();
    ArrayList<String> SelectedBowlers3 = new ArrayList<>();
    EditText input;
    EditText input1;
    EditText input2;
    EditText input3;
    final String[] FormA = new String[1];
    final String[] FormB = new String[1];
    final String[] FormC = new String[1];
    final String[] FormD = new String[1];
    int a,b,c,d;
    private TextView txtTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&I AM IN TEAM");



        txtTemp =(TextView) findViewById(R.id.teamnames);
        Allrounder = (ImageView) findViewById(R.id.allrounder);
        Batsmen = (ImageView) findViewById(R.id.batsmen);
        Bowlers = (ImageView) findViewById(R.id.bowlers);
        Wicketkeepers = (ImageView) findViewById(R.id.wicketkeeper);
            /*
            * intent.putExtra("TeamName",TeamName);
            intent.putStringArrayListExtra("Bowlers", OnlyBowlers);
            intent.putStringArrayListExtra("Batman", OnlyBatsman);
            intent.putStringArrayListExtra("Allrounders", OnlyAlrounder);
            intent.putStringArrayListExtra("Wicketkeeper", OnlyWicketKeeper);
            * */


        //GETTING VALUE FROM PREVIOUS ACTIVITY
        Intent intent = getIntent();
        TextView ab = (TextView)findViewById(R.id.UserName);

        UserName = getIntent().getStringExtra("User");
        ab.setText(UserName);
        String TeamName = intent.getStringExtra("TeamName");
        PlayerWrapper dw = (PlayerWrapper) intent.getSerializableExtra("bowler");
        PlayerWrapper dw2 = (PlayerWrapper) intent.getSerializableExtra("batsman");
        PlayerWrapper dw3 = (PlayerWrapper) intent.getSerializableExtra("allrounder");
        PlayerWrapper dw4= (PlayerWrapper) intent.getSerializableExtra("wicketKeeper");

        ArrayList <Player> BowlersA = dw.getPlayerArray();
        ArrayList <Player> BatsmanA = dw2.getPlayerArray();
        ArrayList <Player> AllRoundersA = dw3.getPlayerArray();
        ArrayList <Player> WicketkeeperA = dw4.getPlayerArray();

        txtTemp.setText(TeamName);
        if(BatsmanA.isEmpty()||BowlersA.isEmpty()||AllRoundersA.isEmpty()){
            System.out.println("HAAAAAAAAAAAAYYYYYYYYYYYYEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE!!!!!!!!!!! Bowlers wagera empty hain!!");
        }
        else {
            for (int i = 0; i < 1; i++) {
                System.out.println("BOWLERSSSS->  " + BowlersA.get(i).name);
                System.out.println("BAtsman->  " + BatsmanA.get(i).name);
                System.out.println("Allrounder->  " + AllRoundersA.get(i).name);
                System.out.println("Wicket Keeper->  " + WicketkeeperA.get(i).name);
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("All-Rounders");
        builder.setMessage("Your Batsmen: ");

        listview = new ListView(this);
        input = new EditText(this);
       // builder.setView(listview);




        String []data = new String[AllRoundersA.size()]; //= {"Dwayne Bravo   Alrounder","J. Charles   Alrounder","Chris Gayle   Alrounder","Shahid Afridi   Alrounder"};
        for (int i = 0;i<AllRoundersA.size();i++)
        {
            data[i] = AllRoundersA.get(i).name;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.blayout,data);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedbowler = ((TextView)view).getText().toString();

                if (SelectedBowlers.contains(selectedbowler))
                {
                    SelectedBowlers.remove(selectedbowler);
                }
                else
                {
                    listview.setItemChecked(position,true);
                    SelectedBowlers.add(selectedbowler);
                }
            }
        });
        //input = new EditText(this);
        builder.setView(listview);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Batsmen");
        builder1.setMessage("Your Batsmen: ");

        listview1 = new ListView(this);
        input1 = new EditText(this);


        String []data1 = new String[BatsmanA.size()];//= {"Darren Bravo   Batsman","Misbah-Ul-Haq   Batsman","Umar Akmal   Batsman","M. Rizwan   Batsman"};

        for (int i = 0;i<BatsmanA.size();i++)
        {
            data1[i] = BatsmanA.get(i).name;
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.blayout,data1);
        listview1.setAdapter(adapter1);
        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedbowler1 = ((TextView)view).getText().toString();

                if (SelectedBowlers1.contains(selectedbowler1))
                {
                    SelectedBowlers1.remove(selectedbowler1);
                }
                else
                {
                    listview1.setItemChecked(position,true);
                    SelectedBowlers1.add(selectedbowler1);
                }
            }
        });
        builder1.setView(listview1);

        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("Bowlers");
        builder2.setMessage("Your Bowlers: ");

        listview2 = new ListView(this);
        input2 = new EditText(this);



         /*
        * ArrayList <String> BowlersA = getIntent().getStringArrayListExtra("Bowlers");
        ArrayList <String> BatsmanA = getIntent().getStringArrayListExtra("Batman");
        ArrayList <String> AllRoundersA = getIntent().getStringArrayListExtra("Allrounders");
        ArrayList <String> WicketkeeperA = getIntent().getStringArrayListExtra("Wicketkeeper");
        *
        * */
        String []data2 = new String[BowlersA.size()]; //= {"Abdul Rehman  Bowler","Kema Roach  Bowler","J. Holder  Bowler","Best  Bowler"};

        for (int i = 0;i< BowlersA.size();i++)
        {
            data2[i] = BowlersA.get(i).name;
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.blayout,data2);
        listview2.setAdapter(adapter2);
        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedbowler2 = ((TextView)view).getText().toString();

                if (SelectedBowlers2.contains(selectedbowler2))
                {
                    SelectedBowlers2.remove(selectedbowler2);
                }
                else
                {
                    listview2.setItemChecked(position,true);
                    SelectedBowlers2.add(selectedbowler2);
                }
            }
        });



        builder2.setView(listview2);

        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
        builder3.setTitle("Wicket Keepers");
        builder3.setMessage("Your WicketKeepers: ");

        listview3 = new ListView(this);
        input3 = new EditText(this);

         /*
        * ArrayList <String> BowlersA = getIntent().getStringArrayListExtra("Bowlers");
        ArrayList <String> BatsmanA = getIntent().getStringArrayListExtra("Batman");
        ArrayList <String> AllRoundersA = getIntent().getStringArrayListExtra("Allrounders");
        ArrayList <String> WicketkeeperA = getIntent().getStringArrayListExtra("Wicketkeeper");
        *
        * */
        String []data3 = new String[WicketkeeperA.size()];//= {"Umar Akmal  WicketKeeper","M. Rizwan  WicketKeeper","Dinesh Ramdin  WicketKeeper"};

        for (int i=0;i<WicketkeeperA.size();i++)
        {
            data3[i] = WicketkeeperA.get(i).name;
        }
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.blayout,data3);
        listview3.setAdapter(adapter3);
        listview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedbowler3 = ((TextView)view).getText().toString();

                if (SelectedBowlers3.contains(selectedbowler3))
                {
                    SelectedBowlers3.remove(selectedbowler3);
                }
                else
                {
                    listview3.setItemChecked(position,true);
                    SelectedBowlers3.add(selectedbowler3);
                }
            }
        });


        builder3.setView(listview3);

        //For First Builder
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                android.widget.Toast.makeText(getBaseContext(), "You selected this formation: " + SelectedBowlers.size(), android.widget.Toast.LENGTH_LONG).show();
               // a = Integer.parseInt(input.getText().toString());
                a = SelectedBowlers.size();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog ad  = builder.create();

        //For Second Builder
        builder1.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                android.widget.Toast.makeText(getBaseContext(), "You selected this formation: " + SelectedBowlers1.size(), android.widget.Toast.LENGTH_LONG).show();
                //b = Integer.parseInt(input1.getText().toString());
                b = SelectedBowlers1.size();
            }
        });

        builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        final AlertDialog ad1  = builder1.create();

        //For Third Builder
        builder2.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                android.widget.Toast.makeText(getBaseContext(), "You selected this formation: " + SelectedBowlers2.size(), android.widget.Toast.LENGTH_LONG).show();
                //c = Integer.parseInt(input2.getText().toString());
                c = SelectedBowlers2.size();
            }
        });

        builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog ad2  = builder2.create();


        //For Fourth Builder
        builder3.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                android.widget.Toast.makeText(getBaseContext(), "You selected this formation: " + SelectedBowlers3.size(), android.widget.Toast.LENGTH_LONG).show();
                //d = Integer.parseInt(input3.getText().toString());
                d = SelectedBowlers3.size();
            }
        });

        builder3.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog ad3  = builder3.create();





        Allrounder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.setTitle("Set AllRounders Formation");
                ad.setMessage("What Formation do you want?");
                //FormA[0] = input.getText().toString();

                ad.show();

            }
        });

        Batsmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad1.setTitle("Set Batsmen Formation");
                ad1.setMessage("What Formation do you want?");
                //FormB[0] = input1.getText().toString();

                ad1.show();


            }
        });

        Bowlers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad2.setTitle("Set Bowlers Formation");
                ad2.setMessage("What Formation do you want?");
                //FormC[0] = input.getText().toString();
                ad2.show();

            }
        });

        Wicketkeepers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad3.setTitle("Set Wicket Keepers Formation");
                ad3.setMessage("What Formation do you want?");
//                FormD[0] = input.getText().toString();
                ad3.show();

            }
        });


    }

public void GoBack(View v)
{
    Intent intent = new Intent(this, WelcomeMenu.class);
    startActivity(intent);
    finish();
}


    public void GoForward(View v)
    {
        System.out.println("122222222222222222222222222222222222222&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&I AM IN TEAM");


        Intent intent = new Intent(this, MyFormation.class);
        Bundle extras = new Bundle();
        Bundle extras1 = new Bundle();
        extras.putString("AllRounders",FormA[0]);
        extras.putString("Batsman",FormB[0]);
        extras.putString("Bowlers",FormC[0]);
        extras1.putString("WicketKeepers",FormD[0]);


        intent.putExtra("AllRounders",a);
        intent.putExtra("Batsman",b);
        intent.putExtra("Bowlers",c);
        intent.putExtra("WicketKeepers",d);
        //intent.putExtras(extras1);

        startActivity(intent);
        finish();
    }
}
