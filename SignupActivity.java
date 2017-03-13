package com.example.emad.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;

public class SignupActivity extends AppCompatActivity {
    EditText emadsusername;
    EditText emadsemail;
    EditText emadspassword;
    EditText emadsrepassword;
    MyDBHandler dbhandler;

    String UserName = "";

    @BindView(R.id.btn_signup) android.support.v7.widget.AppCompatButton _signupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        dbhandler = new MyDBHandler(this);
        /*_signupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                android.util.Log.d("Yeh","Yahan hoon mein");
                userCreated();

            }
        });*/
        emadsusername = (EditText)findViewById(R.id.input_user);
        emadsemail = (EditText)findViewById(R.id.input_email);
        emadspassword = (EditText)findViewById(R.id.input_password);
        emadsrepassword = (EditText)findViewById(R.id.input_repass);
        //dbhandler = new MyDBHandler(this,null,null,1);


    }

    public void userCreated(View v)
    {
        boolean CheckConnectivity = UserConnectedtoInternetOrNot();

            if (emadspassword.getText().toString().isEmpty())
            {
                emadspassword.setError("Enter Password!");
            }
        if (emadsemail.getText().toString().isEmpty())
        {
            emadsemail.setError("Enter Email!");
        }


        if (emadspassword.getText().toString().equals(emadsrepassword.getText().toString()) && !emadsemail.getText().toString().isEmpty() && !emadsusername.getText().toString().isEmpty()  && CheckConnectivity) {
            User user = new User(emadsusername.getText().toString(), emadsemail.getText().toString(), emadspassword.getText().toString());
            UserName = emadsusername.getText().toString();
            dbhandler.setUserData(emadsusername.getText().toString(), emadsemail.getText().toString(), emadspassword.getText().toString());
            //android.util.Log("UserCreatedError","Iss function mein hoon mein");
            //android.widget.Toast.makeText(getBaseContext(), "User Stored in Db", android.widget.Toast.LENGTH_LONG).show();
            android.widget.Toast.makeText(getBaseContext(), "User Created Successfully!", android.widget.Toast.LENGTH_LONG).show();

            SharedPreferences sp=getSharedPreferences("Login", 0);
            SharedPreferences.Editor Ed=sp.edit();
            Ed.putString("Usr",UserName );
            Ed.putString("Psw",emadspassword.getText().toString());
            Ed.commit();
            WelcomePage();
        }else if (!emadspassword.getText().toString().equals(emadsrepassword.getText().toString()))
        {
            emadsrepassword.setError("Password Doesn't Matches!");
        }


    }

    public void BackToSamePage(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }



    public void WelcomePage()
    {
        boolean CheckConnectivity = UserConnectedtoInternetOrNot();

        if (CheckConnectivity == true)
        {

            Intent intent = new Intent(this, WelcomeMenu.class);
            intent.putExtra("User",UserName);
            startActivity(intent);
            finish();
        }
        else
        {
            android.widget.Toast.makeText(getBaseContext(), "Check Internet Connection and retry", android.widget.Toast.LENGTH_LONG).show();
        }

    }

    public boolean UserConnectedtoInternetOrNot()
    {
        ConnectivityManager connec = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connec != null && (
                (connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) ||
                        (connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED))) {

            //You are connected, do something online.
            return true;
        } else if (connec != null && (
                (connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) ||
                        (connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED ))) {

            //Not connected.
            android.widget.Toast.makeText(getBaseContext(), "No Internet Connection! Check Connection!", android.widget.Toast.LENGTH_LONG).show();
            return false;

        }
        return false;
    }


}
