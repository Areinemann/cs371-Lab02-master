package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 * 
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    Hashtable<String, SoccerPlayer> players= new Hashtable<String, SoccerPlayer>();
   // ArrayList<String> keys = new ArrayList<>()

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
	public boolean addPlayer(String firstName, String lastName,
			int uniformNumber, String teamName) {

        if(players.containsKey(firstName+lastName))
        {
            return false;
        }

        players.put(firstName+lastName, new SoccerPlayer(firstName,lastName,uniformNumber,teamName));

        return true;
	}

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {

        String hashKey = firstName+lastName;

        if(players.containsKey(hashKey))
        {
            players.remove(hashKey);
            return true;
        }

        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
	public SoccerPlayer getPlayer(String firstName, String lastName) {

        String hashKey = firstName+lastName;

        if(players.containsKey(hashKey))
        {
            return players.get(hashKey);
        }

        return null;
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        String hashKey = firstName+lastName;

        if(players.containsKey(hashKey))
        {
            players.get(hashKey).bumpGoals();
            return true;
        }
        return false;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        String hashKey = firstName+lastName;

        if(players.containsKey(hashKey))
        {
            players.get(hashKey).bumpAssists();
            return true;
        }
        return false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        String hashKey = firstName+lastName;

        if(players.containsKey(hashKey))
        {
            players.get(hashKey).bumpShots();
            return true;
        }
        return false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        String hashKey = firstName+lastName;

        if(players.containsKey(hashKey))
        {
            players.get(hashKey).bumpSaves();
            return true;
        }
        return false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        String hashKey = firstName+lastName;

        if(players.containsKey(hashKey))
        {
            players.get(hashKey).bumpFouls();
            return true;
        }
        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        String hashKey = firstName+lastName;

        if(players.containsKey(hashKey))
        {
            players.get(hashKey).bumpYellowCards();
            return true;
        }
        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        String hashKey = firstName+lastName;

        if(players.containsKey(hashKey))
        {
            players.get(hashKey).bumpRedCards();
            return true;
        }
        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName) {
        int count = 0;

        if (teamName == null) {
            return players.size();
        }



        Collection colOfPlayers = players.values();

        Object[] arrayOfPlayers = colOfPlayers.toArray();

        for(int i=0; i < arrayOfPlayers.length; i++)
        {
            SoccerPlayer instance = (SoccerPlayer)arrayOfPlayers[i];
            if(instance.getTeamName().equals(teamName))
            {
                count++;
            }
        }

        return count;
	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        int count = 0;

        if(teamName == null)
        {
            for (SoccerPlayer sp : players.values())
            {
                if(count == idx)
                {
                    return sp;
                }

                count++;
            }
        }


        for (SoccerPlayer sp : players.values())
        {
            if(sp.getTeamName().equals(teamName))
            {
                if(count == idx)
                {
                    return sp;
                }

                count++;
            }
        }


        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {
        try {
            Scanner scan = new Scanner(file);

            String[] player = new String[11];

            for(int i = 0; i < 11 || scan.hasNextLine(); i++)
            {
                if(i==11)
                {
                    i = 0;
                }

                player[i] = scan.nextLine();

                if(i == 10)
                {
                    int num = Integer.parseInt(player[2]);
                    addPlayer(player[0], player[1], num, player[10]);

                    for (int j = 0; j < Integer.parseInt(player[3]);j++ )
                    {
                        bumpGoals(player[0],player[1]);
                    }

                    for (int j = 0; j < Integer.parseInt(player[4]);j++ )
                    {
                        bumpAssists(player[0], player[1]);
                    }

                    for (int j = 0; j < Integer.parseInt(player[5]);j++ )
                    {
                        bumpShots(player[0], player[1]);
                    }
                    for (int j = 0; j < Integer.parseInt(player[6]);j++ )
                    {
                        bumpFouls(player[0], player[1]);
                    }

                    for (int j = 0; j < Integer.parseInt(player[7]);j++ )
                    {
                        bumpSaves(player[0], player[1]);
                    }

                    for (int j = 0; j < Integer.parseInt(player[8]);j++ )
                    {
                        bumpYellowCards(player[0], player[1]);
                    }
                    for (int j = 0; j < Integer.parseInt(player[9]);j++ )
                    {
                        bumpRedCards(player[0], player[1]);
                    }

                }//if
            }//for

            scan.close();

            return  true;

        } catch (FileNotFoundException e) {
            return false;
        }
	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file) {
        try {
            PrintWriter pw = new PrintWriter(file);

            for (SoccerPlayer sp : players.values()) {
                pw.println(sp.getFirstName());
                pw.println(sp.getLastName());
                pw.println(sp.getUniform());
                pw.println(sp.getGoals());
                pw.println(sp.getAssists());
                pw.println(sp.getShots());
                pw.println(sp.getFouls());
                pw.println(sp.getSaves());
                pw.println(sp.getYellowCards());
                pw.println(sp.getRedCards());
                pw.println(sp.getTeamName());

                logString(sp.getFirstName() + " " + sp.getLastName() + " has been saved");
            }
            pw.close();

            return true;
        }
        catch (FileNotFoundException fnfe)
        {
            return false;
        }
	}

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams() {



        return new HashSet<String>();
	}

}
