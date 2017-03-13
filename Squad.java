package com.example.emad.splashscreen;

import android.os.AsyncTask;
import android.os.SystemClock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by HP Pavilion 13 on 2/27/2017.
 */

public class Squad {

    squad_calculation h= new squad_calculation();
    int index=0;
    public static ArrayList<Player> squad_team1;
    String team1;
    public static ArrayList<Player> squad_team2;
    public static ArrayList<Player> AI_team=new ArrayList<Player>();
    String team2;
    public int series_id;


    public Squad(){

        /*for(int i=0;i<18;i++){
            squad_team1[i]=new Player();
            squad_team2[i]=new Player();
        }*/
        //squad_team2=null;
    }


    public ArrayList<ArrayList<Player>> get_squad(final JSONObject team1_, final JSONObject team2_) throws JSONException,NullPointerException{
        team1=team1_.getString("name");
        team2=team2_.getString("name");

        //CheckTeam("Pakistan 1st Innings");
        // Country names----------------

        squad_team1=new ArrayList<>();
        squad_team2=new ArrayList<>();


        //Putting Players in Array---------------
        final JSONArray players1=team1_.getJSONArray("players");
        final JSONArray players2=team2_.getJSONArray("players");
        //System.out.println(players1);
        //System.out.println(players2);

        int n1=players1.length();
        int n2=players2.length();


        System.out.println(team1+"-------------   "+n1+"\n");

        for(int i=0;i<n1;i++) {    //Team1 k players initiate horahe hen
            final JSONObject player = players1.getJSONObject(i);
            Player p= new Player();
            String id = player.getString("pid");
            //p.ID=id;
            //CHANGE THAT I EXECUTED ##CHHHHHH
            try {
                String ss=new JSONTask12().execute("http://cricapi.com/api/playerStats?pid=" + id + "&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2").get();
                //System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss  "+i+"   "+ss);

                Double[] BattingAvg = new Double[3];
                Double[] BowlingAvg = new Double[3];
                String[] batAverages = new String[3];
                String[] bowlAverages = new String[3];
                try {
                    final JSONObject obj1 = new JSONObject(ss);
                  //  System.out.println("OBJ1_____>" + obj1 + "\n\n");
                    final JSONObject data1 = obj1.getJSONObject("data");
                   // System.out.println("DATA_____>" + data1 + "\n\n");
                    if (!obj1.isNull("data")) {
                        final JSONObject data = obj1.getJSONObject("data");
                        //System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX "+index+"    "+data);
                        if (obj1.has("playingRole")) {
                           // System.out.println(obj1.get("playingRole"));
                            p.setType(obj1.getString("playingRole"));
                        } else {
                            p.setType("TBD");
                        }
                        // GETTING Batting AVERAGES
                        final JSONObject batting = data.getJSONObject("batting");
                        if (!batting.isNull("listA")) {
                            final JSONObject ListA = batting.getJSONObject("listA");
                            batAverages[1] = (String) ListA.get("Ave");
                        } else
                            batAverages[1] = "0";
                        if (!batting.isNull("firstClass")) {
                            final JSONObject FC = batting.getJSONObject("firstClass");
                            batAverages[0] = (String) FC.get("Ave");
                        } else
                            batAverages[0] = "0";
                        if (!batting.isNull("tests")) {
                            final JSONObject tests = batting.getJSONObject("tests");
                        } else
                            batAverages[2] = "0";
                        // GETTING BOWLING AVERAGE NOW
                        final JSONObject bowling = data.getJSONObject("bowling");

                        // SOME INPUT HAVE "-" instead of numbers...
                        // batAverages[0]=(String) FC.get("Ave");
                        // batAverages[1]=(String) ListA.get("Ave");
                        // batAverages[2]=(String) tests.get("Ave");
                        if (!bowling.isNull("listA")) {
                            final JSONObject ListA = bowling.getJSONObject("listA");
                            bowlAverages[1] = (String) ListA.get("Ave");
                        } else
                            bowlAverages[1] = "0";
                        if (!bowling.isNull("firstClass")) {
                            final JSONObject FC = bowling.getJSONObject("firstClass");
                            bowlAverages[0] = (String) FC.get("Ave");
                        } else
                            bowlAverages[0] = "0";
                        if (!bowling.isNull("tests")) {
                            final JSONObject tests = bowling.getJSONObject("tests");
                        } else
                            bowlAverages[2] = "0";
                        // bowlAverages[2]= (String) tests_.get("Ave");

                        for (int k = 0; k < 2; k++) {
                            // System.out.println(batAverages[i]);
                            if (batAverages[k].equals("-")) {
                                BattingAvg[k] = 0.0;
                            } else {
                                BattingAvg[k] = (Double.parseDouble(batAverages[k]));
                            }
                        }

                        // System.out.println(a+"DDDDDDDDDDDDDDDDDDD");
                        for (int k = 0; k < 2; k++) {
                            // System.out.println(bowlAverages[i]);
                            if (bowlAverages[k].equals("-")) {
                                // System.out.println("DDDDDDDDDDDDDDDDDDD");
                                BowlingAvg[k] = 0.0;
                            } else {
                                BowlingAvg[k] = (Double.parseDouble(bowlAverages[k]));
                            }
                        }
                        // System.out.println("\n ----------- Checking--\n");
                        p.setcost("", BattingAvg, BowlingAvg);
                    }

                    // double a=p.printCost();
                    // p.Display_Player();

                    //squad_team1.get(i).setName(player.getString("name"));
                    //p= squad_team1.get(i);
                    p.name = obj1.getString("name");
                    // p.ID=obj1.getString("pid");

                   // p.Display_Player();
                    //players k cost or type
                    // p = b.parseStats(obj1, p);
                    squad_team1.add(p);
                    //System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY   "+index);
                    index++;
                    //String a=squad_team1[i].getType();
                    //if (a.equals("batter"))
                    //System.out.println(i+" Batters : "+squad_team1[i].name);

                }
                catch(JSONException e){}


            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }



        index=0;
        //int n2=players2.length();
        System.out.println(team2+"-------------  "+n2+"\n");
        for(int i=0;i<n2;i++) {    //Team1 k players initiate horahe hen
            final JSONObject player = players2.getJSONObject(i);
            Player p= new Player();
            String id = player.getString("pid");
            //p.ID=id;
             //CHANGE THAT I EXECUTED ##CHHHHHH
            try {
                String jj=new JSONTask1().execute("http://cricapi.com/api/playerStats?pid=" + id + "&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2").get();
                //System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj  "+jj);


                Double[] BattingAvg = new Double[3];
                Double[] BowlingAvg = new Double[3];
                String[] batAverages = new String[3];
                String[] bowlAverages = new String[3];
                try {
                    System.out.println("");
                    final JSONObject obj1 = new JSONObject(jj);
                    //System.out.println("OBJ1_____>" + obj1 + "\n\n");
                    final JSONObject data1 = obj1.getJSONObject("data");
                    //System.out.println("DATA_____>" + data1 + "\n\n");
                    if (!obj1.isNull("data")) {
                        final JSONObject data = obj1.getJSONObject("data");
                        //System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD   "+data);
                        if (obj1.has("playingRole")) {
                            System.out.println(obj1.get("playingRole"));
                            p.setType(obj1.getString("playingRole"));
                        } else {
                            p.setType("TBD");
                        }
                        // GETTING Batting AVERAGES
                        final JSONObject batting = data.getJSONObject("batting");
                        if (!batting.isNull("listA")) {
                            final JSONObject ListA = batting.getJSONObject("listA");
                            batAverages[1] = (String) ListA.get("Ave");
                        } else
                            batAverages[1] = "0";
                        if (!batting.isNull("firstClass")) {
                            final JSONObject FC = batting.getJSONObject("firstClass");
                            batAverages[0] = (String) FC.get("Ave");
                        } else
                            batAverages[0] = "0";
                        if (!batting.isNull("tests")) {
                            final JSONObject tests = batting.getJSONObject("tests");
                        } else
                            batAverages[2] = "0";
                        // GETTING BOWLING AVERAGE NOW
                        final JSONObject bowling = data.getJSONObject("bowling");

                        // SOME INPUT HAVE "-" instead of numbers...
                        // batAverages[0]=(String) FC.get("Ave");
                        // batAverages[1]=(String) ListA.get("Ave");
                        // batAverages[2]=(String) tests.get("Ave");
                        if (!bowling.isNull("listA")) {
                            final JSONObject ListA = bowling.getJSONObject("listA");
                            bowlAverages[1] = (String) ListA.get("Ave");
                        } else
                            bowlAverages[1] = "0";
                        if (!bowling.isNull("firstClass")) {
                            final JSONObject FC = bowling.getJSONObject("firstClass");
                            bowlAverages[0] = (String) FC.get("Ave");
                        } else
                            bowlAverages[0] = "0";
                        if (!bowling.isNull("tests")) {
                            final JSONObject tests = bowling.getJSONObject("tests");
                        } else
                            bowlAverages[2] = "0";
                        // bowlAverages[2]= (String) tests_.get("Ave");

                        for (int k = 0; k < 2; k++) {
                            // System.out.println(batAverages[i]);
                            if (batAverages[k].equals("-")) {
                                BattingAvg[k] = 0.0;
                            } else {
                                BattingAvg[k] = (Double.parseDouble(batAverages[k]));
                            }
                        }

                        // System.out.println(a+"DDDDDDDDDDDDDDDDDDD");
                        for (int k = 0; k < 2; k++) {
                            // System.out.println(bowlAverages[i]);
                            if (bowlAverages[k].equals("-")) {
                                // System.out.println("DDDDDDDDDDDDDDDDDDD");
                                BowlingAvg[k] = 0.0;
                            } else {
                                BowlingAvg[k] = (Double.parseDouble(bowlAverages[k]));
                            }
                        }
                        // System.out.println("\n ----------- Checking--\n");
                        p.setcost("", BattingAvg, BowlingAvg);

                    }

                    // double a=p.printCost();
                    // p.Display_Player();

                    //squad_team1.get(i).setName(player.getString("name"));
                    //p= squad_team1.get(i);
                    p.name = obj1.getString("name");
                    // p.ID=obj1.getString("pid");

                   // p.Display_Player();
                    //players k cost or type
                    // p = b.parseStats(obj1, p);
                    squad_team2.add(p);
                    //@2nd
                   // System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM  "+index);
                    index++;
                    //String a=squad_team1[i].getType();
                    //if (a.equals("batter"))
                    //System.out.println(i+" Batters : "+squad_team1[i].name);
                }
                catch(JSONException e){}
                // delegate.processFinish(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        }
        //SystemClock.sleep(500);


        System.out.println("----------------------------------------------------------------------SQUAD TEAM 1");
        Display_Squad(squad_team1);//displaying the squad
        System.out.println("----------------------------------------------------------------------SQUAD TEAM 2");

        Display_Squad(squad_team2);


   ArrayList<ArrayList<Player>> returnObj=new ArrayList<>(2);
        returnObj.add(squad_team1);
        returnObj.add(squad_team2);
        //Adding and Returning the returnObj


    return returnObj;
    }


    public void Display_Squad(ArrayList<Player> P){
        int n1=P.size();
        for(int i=0;i<n1;i++){
            System.out.println(i+" --");
            Player pp=P.get(i);
            pp.Display_Player();//har player ki info print hogii!!
        }

        System.out.println("\n\n\n");
    }

    public Boolean CheckTeam(String str){
        System.out.println("Team Playing innings:  "+ str);
        System.out.println("Name of Squad:  "+ team1);

        if(str.contains(team1)){
            //System.out.println(team1+" - returning false \n\n");
            return false;
        }
        else{
            //System.out.println(team2+" -   returning true \n\n");
            return true;
        }


    }

    public ArrayList<Player> return_squad(Boolean t){
        if(t==true)
            return squad_team2;
        else {
            System.out.println("england aagaya");
            //Display_Squad(squad_team1);
            return squad_team1;
        }
    }
    public void set_squad(Player[]P, Boolean B){
        if(B==true){
           // squad_team2.set(P);
            //System.out.println("!!!!!!!!!!!!!!!!!!!!INDIAAAA!!!!!!!!!!!!!!!!!");
            //Display_Squad(squad_team2);
        }
        else{
           // squad_team1=P;
            //System.out.println("this is england");
            //Display_Squad(squad_team1);

        }

    }



    public class JSONTask12 extends AsyncTask<String, String, String> {


        protected void onPostExecute(String s) {
       // public Player parseStats(String s, Player p) throws JSONException {
            super.onPostExecute(s);
        }


        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                System.out.println("\n"+buffer.toString()+"   LOOOOOOOOOLLLLLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                return buffer.toString();


            } catch (MalformedURLException e) {
                System.out.println("GAYA INTERNET1\n");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("GAYA INTERNET2\n");
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    System.out.println("GAYA INTERNET3\n");
                    e.printStackTrace();
                }
            }
            return null;
        }

    }



    public class JSONTask1 extends AsyncTask<String, String, String> {


        protected void onPostExecute(String s) {
       // public Player parseStats(String s, Player p) throws JSONException {
            super.onPostExecute(s);
        }



        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                //System.out.println("\n"+buffer.toString()+"   LOOOOOOOOOLLLLLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                return buffer.toString();


            } catch (MalformedURLException e) {
                System.out.println("GAYA INTERNET1\n");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("GAYA INTERNET2\n");
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    System.out.println("GAYA INTERNET3\n");
                    e.printStackTrace();
                }
            }
            return null;
        }

    }




    public ArrayList<ArrayList<Player>> get_AI_squad(final JSONObject team1_, final JSONObject team2_) throws JSONException,NullPointerException{
        Double[] battingAvgTest=new Double[2] ,battingAvgOdi=new Double[2];
        //double battingAvgct=0.00;
        //double battingAvgco=0.00;
        Double[] bowlingAvgTest=new Double[2],bowlingAvgOdi=new Double[2],bowlingAvgc=new Double[2];
        String[] batAvgTest=new String[2],batAvgOdi=new String[2],batAvgT20=new String[2];
        String[] bowlAvgTest=new String[2],bowlAvgOdi=new String[2],bowlAvgT20=new String[2];
        String innL,innO,innT,innF,wktsO="-",wktsF="-",wktsT="-",wktsA="-";
        Double wktOdi=0.00,wktTest=0.00,wktFC=0.00,wktListA=0.00;

        Double innTest=0.00,innOdi=0.00,innListA=0.00;
        Double innFC =0.00;

        team1=team1_.getString("name");
        team2=team2_.getString("name");

        squad_team1=new ArrayList<>();
        squad_team2=new ArrayList<>();

        //CheckTeam("Pakistan 1st Innings");
        // Country names----------------
        //Putting Players in Array---------------
        final JSONArray players1=team1_.getJSONArray("players");
        final JSONArray players2=team2_.getJSONArray("players");
        //System.out.println(players1);
        //System.out.println(players2);

        int n1=players1.length();
        int n2=players2.length();


        System.out.println(team1+"-------------   "+n1+"\n");

        for(int j=0;j<n1;j++) {    //Team1 k players initiate horahe hen
            final JSONObject player = players1.getJSONObject(j);
            Player p= new Player();
            String id = player.getString("pid");
            String name=player.getString("name");


            //p.ID=id;
            p.name=name;
            //CHANGE THAT I EXECUTED ##CHHHHHH
            try {
                String ss = new JSONTask12().execute("http://cricapi.com/api/playerStats?pid=" + id + "&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2").get();
                final JSONObject obj1 = new JSONObject(ss);
        //  System.out.println(obj1);
        if(!obj1.isNull("data")){
            final JSONObject data= obj1.getJSONObject("data");
		/*	  if(obj1.has("playingRole")) {
			    	 p.setType(obj1.getString("playingRole")); ;
			    	}
			     else {
			    	 p.setType("TBD");
			     }*/
            //  System.out.println(data);
            // GETTING Batting AVERAGES
            final JSONObject batting= data.getJSONObject("batting");
            if(!batting.isNull("listA")){
                final JSONObject ListA= batting.getJSONObject("listA");
                batAvgOdi[0]=(String) ListA.get("Ave");
                innL=(String) ListA.get("Inns");}
            else
            {batAvgOdi[0]="0";
                innL="0";
            }
            if(!batting.isNull("ODIs")){
                final JSONObject ODI= batting.getJSONObject("ODIs");
                batAvgOdi[1]=(String) ODI.get("Ave");
                innO=(String) ODI.get("Inns");}
            else
            { batAvgOdi[1]="0";
                innO="0";}
            if(!batting.isNull("firstClass")){
                final JSONObject FC= batting.getJSONObject("firstClass");
                batAvgTest[0]=(String) FC.get("Ave");
                innF=(String) FC.get("Inns");}
            else
            {batAvgTest[0]="0";
                innF="0";}
            if(!batting.isNull("tests")){
                final JSONObject tests= batting.getJSONObject("tests");
                batAvgTest[1]=(String) tests.get("Ave");
                innT= (String) tests.get("Inns");}
            else
            {batAvgTest[1]="0";
                innT="0";}
            //GETTING BOWLING AVERAGE NOW
            final JSONObject bowling= data.getJSONObject("bowling");

            //SOME INPUT HAVE "-" instead of numbers...
            // batAverages[0]=(String) FC.get("Ave");
            //batAverages[1]=(String) ListA.get("Ave");
            // batAverages[2]=(String) tests.get("Ave");
            if(!bowling.isNull("listA")){
                final JSONObject ListA= bowling.getJSONObject("listA");
                bowlAvgOdi[0]=(String) ListA.get("Ave");

                wktsA= (String) ListA.get("Wkts");}
            else
                bowlAvgTest[0]="0";
            if(!bowling.isNull("ODIs")){
                final JSONObject ODI= bowling.getJSONObject("ODIs");
                bowlAvgOdi[0]=(String) ODI.get("Ave");
                wktsO= (String) ODI.get("Wkts");
                System.out.println(wktsO);}
            else
                bowlAvgTest[0]="0";
            if(!bowling.isNull("firstClass")){
                final JSONObject FC= bowling.getJSONObject("firstClass");
                bowlAvgTest[0]=(String) FC.get("Ave");

                wktsF= (String) FC.get("Wkts");
            }
            else
                bowlAvgTest[0]="0";
            if(!bowling.isNull("tests")){
                final JSONObject tests= bowling.getJSONObject("tests");
                bowlAvgTest[1]=(String) tests.get("Ave");
                wktsT= (String) tests.get("Wkts");}
            else
                bowlAvgTest[1]="0";
            //  bowlAvgTest[2]= (String) tests_.get("Ave");
            innTest=Double.parseDouble(innT);
            innFC=Double.parseDouble(innF);
            innListA=Double.parseDouble(innL);

            innOdi=Double.parseDouble(innO);
            if(wktsO.equals("-"))
                wktOdi=0.00;
            else
                wktOdi=Double.parseDouble(wktsO);
            if(wktsF.equals("-"))
                wktFC=0.00;
            else
                wktFC=Double.parseDouble(wktsF);
            if(wktsA.equals("-"))
                wktListA=0.00;
            else
                wktListA=Double.parseDouble(wktsA);
            if(wktsT.equals("-"))
                wktTest=0.00;
            else
                wktTest=Double.parseDouble(wktsT);



            for(int i=0; i<2;i++){
                // System.out.println(batAverages[i]);
                if(batAvgTest[i].equals("-")){
                    battingAvgTest[i]=0.0;
                    if (i==1)
                        innTest=0.00;
                    if (i==0)
                        innFC=0.00;
                }
                else {
                    battingAvgTest[i]=(Double.parseDouble(batAvgTest[i]));



                }

            }
            //System.out.println("battingAverageTestc"+battingAvgct+ "battingavgTesy"+battingAvgTest[0]+"asdasd"+battingAvgTest[1]);
            p.battingAvgct=0.00;
            innTest=innTest*0.4;
            innFC=innFC*0.6;
            battingAvgTest[0]=battingAvgTest[0]*1.4;
            battingAvgTest[1]=battingAvgTest[1]*1.6;
            p.battingAvgct=(((battingAvgTest[0]+innFC)*0.35)+((battingAvgTest[1]+innTest)*0.65));
            //System.out.println("Weighted battingAverageTest"+p.battingAvgct + "");
            for(int i=0; i<2;i++){
                // System.out.println(batAverages[i]);
                if(batAvgOdi[i].equals("-")){
                    battingAvgOdi[i]=0.0;
                    if (i==0)
                        innListA=0.00;
                    if (i==1)
                        innOdi=0.00;
                }
                else {

                    battingAvgOdi[i]=(Double.parseDouble(batAvgOdi[i]));
                }
            }
            p.battingAvgco=0.00;
            p.battingAvgco=(((battingAvgOdi[0]+innListA)*0.35)+((battingAvgOdi[1]+innOdi)*0.65));

            // System.out.println("battingAverageODI"+p.battingAvgco);

            for(int i=0; i<2;i++){
                //System.out.println(bowlAvgTest[i]);
                if(bowlAvgTest[i].equals("-")){
                    //	  System.out.println("DDDDDDDDDDDDDDDDDDD");
                    bowlingAvgTest[i]=100.0;
                    if (i==1)
                        innTest=500.00;
                    if (i==0)
                        innFC=500.00;
                }

                else {
                    bowlingAvgTest[i]=(Double.parseDouble(bowlAvgTest[i]));

                }
            }

            if(wktTest+wktFC>180)
            {
                p.bowlingAvgct=((bowlingAvgTest[1]*innTest)+(bowlingAvgTest[0]*innFC));
            }
            else p.bowlingAvgct=12000;
            //System.out.println(p.bowlingAvgct);
            p.Display_Player();
            squad_team1.add(p);// ADDING
            for(int i=0; i<2;i++){
                //System.out.println(bowlAvgTest[i]);
			/*	  if(bowlAvgOdi[i].equals("-")){
				//	  System.out.println("DDDDDDDDDDDDDDDDDDD");
					  bowlingAvgOdi[i]=0.0;
				  }
				  else {
					  bowlingAvgOdi[i]=(Double.parseDouble(bowlAvgOdi[i]));

				  }
			  }
			  //p.bowlingAvgct=(bowlingAvgOdi[1]*innOdi)+(bowlingAvgOdi[0]*innListA);
			 // System.out.println("\n ----------- Checking--\n");
			  */
                //  p.setcost("", BattingAvg, BowlingAvg);

            }
        }

    }

            catch(JSONException e){e.printStackTrace();}
            // delegate.processFinish(s);
            catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println(team1+"-------------   "+n1+"\n");

        for(int j=0;j<n2;j++) {    //Team1 k players initiate horahe hen
            final JSONObject player = players2.getJSONObject(j);
            Player p= new Player();
            String id = player.getString("pid");
            String name1=player.getString("name");


            //p.ID=id;
            p.name=name1;
            //CHANGE THAT I EXECUTED ##CHHHHHH
            try {
                String ss = new JSONTask12().execute("http://cricapi.com/api/playerStats?pid=" + id + "&apikey=AA56WBu4FyX74UjdhWWbCmeXwrn2").get();
                final JSONObject obj1 = new JSONObject(ss);
                //  System.out.println(obj1);
                if(!obj1.isNull("data")){
                    final JSONObject data= obj1.getJSONObject("data");
		/*	  if(obj1.has("playingRole")) {
			    	 p.setType(obj1.getString("playingRole")); ;
			    	}
			     else {
			    	 p.setType("TBD");
			     }*/
                    //  System.out.println(data);
                    // GETTING Batting AVERAGES
                    final JSONObject batting= data.getJSONObject("batting");
                    if(!batting.isNull("listA")){
                        final JSONObject ListA= batting.getJSONObject("listA");
                        batAvgOdi[0]=(String) ListA.get("Ave");
                        innL=(String) ListA.get("Inns");}
                    else
                    {batAvgOdi[0]="0";
                        innL="0";
                    }
                    if(!batting.isNull("ODIs")){
                        final JSONObject ODI= batting.getJSONObject("ODIs");
                        batAvgOdi[1]=(String) ODI.get("Ave");
                        innO=(String) ODI.get("Inns");}
                    else
                    { batAvgOdi[1]="0";
                        innO="0";}
                    if(!batting.isNull("firstClass")){
                        final JSONObject FC= batting.getJSONObject("firstClass");
                        batAvgTest[0]=(String) FC.get("Ave");
                        innF=(String) FC.get("Inns");}
                    else
                    {batAvgTest[0]="0";
                        innF="0";}
                    if(!batting.isNull("tests")){
                        final JSONObject tests= batting.getJSONObject("tests");
                        batAvgTest[1]=(String) tests.get("Ave");
                        innT= (String) tests.get("Inns");}
                    else
                    {batAvgTest[1]="0";
                        innT="0";}
                    //GETTING BOWLING AVERAGE NOW
                    final JSONObject bowling= data.getJSONObject("bowling");

                    //SOME INPUT HAVE "-" instead of numbers...
                    // batAverages[0]=(String) FC.get("Ave");
                    //batAverages[1]=(String) ListA.get("Ave");
                    // batAverages[2]=(String) tests.get("Ave");
                    if(!bowling.isNull("listA")){
                        final JSONObject ListA= bowling.getJSONObject("listA");
                        bowlAvgOdi[0]=(String) ListA.get("Ave");

                        wktsA= (String) ListA.get("Wkts");}
                    else
                        bowlAvgTest[0]="0";
                    if(!bowling.isNull("ODIs")){
                        final JSONObject ODI= bowling.getJSONObject("ODIs");
                        bowlAvgOdi[0]=(String) ODI.get("Ave");
                        wktsO= (String) ODI.get("Wkts");
                        System.out.println(wktsO);}
                    else
                        bowlAvgTest[0]="0";
                    if(!bowling.isNull("firstClass")){
                        final JSONObject FC= bowling.getJSONObject("firstClass");
                        bowlAvgTest[0]=(String) FC.get("Ave");

                        wktsF= (String) FC.get("Wkts");
                    }
                    else
                        bowlAvgTest[0]="0";
                    if(!bowling.isNull("tests")){
                        final JSONObject tests= bowling.getJSONObject("tests");
                        bowlAvgTest[1]=(String) tests.get("Ave");
                        wktsT= (String) tests.get("Wkts");}
                    else
                        bowlAvgTest[1]="0";
                    //  bowlAvgTest[2]= (String) tests_.get("Ave");
                    innTest=Double.parseDouble(innT);
                    innFC=Double.parseDouble(innF);
                    innListA=Double.parseDouble(innL);

                    innOdi=Double.parseDouble(innO);
                    if(wktsO.equals("-"))
                        wktOdi=0.00;
                    else
                        wktOdi=Double.parseDouble(wktsO);
                    if(wktsF.equals("-"))
                        wktFC=0.00;
                    else
                        wktFC=Double.parseDouble(wktsF);
                    if(wktsA.equals("-"))
                        wktListA=0.00;
                    else
                        wktListA=Double.parseDouble(wktsA);
                    if(wktsT.equals("-"))
                        wktTest=0.00;
                    else
                        wktTest=Double.parseDouble(wktsT);



                    for(int i=0; i<2;i++){
                        // System.out.println(batAverages[i]);
                        if(batAvgTest[i].equals("-")){
                            battingAvgTest[i]=0.0;
                            if (i==1)
                                innTest=0.00;
                            if (i==0)
                                innFC=0.00;
                        }
                        else {
                            battingAvgTest[i]=(Double.parseDouble(batAvgTest[i]));



                        }

                    }
                    //System.out.println("battingAverageTestc"+battingAvgct+ "battingavgTesy"+battingAvgTest[0]+"asdasd"+battingAvgTest[1]);
                    p.battingAvgct=0.00;
                    innTest=innTest*0.4;
                    innFC=innFC*0.6;
                    battingAvgTest[0]=battingAvgTest[0]*1.4;
                    battingAvgTest[1]=battingAvgTest[1]*1.6;
                    p.battingAvgct=(((battingAvgTest[0]+innFC)*0.35)+((battingAvgTest[1]+innTest)*0.65));
                    //System.out.println("Weighted battingAverageTest"+p.battingAvgct + "");
                    for(int i=0; i<2;i++){
                        // System.out.println(batAverages[i]);
                        if(batAvgOdi[i].equals("-")){
                            battingAvgOdi[i]=0.0;
                            if (i==0)
                                innListA=0.00;
                            if (i==1)
                                innOdi=0.00;
                        }
                        else {

                            battingAvgOdi[i]=(Double.parseDouble(batAvgOdi[i]));
                        }
                    }
                    p.battingAvgco=0.00;
                    p.battingAvgco=(((battingAvgOdi[0]+innListA)*0.35)+((battingAvgOdi[1]+innOdi)*0.65));

                    // System.out.println("battingAverageODI"+p.battingAvgco);

                    for(int i=0; i<2;i++){
                        //System.out.println(bowlAvgTest[i]);
                        if(bowlAvgTest[i].equals("-")){
                            //	  System.out.println("DDDDDDDDDDDDDDDDDDD");
                            bowlingAvgTest[i]=100.0;
                            if (i==1)
                                innTest=500.00;
                            if (i==0)
                                innFC=500.00;
                        }

                        else {
                            bowlingAvgTest[i]=(Double.parseDouble(bowlAvgTest[i]));

                        }
                    }

                    if(wktTest+wktFC>180)
                    {
                        p.bowlingAvgct=((bowlingAvgTest[1]*innTest)+(bowlingAvgTest[0]*innFC));
                    }
                    else p.bowlingAvgct=12000;
                    //System.out.println(p.bowlingAvgct);
                    p.Display_Player();
                    squad_team2.add(p);// ADDING
                    for(int i=0; i<2;i++){
                        //System.out.println(bowlAvgTest[i]);
			/*	  if(bowlAvgOdi[i].equals("-")){
				//	  System.out.println("DDDDDDDDDDDDDDDDDDD");
					  bowlingAvgOdi[i]=0.0;
				  }
				  else {
					  bowlingAvgOdi[i]=(Double.parseDouble(bowlAvgOdi[i]));

				  }
			  }
			  //p.bowlingAvgct=(bowlingAvgOdi[1]*innOdi)+(bowlingAvgOdi[0]*innListA);
			 // System.out.println("\n ----------- Checking--\n");
			  */
                        //  p.setcost("", BattingAvg, BowlingAvg);

                    }
                }

            }

            catch(JSONException e){e.printStackTrace();}
            // delegate.processFinish(s);
            catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        ArrayList<ArrayList<Player>> returnObj=new ArrayList<>(2);
        returnObj.add(squad_team1);
        returnObj.add(squad_team2);
        //Adding and Returning the returnObj


        return returnObj;
    }

}

