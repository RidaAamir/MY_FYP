package com.example.emad.splashscreen;

import android.media.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Comparator;

/**
 * Created by HP Pavilion 13 on 3/8/2017.
 */

public class AI_team {

    protected void createAiTeamTest(ArrayList<Player> squadTeam1,ArrayList<Player> squadTeam2 ) {
        int j=0;
        Player p=new Player();
        Player [] globalHonMe= new Player[40];
        for (int i=0;i<40;i++)
        {
            globalHonMe[i]=new Player();
        }


        for(int i=0;i<squadTeam1.size();i++)
        {
            p.copy(globalHonMe[i], squadTeam1.get(i));
        }
        for(int i=squadTeam1.size();i<squadTeam2.size();i++)
        {
            p.copy(globalHonMe[i], squadTeam2.get(j));
            j++;
        }
        ArrayList<Player> arrayList = new ArrayList<Player>(Arrays.asList(globalHonMe));
        sortBatting(arrayList);
        Player [] global = arrayList.toArray(new Player[arrayList.size()]);
        for (int i=0;i<6;i++)
        {
            Display_Squad(global,i);  //batsman
        }




        ArrayList<Player> arrayLi = new ArrayList<Player>(Arrays.asList(globalHonMe));

        sortowling(arrayLi);
        ArrayList<Player> players =remove_Zeros(arrayLi);
        Player[] globalBowler = players.toArray(new Player[players.size()]);
        for (int i=0;i<5;i++)
        {
            Display_Squad(globalBowler,i);
        }
    }

    protected ArrayList<Player> createAiOdiTeam(ArrayList<Player> squadTeam1,ArrayList<Player> squadTeam2 ) {

        System.out.println("###################################################################################### CREATING ODI TEAM");
        int j=0;
        Player p=new Player();

        ArrayList<Player> final_AI=new ArrayList<>();
        int n1=squadTeam1.size();
        int n2=squadTeam2.size();
        int n3=n1+n2;
        Player [] globalHonMe= new Player[n3];
        for (int i=0;i<n3;i++)
        {
            globalHonMe[i]=new Player();
        }

        for(int i=0;i<squadTeam1.size();i++)
        {
            //squadTeam1.get(i).Display_Player();
            p.copy(globalHonMe[i], squadTeam1.get(i));
        }
        for(int i=squadTeam1.size();j<squadTeam2.size();i++)
        {
            //squadTeam2.get(j).Display_Player();
            p.copy(globalHonMe[i], squadTeam2.get(j));
            j++;
        }

        ArrayList<Player> arrayList1 = new ArrayList<Player>(Arrays.asList(globalHonMe));
        Player [] global1 = arrayList1.toArray(new Player[arrayList1.size()]);
        System.out.println("####  POORA SQUAD   ########################################");
        for (int i=0;i<n3;i++)
        {
            Display_Squad(global1,i);
        }


        ArrayList<Player> arrayList = new ArrayList<Player>(Arrays.asList(globalHonMe));
        sortBatting(arrayList);
        Player [] global = arrayList.toArray(new Player[arrayList.size()]);
        System.out.println("MY AI TEAM SO CALLED");
        for (int i=0;i<6;i++)
        {
            Display_Squad(global,i);
            final_AI.add(global[i]);
        }

        ArrayList<Player> arrayLi = new ArrayList<Player>(Arrays.asList(globalHonMe));

        sortowling(arrayLi);

        ArrayList<Player> players =remove_Zeros(arrayLi);
        Player[] globalBowler = players.toArray(new Player[players.size()]);
        for (int i=0;i<5;i++)
        {
            Display_Squad(globalBowler,i);
            final_AI.add(globalBowler[i]);
        }
        return final_AI;
    }


    private ArrayList<Player> remove_Zeros(ArrayList<Player>arrayli){
        int size1=arrayli.size();
        System.out.println("size of the arraylist is: "+size1);

        for(int i=0;i<size1;i++){
            size1=arrayli.size();
            System.out.println("size of the arraylist is: "+size1+"   and the index is  "+i);
            Player p=arrayli.get(i);
            System.out.println("PROCCESSED PLAYER IS  "+p.name);

            if(p.bowlingAvgct==0.0){
                 System.out.println("THIS IS REMOVED: "+p.bowlingAvgct+"   and the INDEX WAS  "+i);
                 arrayli.remove(i);
            }
        }

        return arrayli;
    }

    private void sortowling(ArrayList<Player> arrayLi) {

        Collections.sort(arrayLi, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {

                double avg1=o1.getBowlingAvgct();
                double avg2=o2.getBowlingAvgct();
                return avg1>avg2? 1 : avg1<avg2? -1 : 0;
            }
        });
    }

    private void sortBatting(ArrayList<Player> arrayList) {
        Collections.sort(arrayList, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                double avg1=o1.getBattingAvgct();
                double avg2=o2.getBattingAvgct();
                return avg1<avg2? 1 : avg1>avg2? -1 : 0;
            }
        });

    }

    public void Display_Squad(Player[] P,int i){
        int n1=30;
        //for(int i=0;i<n1;i++){
        //System.out.println(i+" --");
        P[i].Display_Player();//har player ki info print hogii!!
        //}

        //System.out.println("\n");
    }


}
