package com.example.emad.splashscreen;

import java.io.Serializable;

/**
 * Created by عماد شیخ on 11/16/2016.
 */

public class Player implements Serializable {
    public String type;
    public Double cost;
    public String name;
    public int points;
    public int runs;
    public Double SR;
    public int wickets;
    public int catches;
    public Double economy;
    public Double overs_;
    public int runout;
    public int stumping;
    public int balls;
    public int maidens_;
    public double battingAvgct;
    public double battingAvgco;
    public double bowlingAvgct;

    public int getCatches() {
        return catches;
    }
    public void setCatches(int catches) {
        this.catches = catches;
    }
    public double getBattingAvgct() {
        return battingAvgct;
    }
    public void setBattingAvgct(double battingAvgct) {
        this.battingAvgct = battingAvgct;
    }
    public double getBattingAvgco() {
        return battingAvgco;
    }
    public void setBattingAvgco(double battingAvgco) {
        this.battingAvgco = battingAvgco;
    }
    public double getBowlingAvgco() {

        return bowlingAvgco;

    }
    public void setBowlingAvgco(double bowlingAvgco) {
        this.bowlingAvgco = bowlingAvgco;
    }

    public double getBowlingAvgct() {
        return bowlingAvgct;
    }
    public void setBowlingAvgct(double bowlingAvgct) {
        this.bowlingAvgct = bowlingAvgct;
    }
    public double bowlingAvgco;
    Player(){
        balls=-1;
        name=" ";
        cost=0.00;
        type= "";
        points=0;
        runs=0;
        SR=0.00;
        wickets=0;
        catches=0;
        economy=0.00;
        overs_=0.00;
        runout=0;
        stumping=0;
        maidens_=0;
    }
    public void setName(String name_)
    {
        name=name_;
    }
    void setPoints(int points_){
        points=points_;

    }


    void setcost(String name_,Double[]batAVG,Double[]bowlAVG){
        Double bat_avg=0.0;
        Double bowl_avg=0.0;
        for(int i=0;i<2;i++){
            bat_avg=bat_avg+batAVG[i];
            //System.out.println("bat_avg--- "+bat_avg);
            bowl_avg=bowl_avg+bowlAVG[i];
            //System.out.println("bowl_avg--- "+bowl_avg);
        }
        //TAKING AVERAGES
        bat_avg=bat_avg/2;
        bowl_avg=bowl_avg/2;

        //System.out.println("Final Batting average : "+bat_avg+"\n Final bowling average  :  "+bowl_avg);
        //SETTING COST

        if(bowl_avg>0 &&bat_avg>=50.0 && bowl_avg>45 && bowl_avg<15){		//BAtting
            cost=10.0;
            //type="batter";
        }
        else if(bowl_avg>0 &&bat_avg<50.0 && bat_avg>=48.0 && bowl_avg>45 ){
            cost=9.0;
            //type="batter"
            ;}
        else if(bowl_avg>0 &&bat_avg>=44.0 && bat_avg<48.0 && bowl_avg>45 && bowl_avg<15){
            cost=8.0;
            //type="batter";
        }
        else if(bowl_avg>0 &&bat_avg>=40.0 && bat_avg<44.0 && bowl_avg>45 && bowl_avg<15){
            cost=7.5;
            //type="batter";
        }
        else if(bowl_avg>0 &&bat_avg<44.0 && bat_avg>=35.0 && bowl_avg>45 && bowl_avg<15){
            cost=7.0;
            //type="batter";
        }
        else if(bowl_avg>0 &&bat_avg<40.0 && bat_avg>=28.0 && bowl_avg<40 ){
            cost=8.0;
            //type="alrounder";
        }
        else if(bowl_avg>0 &&bat_avg<40.0 && bat_avg>=35.0 && bowl_avg<40){
            cost=7.0;
            //type="alrounder";
        }
        else if (bowl_avg>0 &&bat_avg>25 && bat_avg<31 && bowl_avg<15 )
        {
            cost=6.0;
            //type="batter";
        }
        else {
            cost=6.5;
            //type="alrounder";
        }
//-----------------------------------
        if(bowl_avg>=40.0 && bat_avg<13.0){		//BOWLING
            cost=6.0;
        }
        else if(bowl_avg>0 &&bowl_avg<30.0 && bowl_avg>=28.0 && bat_avg<20.0 ){
            cost=7.0;
            //type="bowler";
        }
        else if(bowl_avg>0 &&bowl_avg>=26.0 && bowl_avg<28.0 && bat_avg<25.0 ){
            cost=8.0;
            //type="bowler";
        }
        else if(bowl_avg>0 &&bowl_avg<32.0 && bowl_avg>26.0 && bat_avg<20.0 && bat_avg>15 ){
            cost=7.5;
            //type="bowler";
        }
        else if(bowl_avg>0 &&bowl_avg<33.0 && bowl_avg>27.0 && bat_avg<15.0 ){
            cost=7.5;
            //type="bowler";
        }
        else if(bowl_avg>0 &&bowl_avg<30 && bat_avg>15.0 ){
            cost=8.5;
            //type="bowler";
        }
        else if(bowl_avg>0 && bowl_avg>25.0 && bowl_avg<=27.0 && bat_avg<35.0 && bat_avg>20){
            cost=9.0;
            //type="alrounder";
        }

        else if(bowl_avg>0 &&bowl_avg<25.0 && bowl_avg>=24.0 && bat_avg<35.0 && bat_avg>20){
            cost=10.0;
            //type="alrounder";
        }
        else if(bowl_avg>0 &&bowl_avg>=30.0 && bowl_avg<55.0 && bat_avg>28.0 && bat_avg<35 ){
            cost=8.0;
            //type="alrounder";
        }
        else if (bat_avg>35){
            cost=8.5;
            //type="batter";
        }
    }



