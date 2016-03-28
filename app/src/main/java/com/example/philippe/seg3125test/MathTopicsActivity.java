package com.example.philippe.seg3125test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MathTopicsActivity extends Activity implements View.OnClickListener {

    private Button profileButton;
    private Button glossaryButton;
    private Button learnButton;

    private Button algebraButton;
    SharedPreferences userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_topics);

        profileButton = (Button) findViewById(R.id.button_profile);
        profileButton.setOnClickListener(this);
        glossaryButton = (Button) findViewById(R.id.button_glossary);
        glossaryButton.setOnClickListener(this);
        learnButton = (Button) findViewById(R.id.button_learn);
        learnButton.setOnClickListener(this);
        learnButton.setTextColor(ColorStateList.valueOf(Color.BLUE));
        userData = getSharedPreferences("studyonPreferences", MODE_PRIVATE);
        algebraButton = (Button) findViewById(R.id.algebra_button);
        algebraButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_profile:
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
            case R.id.button_learn:
                Intent intentl = new Intent(this, MainActivity.class);
                startActivity(intentl);
                overridePendingTransition(0, 0);
                break;
            case R.id.algebra_button:
                Intent intentAlg = new Intent(this, AlgebraActivity.class);
                startActivity(intentAlg);
                overridePendingTransition(0,0);
                break;
        }
    }
}
