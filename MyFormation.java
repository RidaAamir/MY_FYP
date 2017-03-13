package com.example.emad.splashscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.Normalizer;

public class MyFormation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_formation);
       Intent intent = getIntent();
        //*Bundle extras = intent.getExtras();
       //Bundle extras1 = intent.getExtras();*//*
        int AllRounders = intent.getIntExtra("AllRounders",0);//extras.getString("AllRounders");
        int Batsman = intent.getIntExtra("Batsman",0);
        int Bowlers = intent.getIntExtra("Bowlers",0);
        int WicketKeepers = intent.getIntExtra("WicketKeepers",0);

        System.out.println("@@@@@@@@@@@@@@@@@@@########################  "+AllRounders+" "+Batsman+" "+Bowlers+" "+WicketKeepers);
        TextView abc = (TextView)findViewById(R.id.info);

        abc.setText(WicketKeepers + " " + Bowlers + " " +  Batsman + " "+AllRounders);

        /*extras.putString("AllRounders",FormA[0]);
        extras.putString("Batsman",FormB[0]);
        extras.putString("Bowlers",FormC[0]);
        extras.putString("WicketKeepers",FormD[0]);*/

        LinearLayout layout = (LinearLayout)findViewById(R.id.Shirts);
        LinearLayout layout1 = (LinearLayout)findViewById(R.id.Shirts1);
        LinearLayout layout2 = (LinearLayout)findViewById(R.id.Shirts2);
        LinearLayout layout3 = (LinearLayout)findViewById(R.id.Shirts3);

       // int Ar = Integer.parseInt(AllRounders);

        for(int i=0;i<AllRounders;i++)
        {
            ImageView image = new ImageView(this);
            image.setImageResource(R.drawable.allrounders);
            image.setLayoutParams(new android.view.ViewGroup.LayoutParams(150,150));
            image.setMaxHeight(100);
            image.setMaxWidth(100);

            // Adds the view to the layout
            layout.addView(image);
        }

        for(int i=0;i<Bowlers;i++)
        {
            ImageView image = new ImageView(this);
            image.setImageResource(R.drawable.bowlers);
            image.setLayoutParams(new android.view.ViewGroup.LayoutParams(150,150));
            image.setMaxHeight(100);
            image.setMaxWidth(100);

            // Adds the view to the layout
            layout1.addView(image);
        }

        for(int i=0;i<WicketKeepers;i++)
        {
            ImageView image = new ImageView(this);
            image.setImageResource(R.drawable.wicketkeepers);
            image.setLayoutParams(new android.view.ViewGroup.LayoutParams(150,150));
            image.setMaxHeight(100);
            image.setMaxWidth(100);

            // Adds the view to the layout
            layout2.addView(image);
        }

        for(int i=0;i<Batsman;i++)
        {
            ImageView image = new ImageView(this);
            image.setImageResource(R.drawable.batsmen);
            image.setLayoutParams(new android.view.ViewGroup.LayoutParams(150,150));
            image.setMaxHeight(100);
            image.setMaxWidth(100);

            // Adds the view to the layout
            layout3.addView(image);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, WelcomeMenu.class);
        i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
    }

    public void TeamFormation(View v)
    {
        Intent intent = new Intent(this,WelcomeMenu.class);
        //android.support.v7.widget.AppCompatButton b = (android.support.v7.widget.AppCompatButton)findViewById(R.id.button);
        TextView b = (TextView) v;
        startActivity(intent);
        finish();
    }
}
