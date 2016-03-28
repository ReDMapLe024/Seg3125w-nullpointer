package com.example.philippe.seg3125test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginPage extends Activity implements View.OnClickListener {

    SharedPreferences userData;

    Button loginBtn;
    EditText emailET, passwordET;
    TextView registerTV, errorTV;

    Button profileButton;
    Button glossaryButton;
    Button learnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });

        toolbar.inflateMenu(R.menu.toolbar_menu_profile);

        learnButton = (Button) findViewById(R.id.button_learn);
        learnButton.setOnClickListener(this);
        glossaryButton = (Button) findViewById(R.id.button_glossary);
        glossaryButton.setOnClickListener(this);
        profileButton = (Button) findViewById(R.id.button_profile);
        profileButton.setTextColor(ColorStateList.valueOf(Color.BLUE));

        loginBtn = (Button) findViewById(R.id.loginBtn);
        emailET = (EditText) findViewById(R.id.emailET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        registerTV = (TextView) findViewById(R.id.registerTV);
        errorTV = (TextView) findViewById(R.id.errorTV);

        // Add OnClickListeners.
        loginBtn.setOnClickListener(this);
        registerTV.setOnClickListener(this);

    } // end onCreate

    /**
     * Code to be run when a button press occurs.
     * @param v, the view that was clicked.
     */
    @Override
    public void onClick(View v) {

        switch(v.getId()) {

            case R.id.loginBtn: // Login pressed.

                // Get input values.
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                if (email.isEmpty() || password.isEmpty()) Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_LONG).show();
                else if (!validate(email, password)) Toast.makeText(this, "Invalid email or password", Toast.LENGTH_LONG).show();
                else {
                    SharedPreferences.Editor ed = getSharedPreferences("studyonPreferences", MODE_PRIVATE).edit();

                    ed.putBoolean("online", true);
                    ed.commit();
                    startActivity(new Intent(this, ProfileActivity.class)); // Go to Account page.
                    finish();
                }
                break;

            case R.id.registerTV: // Register pressed.
                startActivity(new Intent(this, RegisterPage.class));
                finish();
                break;

            /* Main Menu */
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
     * Checks if a user exists.
     * @param email, the email address being checked.
     * @param password, the password being checked.
     * @return true if the user is found and false otherwise.
     */
    public boolean validate(String email, String password) {

        userData = getSharedPreferences("studyonPreferences", MODE_PRIVATE);
        return email.equals(userData.getString("email", "")) && password.equals(userData.getString("password", ""));

    }

} // end LoginPage
