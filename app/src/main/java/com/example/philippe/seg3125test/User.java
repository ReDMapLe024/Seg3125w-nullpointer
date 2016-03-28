/**
 * Object for storing user data.
 *
 * A user has an email address and a password, as well as integer values for progress in each subject.
 */

package com.example.philippe.seg3125test;


public class User {

    private String email, password;
    private int math, science, economics, finance, computers;
    private boolean loggedIn;

    /* Constructors */

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.math = 0;
        this.science = 0;
        this.economics = 0;
        this.finance = 0;
        this.computers = 0;
    }

    public User(String email, String password, int math, int science, int economics, int finance, int computers) {
        this.email = email;
        this.password = password;
        this.math = math;
        this.science = science;
        this.economics = economics;
        this.finance = finance;
        this.computers = computers;
    }

    /* Getters and Setters */

    public boolean getLoggedIn() { return this.loggedIn; }
    public void setLoggedIn(boolean loggedin) { this.loggedIn = loggedin; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getMath() { return math; }
    public void setMath(int math) { this.math = math; }

    public int getScience() { return science; }
    public void setScience(int science) { this.science = science; }

    public int getEconomics() { return economics; }
    public void setEconomics(int economics) { this.economics = economics; }

    public int getFinance() { return finance; }
    public void setFinance(int finance) { this.finance = finance; }

    public int getComputers() { return computers; }
    public void setComputers(int computers) { this.finance = computers; }

}