    String getType()
    {
        return type;
    }


    public void Display_AI_Player(){
        if(bowlingAvgct>0)
        {
            System.out.println("Player Name:  "+name);
            //System.out.println("Player Cost:  "+cost);
            //System.out.println("Player Type:  "+type);
	/*System.out.println("Player runs:  "+runs);
	System.out.println("Player wickets:  "+wickets);
	System.out.println("Player Catches:  "+catches);
	System.out.println("Player Economy:  "+economy);
	System.out.println("Player SR:  "+SR);*/

            //System.out.println("Player points:  "+points);
            //System.out.println("Player Test batting Cumalative avg:  "+battingAvgct);
            //System.out.println("Player test bowling Cumalative avg:  "+bowlingAvgct);
            System.out.println(" ------  ");

        }
    }

    public void Display_Player(){

        System.out.println("Player Name:  "+name);
        System.out.println("Player Cost:  "+cost);
        System.out.println("Player Type:  "+type);
	/*System.out.println("Player runs:  "+runs);
	System.out.println("Player wickets:  "+wickets);
	System.out.println("Player Catches:  "+catches);
	System.out.println("Player Economy:  "+economy);
	System.out.println("Player SR:  "+SR);*/

        System.out.println("Player points:  "+points);
        System.out.println(" ------  ");


    }


    //cost=cost_;

    double printCost()
    {
        return cost;
    }
    String printType()
    {
        return type;
    }
    public int showpoints() {

        return points;
    }
    public void setInfo(int i, int j, Double k, int l, int m, Double n, int o) {
        name="";
        points=i;
        runs=j;
        SR=k;
        wickets=l;
        catches=m;
        economy=n;
    }
    public void setType(String string) {
        type=string;
    }


    public void copy(Player p,Player q){
        p.name=q.name;

        p.battingAvgct=q.battingAvgct;
        p.battingAvgco=q.battingAvgco;
        p.bowlingAvgco=q.bowlingAvgco;
        p.bowlingAvgct=q.bowlingAvgct;

    }
}