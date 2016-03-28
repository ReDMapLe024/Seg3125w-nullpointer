/**
 * Database helper class for db operations.
 */

package com.example.philippe.seg3125test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    /* Constants */

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "studyon.db";
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_MATH = "math";
    public static final String COLUMN_SCIENCE = "science";
    public static final String COLUMN_ECONOMICS = "economics";
    public static final String COLUMN_FINANCE = "finance";
    public static final String COLUMN_COMPUTERS = "computers";

    SQLiteDatabase db;

    // Users table.
    public static final String CREATE_TABLE = "CREATE TABLE users (id INTEGER PRIMARY KEY NOT NULL, " +
            "email TEXT, password TEXT, math INTEGER(3), science INTEGER(3), economics INTEGER(3), finance INTEGER(3), computers INTEGER(3))";

    // Constructor.
    public DBHelper(Context c) {
        super(c, DB_NAME, null, DB_VERSION);
    }

    /* Getters and Setters */

    public int getMath(String user) {

        db = this.getReadableDatabase();
        int math = 0;

        String query = "SELECT "+COLUMN_MATH+" FROM "+TABLE_NAME+" WHERE "+COLUMN_EMAIL+" = '"+user+"'";
        Cursor cursor = db.rawQuery(query, null);

        int id = cursor.getColumnIndex("math");

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            math = cursor.getInt(id);
        }

        cursor.close();
        db.close();

        return math;

    }

    public int getScience(String user) {

        db = this.getReadableDatabase();
        int science = 0;

        String query = "SELECT "+COLUMN_SCIENCE+" FROM "+TABLE_NAME+" WHERE "+COLUMN_EMAIL+" = '"+user+"'";
        Cursor cursor = db.rawQuery(query, null);

        int id = cursor.getColumnIndex("science");

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            science = cursor.getInt(id);
        }

        cursor.close();
        db.close();

        return science;

    }

    public int getEconomics(String user) {

        db = this.getReadableDatabase();
        int economics = 0;

        String query = "SELECT "+COLUMN_ECONOMICS+" FROM "+TABLE_NAME+" WHERE "+COLUMN_EMAIL+" = '"+user+"'";
        Cursor cursor = db.rawQuery(query, null);

        int id = cursor.getColumnIndex("economics");

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            economics = cursor.getInt(id);
        }

        cursor.close();
        db.close();

        return economics;

    }

    public int getFinance(String user) {

        db = this.getReadableDatabase();
        int finance = 0;

        String query = "SELECT "+COLUMN_FINANCE+" FROM "+TABLE_NAME+" WHERE "+COLUMN_EMAIL+" = '"+user+"'";
        Cursor cursor = db.rawQuery(query, null);

        int id = cursor.getColumnIndex("finance");

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            finance = cursor.getInt(id);
        }

        cursor.close();
        db.close();

        return finance;

    }

    public int getComputers(String user) {

        db = this.getReadableDatabase();
        int computers = 0;

        String query = "SELECT "+COLUMN_COMPUTERS+" FROM "+TABLE_NAME+" WHERE "+COLUMN_EMAIL+" = '"+user+"'";
        Cursor cursor = db.rawQuery(query, null);

        int id = cursor.getColumnIndex("computers");

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            computers = cursor.getInt(id);
        }

        cursor.close();
        db.close();

        return computers;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    /**
     * Insert a new user into the database.
     * @param u, the user to be inserted.
     */
    public void insertUser(User u) {

        db = this.getWritableDatabase();
        ContentValues userInfo = new ContentValues();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        int count = cursor.getCount(); // Get primary key for this user.

        userInfo.put(COLUMN_ID, count);
        userInfo.put(COLUMN_EMAIL, u.getEmail());
        userInfo.put(COLUMN_PASSWORD, u.getPassword());
        userInfo.put(COLUMN_MATH, u.getMath());
        userInfo.put(COLUMN_SCIENCE, u.getScience());
        userInfo.put(COLUMN_ECONOMICS, u.getEconomics());
        userInfo.put(COLUMN_FINANCE, u.getFinance());
        userInfo.put(COLUMN_COMPUTERS, u.getComputers());

        db.insert(TABLE_NAME, null, userInfo);

        cursor.close();
        db.close();

    } // end insertUser

    /**
     * Search for a user in the database.
     * @param password, the password associated with the user.
     * @return the user (if found).
     */
    public String search(String password) {

        db = this.getReadableDatabase();

        String query = "SELECT "+COLUMN_EMAIL+", "+COLUMN_PASSWORD+" FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String a;
        String b = "not found";

        if (cursor.moveToFirst()) {

            do {

                a = cursor.getString(0);

                if (a.equals(password)) {

                    b = cursor.getString(1); // User is found.
                    break;

                }

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return b;

    } // end search

    /**
     * Update a user's parameter.
     * @param user, the user being updated.
     * @param parameter, the parameter being updated.
     */
    public void updateUser(String user, String parameter) {

        db = this.getWritableDatabase();

        String query = "UPDATE "+TABLE_NAME+" SET "+parameter+" = "+parameter+" + 50 WHERE "+COLUMN_EMAIL+" = '"+user+"'";
        db.rawQuery(query, null);

        db.close();

    }

} // end DBHelper
