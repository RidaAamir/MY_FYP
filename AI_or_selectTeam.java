package com.example.emad.splashscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AI_or_selectTeam extends AppCompatActivity {


    Intent i;
    String MatchName = "";
    String Id = "";
    String UserName = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_or_select_team);

    }

    public void select_team(View view){
        Intent i = new Intent(this, SelectTeam.class);
        Intent intent = getIntent();
        MatchName = intent.getStringExtra("Teams");
        Id = intent.getStringExtra("Tid");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@  "+Id);
        UserName = intent.getStringExtra("User");

        i.putExtra("User",UserName);
        i.putExtra("Teams", MatchName);
        i.putExtra("Tid",Id);
        startActivity(i);
        finish();

    }

    public void AI_team(View view){
        Intent i = new Intent(this, AI_calculate.class);
        Intent intent = getIntent();
        MatchName = intent.getStringExtra("Teams");
        Id = intent.getStringExtra("Tid");
        UserName = intent.getStringExtra("User");

        i.putExtra("User",UserName);
        i.putExtra("Teams", MatchName);
        i.putExtra("Tid",Id);
        startActivity(i);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, WelcomeMenu.class);
        i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
    }
}
