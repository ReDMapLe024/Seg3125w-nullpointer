package com.example.philippe.seg3125test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences userData;

    Button learnButton;
    Button glossaryButton;
    Button profileButton;
    private MenuItem logoutAction;

    TextView emailTV;

    private ProgressBar pbarMath = null;
    private ProgressBar pbarScience = null;
    private ProgressBar pbarEconomics = null;
    private ProgressBar pbarFinance = null;
    private ProgressBar pbarComputers = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeProgressBars();

        userData = getSharedPreferences("studyonPreferences", MODE_PRIVATE);

        emailTV = (TextView) findViewById(R.id.emailTV);
        emailTV.setText(userData.getString("email", "no email")); // Set TextView to user's email address.

        updateProgressBars(); // Set progress bar to appropriate value.

    } // end onCreate

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        logoutAction = menu.findItem(R.id.action_logout);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_logout:
                logout();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button_learn :
                Intent intentl = new Intent(this, MainActivity.class);
                startActivity(intentl);
                overridePendingTransition(0, 0);
                break;
            case R.id.button_glossary :
                Intent intentg = new Intent(this, GlossaryActivity.class);
                startActivity(intentg);
                overridePendingTransition(0, 0);
                break;
        }

    } // end onClick

    /**
     * Updates the progress bars once the user has logged in.
     */
    public void updateProgressBars() {

        pbarMath.setProgress(userData.getInt("math", 0));
        pbarScience.setProgress(userData.getInt("science", 0));
        pbarEconomics.setProgress(userData.getInt("economics", 0));
        pbarFinance.setProgress(userData.getInt("finance", 0));
        pbarComputers.setProgress(userData.getInt("computers", 0));

    } // end updateProgressBars

    /**
     * Initialize progressBar values.
     */
    public void initializeProgressBars() {

        pbarMath = (ProgressBar) findViewById(R.id.pbar_math);
        pbarMath.setMax(200);
        pbarMath.setVisibility(View.VISIBLE);
        pbarMath.setProgressBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));

        pbarScience = (ProgressBar) findViewById(R.id.pbar_science);
        pbarScience.setMax(200);
        pbarScience.setVisibility(View.VISIBLE);
        pbarScience.setProgressBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));

        pbarEconomics = (ProgressBar) findViewById(R.id.pbar_economics);
        pbarEconomics.setMax(200);
        pbarEconomics.setVisibility(View.VISIBLE);
        pbarEconomics.setProgressBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));

        pbarFinance = (ProgressBar) findViewById(R.id.pbar_finance);
        pbarFinance.setMax(200);
        pbarFinance.setVisibility(View.VISIBLE);
        pbarFinance.setProgressBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));

        pbarComputers = (ProgressBar) findViewById(R.id.pbar_computers);
        pbarComputers.setMax(200);
        pbarComputers.setVisibility(View.VISIBLE);
        pbarComputers.setProgressBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));

        learnButton = (Button) findViewById(R.id.button_learn);
        learnButton.setOnClickListener(this);
        glossaryButton = (Button) findViewById(R.id.button_glossary);
        glossaryButton.setOnClickListener(this);
        profileButton = (Button) findViewById(R.id.button_profile);
        profileButton.setTextColor(ColorStateList.valueOf(Color.BLUE));

    }

    /**
     * Logs a user out.
     */
    public void logout() {

        if (userData.getBoolean("online", false)) { // If user is online.

            SharedPreferences.Editor ed = userData.edit();
            ed.putBoolean("online", false);
            ed.commit();

            Toast.makeText(this, "Logging out..", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, LoginPage.class));
            finish();

        }

    } // end logout

} // end ProfileActivity