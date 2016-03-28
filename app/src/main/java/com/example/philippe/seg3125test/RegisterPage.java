package com.example.philippe.seg3125test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterPage extends AppCompatActivity implements View.OnClickListener {

    Button registerBtn;
    EditText emailET, passwordET, passwordConfirmET;
    TextView errorTV;

    Button profileButton;
    Button glossaryButton;
    Button learnButton;

    SharedPreferences userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar tab_toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        tab_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
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

        userData = getSharedPreferences("studyonPreferences", MODE_PRIVATE);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        emailET = (EditText) findViewById(R.id.emailET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        passwordConfirmET = (EditText) findViewById(R.id.passwordConfirmET);
        errorTV = (TextView) findViewById(R.id.errorTV);

        // Add OnClickListeners.
        registerBtn.setOnClickListener(this);

    } // end onCreate

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to the action bar's BACK button.
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;


        }

        return super.onOptionsItemSelected(item);

    } // end onOptionsItemSelected

    /**
     * Code to be run when a button press occurs.
     * @param v, the view that was clicked.
     */
    @Override
    public void onClick(View v) {

        switch(v.getId()) {

            case R.id.registerBtn: // If register is clicked.

                // Get input values.
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                String passwordConfirm = passwordConfirmET.getText().toString();

                // Empty email or password.
                if (email.isEmpty() || password.isEmpty()) Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_LONG).show();

                    // If input is invalid.
                else if (!isValid(email, password, passwordConfirm)) {

                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_LONG).show();

                }else if (email.equals(userData.getString("email",""))) {

                    Toast.makeText(this, "Email already in use", Toast.LENGTH_LONG).show();

                } else { // Valid input, insert user into database and go to account page.

                    User newUser = new User(email, password);
                    newUser.setLoggedIn(true);

                    // Add the user object to shared prefs.
                    SharedPreferences.Editor ed = getSharedPreferences("studyonPreferences", MODE_PRIVATE).edit();

                    ed.putBoolean("online", newUser.getLoggedIn());
                    ed.putInt("MathQuiz1", 0);
                    ed.putInt("MathQuiz2", 0);
                    ed.putString("email", newUser.getEmail());
                    ed.putString("password", newUser.getPassword());
                    ed.putInt("math", newUser.getMath());
                    ed.putInt("science", newUser.getScience());
                    ed.putInt("economics", newUser.getEconomics());
                    ed.putInt("finance", newUser.getFinance());
                    ed.putInt("computers", newUser.getComputers());
                    ed.commit();

                    Toast.makeText(this, "User created", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(this, ProfileActivity.class)); // Go to Account page.
                    finish();

                }
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
     * Checks whether registration input is valid or not.
     * @param email, the email address being checked.
     * @param password, the password entered by the user.
     * @param passwordConfirm, the password copy re-entered by the user.
     * @return true if the input is valid and false otherwise.
     */
    public boolean isValid(String email, String password, String passwordConfirm) {

        boolean valid = true;

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) valid = false; // Invalid email format.
        else if (!password.equals(passwordConfirm)) valid = false; // Password and password confirmation do not match.

        return valid;

    } // end isValid

} // end Register
