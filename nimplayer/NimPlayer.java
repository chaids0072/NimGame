/*******************************************************************************
 *This program is designed by Gang Chen(SID: 724553) to be submitted to The    *
 * Department of Engineering as Project c assignment                           *
 * @Gang Chen on 20/05/2015                                                    *
 *******************************************************************************
 */

import java.util.Comparator;
import static java.lang.Double.isNaN;

/*
This class caleed NimPlyer is to be control by Nimsys to creat player objects in
the game
 */

public class NimPlayer implements Comparator<NimPlayer>{
    
    
    //Declaration
    private String userName,familyName,givenName;
    
    private int gamePlayed,gameWon;
    
    private double winRate; 
    
    //Constructors
    public NimPlayer(){
        this.userName = "";
        this.familyName = "";
        this.givenName = "";
        this.gamePlayed = 0;
        this.gameWon = 0;
        this.winRate = 0;
    }
    
    public NimPlayer(String userName, String familyName, String givenName){
        this.userName = userName;
        this.familyName = familyName;
        this.givenName = givenName;
        this.gamePlayed = 0;
        this.gameWon = 0;
        this.winRate = 0;
    }
    
    public NimPlayer(String userName, String familyName, String givenName, 
            double winRate,int gamePlayed,int gameWon){
        this.userName = userName;
        this.familyName = familyName;
        this.givenName = givenName;
        this.gamePlayed = gamePlayed;
        this.gameWon = gameWon;
        this.winRate = winRate;
    }   
    
    //Mutators
    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setGivenName(String giveName) {
        this.givenName = giveName;
    }
    
    public void setGamePlayed(int i) {
        this.gamePlayed = i;
    }

    public void setGameWon(int i) {
        this.gameWon = i;
    }
    
    public void setWinRate(){
    	   winRate = 0.0;
    }
    
    //Accessor
    public String getUserName(){
        return this.userName;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public String getGivenName() {
        return this.givenName;
    }
        
    public int getGamePlayed(){
        return this.gamePlayed;
    }
    
    public int getGameWon(){
        return this.gameWon;
    }
    
    public double getWinRate(){
    	if(isNaN(((1.0*this.gameWon)/this.gamePlayed) * 100.0)){
    	        setWinRate();
    	}else{
    		this.winRate = ((1.0*this.gameWon)/this.gamePlayed) * 100.0;
    	}
    	
        return this.winRate;   
    }
    
    public String getWinRateString(){
    	//Form a string to print out win rate as required format
    	return (Long.toString(Math.round(getWinRate()))+"%");
    }
    
    public boolean isAiPlayer(){
        return false;
    }
    
    //toString Function
    @Override
    public String toString(){
        return (userName+","+givenName+","+familyName+","
                    +gamePlayed+" games"+","+gameWon+" wins");
    }
    
    
    //Comparator
    //For name comparing and only name comparing
    @Override
    public int compare(NimPlayer objectOne, NimPlayer objectTwo) {
        if(objectOne == null||objectTwo == null){
            throw new NullPointerException("Failed Attempt to compare");
        }else{
            return objectOne.getUserName().compareTo(objectTwo.getUserName());
        }
    }
}
