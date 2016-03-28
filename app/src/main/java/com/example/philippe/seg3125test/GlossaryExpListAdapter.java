package com.example.philippe.seg3125test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Philippe on 2016-03-27.
 */
public class GlossaryExpListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> termHeaderList; // header titles
    private List<String> originalList;
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public GlossaryExpListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this.termHeaderList = listDataHeader;
        this.originalList = new ArrayList<String>(listDataHeader);
        this._listDataChild = listChildData;
    }

    @Override
    public int getGroupCount() {
        return this.termHeaderList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this.termHeaderList.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.termHeaderList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this.termHeaderList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.glossary_results, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.glossary_result_item, null);
        }


        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query){
        query = query.toLowerCase();
        Log.v("ExpListAdapter", String.valueOf(termHeaderList.size()));
        Log.v("OrgListAdapter", String.valueOf(originalList.size()));
        termHeaderList.clear();
        Log.v("ListCleared", String.valueOf(originalList.size()));
        if(query.isEmpty()){
            termHeaderList.addAll(originalList);
            Log.v("Empty query", String.valueOf(termHeaderList.size()));
        }else{
            Log.v("Working", String.valueOf(termHeaderList.size()));
            ArrayList<String> newTermList = new ArrayList<String>();
            Log.v("Passed Array", String.valueOf(originalList.size()));
            for(int i = 0; i < originalList.size(); i++){
                String term = originalList.get(i);
                Log.v("result", String.valueOf(term.toLowerCase().contains(query)));
                Log.v("newListSize", String.valueOf(newTermList.size()));
                if(term.toLowerCase().contains(query)){
                    newTermList.add(term);


                }
            }
            termHeaderList.addAll(newTermList);
        }

        Log.v("ExpListAdapter", String.valueOf(termHeaderList.size()));
        notifyDataSetChanged();
    }
}
