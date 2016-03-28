package com.example.philippe.seg3125test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.DecimalFormat;

public class AlgebraQuiz2Activity extends Activity implements View.OnClickListener {

    SharedPreferences userData;

    private RadioGroup q1_radioGroup;
    private RadioGroup q2_radioGroup;
    private RadioGroup q3_radioGroup;
    private RadioButton ans1;
    private RadioButton ans2;
    private RadioButton ans3;

    Button profileButton;
    Button glossaryButton;
    Button learnButton;

    private String q1_Sol = "x = y + 5";
    private String q2_Sol = "x = 9y - 6";
    private String q3_Sol = "x = -6y + 5";
    private int total = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algebra_quiz2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });

        toolbar.inflateMenu(R.menu.toolbar_menu_profile);
        learnButton = (Button) findViewById(R.id.button_learn);
        learnButton.setTextColor(ColorStateList.valueOf(Color.BLUE));
        profileButton = (Button) findViewById(R.id.button_profile);
        profileButton.setOnClickListener(this);
        glossaryButton = (Button) findViewById(R.id.button_glossary);
        glossaryButton.setOnClickListener(this);

        userData = getSharedPreferences("studyonPreferences", MODE_PRIVATE);

    }

    public void calcResult (View view) {

        double score = 0;
        double totalScore;
        q1_radioGroup = (RadioGroup) findViewById(R.id.q1);
        q2_radioGroup = (RadioGroup) findViewById(R.id.q2);
        q3_radioGroup = (RadioGroup) findViewById(R.id.q3);

        //Get selection from each radioGroup
        int selectedID1 = q1_radioGroup.getCheckedRadioButtonId();
        int selectedID2 = q2_radioGroup.getCheckedRadioButtonId();
        int selectedID3 = q3_radioGroup.getCheckedRadioButtonId();

        //Find the radiobutton for each group
        ans1 = (RadioButton) findViewById(selectedID1);
        ans2 = (RadioButton) findViewById(selectedID2);
        ans3 = (RadioButton) findViewById(selectedID3);

        if (ans1 == null || ans2 == null || ans3 == null) { // All answers are empty.
            Toast.makeText(this, "Please answer all questions", Toast.LENGTH_LONG).show();
        } else {

            String strAns1 = ans1.getText().toString();
            String strAns2 = ans2.getText().toString();
            String strAns3 = ans3.getText().toString();

            // Checks user's answers against solutions stored in class
            if(strAns1.equals(q1_Sol)) {
                score++;
            }
            if(strAns2.equals(q2_Sol)){
                score++;
            }
            if(strAns3.equals(q3_Sol)){
                score++;
            }

            //Calculate result in percentage
            totalScore = (score/total) * 100;

            Context context = getApplicationContext();
            CharSequence text = "Quiz Completed!  You Scored: " + (new DecimalFormat("#0.00").format(totalScore)) + "%";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            // Update user in database if logged in.
            if (userData.getBoolean("online", false)) {

                int currentMath = userData.getInt("math", 0);
                int currentQuiz = userData.getInt("MathQuiz2", 0);
                int points = (int) (totalScore - currentQuiz);
                Log.v("NUMBER OF POINTS", String.valueOf(points));
                if (points > 1) {
                    SharedPreferences.Editor ed = userData.edit();
                    ed.putInt("MathQuiz2", currentQuiz + points);
                    ed.putInt("math", currentMath + points);
                    ed.commit();
                }

            }

            startActivity(new Intent(this, AlgebraActivity.class));
            finish();

        }


    } // end calcResult

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

    } // end onClick

} // end AlgebraQuizActivity2
