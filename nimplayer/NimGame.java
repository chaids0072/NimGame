/*******************************************************************************
 *This program is designed by Gang Chen(SID: 724553) to be submitted to The    *
 * Department of Engineering as Project c assignment                           *
 * @Gang Chen on 20/05/2015                                                    *
 *******************************************************************************
 */

import java.util.Scanner;

public class NimGame { 
    private int stoneNumber;
    private int stoneBoundary;
    private final NimPlayer playerOne;
    private final NimPlayer playerTwo;
    
    //Constructor
    public NimGame(int stoneNumber, int stoneBoundary,
            NimPlayer playerIndexOne, NimPlayer playerIndexTwo){
        setBoundary(stoneBoundary);
        setStoneNumber(stoneNumber);
        this.playerOne = playerIndexOne;
        this.playerTwo = playerIndexTwo;
    }
    
    //Accessor
    private int getStoneAll(){
     return this.stoneNumber;
    }
    
    private int getStoneBoundary(){
         return this.stoneBoundary;
    }
    
    //Mutators
    private void setBoundary(int stoneBoundary) {
        this.stoneBoundary = stoneBoundary;
    }
    
    private void setStoneNumber(int stoneNumber){
        this.stoneNumber = stoneNumber;
    }
    
    /*
    *function removeStone
    *===========================================================================
    *Remove numbers of stone as required
    */
    private int removeStone(int number){
        return this.stoneNumber -= number;
    }
    
    /*
    *function printStars
    *===========================================================================
    *Helping with printing starts
    */
    private void printStars(int number){
        int i;

        for(i = 0;i < number;i++){
            System.out.print(" *");
        }   
        
        System.out.println(); 
    }
    
    /*
    *function startGame
    *===========================================================================
    *Proceed the game 
    *return the play who won to Nimsys to add gameWon number by 1
    */
    public NimPlayer startGame(Scanner gameScanner) {   
        System.out.println(); 
        System.out.println("Initial stone count: "+ this.stoneNumber);
        System.out.println("Maximum stone removal: "+ this.stoneBoundary);  
        System.out.println("Player 1: " + this.playerOne.getGivenName()
                +" "+ this.playerOne.getFamilyName());
        System.out.println("Player 2: " + this.playerTwo.getGivenName()
                +" "+ this.playerTwo.getFamilyName()); 
                
        
        int temp = 1;
        int input = 0;
        
        //To decide which number is the minimum number
        //the boundary or the total stones left to remove
        int decision = getStoneBoundary();
        
        
        //Keep the game activated by usin this temp variable.
        while(temp > 0){
            if(getStoneAll() < decision){
                decision = getStoneAll();
            }
            
            //Keep checking till the end of the game
            if(temp%2 != 0){                                
                //We need to make sure there are enough stones to be removed 
                //If -> yes, then keep going
                //else -> no, then another player won this game
                
                while(true){   
                    try{
                        System.out.println();
                        System.out.print(this.stoneNumber + " stones left:");
                        printStars(this.stoneNumber);
                        System.out.println(playerOne.getGivenName()
                        +"'s turn - remove how " + "many?");
                        
                        if(!playerOne.isAiPlayer()){
                            input = decisionMaker(gameScanner,decision);
                        }else{
                            input = decisionMaker(decision);
                        }
                         
                        
                        if(input > decision || input < 1){
                            System.out.println();
                            throw new StoneMove(decision);   
                        }
                        else{
                            break;
                        }
                    }catch(StoneMove e){
                        System.out.println(e);
                    }  
                }      
                
                if(input >= getStoneAll()){
                    System.out.println();
                    System.out.println("Game Over");
                    System.out.println(playerTwo.getGivenName()
                    	    +" "+playerTwo.getFamilyName()+" wins!");
                    return (new NimPlayer(playerTwo.getUserName(),
                        playerTwo.getFamilyName(),playerTwo.getGivenName()));
                }else{
                    removeStone(input);   
                }    
            }else{
                //We need to make sure there are enough stones to be removed 
                //If -> yes, then keep going
                //else -> no, then another player won this game
                while(true){
                    try{
                        System.out.println();
                        System.out.print(this.stoneNumber + " stones left:"); 
                        printStars(this.stoneNumber);
                        System.out.println(playerTwo.getGivenName()
                        +"'s turn - remove how " + "many?");

                        if(!playerTwo.isAiPlayer()){
                            input = decisionMaker(gameScanner,decision);
                        }else{
                            input = decisionMaker(decision);
                        }
                        
                        if(input > decision || input < 1){
                            System.out.println();
                            throw new StoneMove(decision);   
                        }
                        else{
                            break;
                        }
                    }catch(StoneMove e){
                        System.out.println(e);
                    }  
                }
                         
                if(input >= getStoneAll()){
                    System.out.println();
                    System.out.println("Game Over");
                    System.out.println(playerOne.getGivenName()
                    	    +" "+playerOne.getFamilyName()+" wins!");
                    return (new NimPlayer(playerOne.getUserName(),
                        playerOne.getFamilyName(),playerOne.getGivenName()));
                }else{
                    removeStone(input);
                }   
                
            } 
            temp += 1;
        }
        
        //gameScanner.close();
        //return the player who won
        return new NimPlayer();
    }
    
    public int decisionMaker(Scanner gameScanner,int decision){
        return gameScanner.nextInt();
    }
    
    /*
    *This function is for the winning guaranteed strategy
    */
    public int decisionMaker(int decision){
        int counter = 0,left;
        
        while((counter*(decision+1)+1)< getStoneAll()){
            counter += 1;    
        }
        
        left = getStoneAll()-((--counter)*(decision+1)+1);
        
        return left;
    }
}

