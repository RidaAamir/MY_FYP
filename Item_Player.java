package com.example.emad.splashscreen;

/**
 * Created by HP Pavilion 13 on 3/4/2017.
 */

public class Item_Player {
    public String type;
    public Double cost;
    public String name;

    public Item_Player() {

    }

    public Item_Player(String n_, String t_, double q) {
        name = n_;
        type = t_;
        cost = q;
    }
}
