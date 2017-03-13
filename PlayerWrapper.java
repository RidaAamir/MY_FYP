package com.example.emad.splashscreen;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by HP Pavilion 13 on 3/12/2017.
 */

public class PlayerWrapper implements Serializable {
    public ArrayList<Player> P=new ArrayList<>();

    PlayerWrapper(ArrayList<Player> P){

        this.P=P;
//        P.get(0).Display_Player();
    }

    public ArrayList<Player> getPlayerArray() {
        return this.P;
    }


}
