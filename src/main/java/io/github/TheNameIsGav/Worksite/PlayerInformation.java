package io.github.TheNameIsGav.Worksite;

import java.util.*;

public class PlayerInformation{

    ArrayList<String> jobs = new ArrayList<>(); //"Data." + player.getUniqueId().toString + "." + job1 -- string
    //"Data." + player.getUniqueId().toString + "." + job2 -- string
    ArrayList<Double> xp = new ArrayList<>(); //"Data." + player.getUniqueId().toString + "." + xp1 -- double
    //"Data." + player.getUniqueId().toString + "." + xp2 -- double

    double balance; //"Data." + player.getUniqueId().toString + "." + bal -- double

    PlayerInformation(){}

    PlayerInformation(String j1, String j2, Double e1, Double e2, Double bal){
        jobs.add(j1);
        jobs.add(j2);
        xp.add(e1);
        xp.add(e2);
        balance = bal;
    }

    @Override
    public String toString(){
        return "Jobs: " + jobs.toString() + " Balance: " + balance;
    }

}
