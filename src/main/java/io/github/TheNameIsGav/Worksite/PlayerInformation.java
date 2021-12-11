package io.github.TheNameIsGav.Worksite;

import java.util.*;

public class PlayerInformation{

    ArrayList<String> jobs = new ArrayList<>();
    ArrayList<Double> xp = new ArrayList<>();
    double balance;
    double mult = 1;

    PlayerInformation(){}

    PlayerInformation(String j1, String j2, Double e1, Double e2, Double bal){
        jobs.add(j1);
        jobs.add(j2);
        xp.add(e1);
        xp.add(e2);
        balance = bal;
    }

    @Override
    public String toString(){ return "Jobs: " + jobs.toString() + " Balance: " + balance; }
}
