package com.example.philippe.seg3125test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button profileButton;
    Button glossaryButton;
    Button learnButton;
    ImageButton mathButton;
    Toolbar toolbar;

    SharedPreferences userData;

    private MenuItem logoutAction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userData = getSharedPreferences("studyonPreferences", MODE_PRIVATE);
        learnButton = (Button) findViewById(R.id.button_learn);
        learnButton.setTextColor(ColorStateList.valueOf(Color.BLUE));
        profileButton = (Button) findViewById(R.id.button_profile);
        profileButton.setOnClickListener(this);
        glossaryButton = (Button) findViewById(R.id.button_glossary);
        glossaryButton.setOnClickListener(this);
        mathButton = (ImageButton) findViewById((R.id.button_mathematics));
        mathButton.setOnClickListener(this);


    }




    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_profile :
                Intent online = new Intent(this, ProfileActivity.class);
                Intent offline = new Intent(this, LoginPage.class);
                if (userData.getBoolean("online", false)) startActivity(online);
                else startActivity(offline);
                overridePendingTransition(0, 0);
                break;
            case R.id.button_glossary:
                Intent intentg = new Intent(this, GlossaryActivity.class);
                startActivity(intentg);
                overridePendingTransition(0, 0);
                break;
            case R.id.button_mathematics:
                Intent intentm = new Intent(this, MathTopicsActivity.class);
                startActivity(intentm);
                overridePendingTransition(0, 0);
                break;
        }
    }
}
