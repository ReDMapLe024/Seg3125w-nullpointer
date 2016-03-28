package com.example.philippe.seg3125test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GlossaryActivity extends Activity implements View.OnClickListener{

    private Button profileButton;
    private Button glossaryButton;
    private Button learnButton;
    SharedPreferences userData;

    private SearchView sview;

    GlossaryExpListAdapter listAdapter;
    ExpandableListView lview;
    List<String> termListHeader;
    HashMap<String, List<String>> termListChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glossary);

        userData = getSharedPreferences("studyonPreferences", MODE_PRIVATE);
        profileButton = (Button) findViewById(R.id.button_profile);
        profileButton.setOnClickListener(this);
        glossaryButton = (Button) findViewById(R.id.button_glossary);
        glossaryButton.setOnClickListener(this);
        glossaryButton.setTextColor(ColorStateList.valueOf(Color.BLUE));
        learnButton = (Button) findViewById(R.id.button_learn);
        learnButton.setOnClickListener(this);

        lview = (ExpandableListView) findViewById(R.id.result_list);
        sview = (SearchView) findViewById(R.id.bar_search);
        sview.setIconifiedByDefault(false);
        prepareListData();
        listAdapter = new GlossaryExpListAdapter(this, termListHeader, termListChild);
        lview.setAdapter(listAdapter);
        sview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                listAdapter.filterData(query);

                return false;
            }
        });
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
            case R.id.button_learn:
                Intent intentl = new Intent(this, MainActivity.class);
                startActivity(intentl);
                overridePendingTransition(0, 0);
                break;
        }

    }

    private void prepareListData() {
        termListHeader = new ArrayList<String>();
        termListChild = new HashMap<String, List<String>>();

        // Adding child data
        termListHeader.add("Independant variable");
        termListHeader.add("Function");
        termListHeader.add("Linear function");
        //termListHeader.add("Functions");
        //termListHeader.add("Linear equations");
        //termListHeader.add("Sequences");
        //termListHeader.add("Systems");

        // Adding child data
        List<String> introduction = new ArrayList<String>();
        introduction.add("This is the definition of a independant variable. This is more sample text");


        List<String> oVarEquations = new ArrayList<String>();
        oVarEquations.add("This is the definition of a Function. This is more sample text");


        List<String> twoVarEquarions = new ArrayList<String>();
        twoVarEquarions.add("This is the definition of a Linear function. This is more sample text");


        termListChild.put(termListHeader.get(0), introduction); // Header, Child data
        termListChild.put(termListHeader.get(1), oVarEquations);
        termListChild.put(termListHeader.get(2), twoVarEquarions);

    }
}