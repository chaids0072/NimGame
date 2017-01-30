/*******************************************************************************
 *This program is designed by Gang Chen(SID: 724553) to be submitted to The    *
 * Department of Engineering as Project c assignment                           *
 * @Gang Chen on 20/05/2015                                                    *
 *******************************************************************************
 */

//Import functions
import java.io.*;
import java.util.*;

/*
This class called Nimsys will control anohter class named NimPlyaer
*/
public class Nimsys{
    //Maxmuim number of players is 100
    private static final int MAX_PLAYER_NUMBER = 100;
    
    //Array to store players
    private static final NimPlayer[] PLAYER_LIST =
                                    new NimPlayer[MAX_PLAYER_NUMBER];
    
    //Scanner for command scanning

    
    //counter for the number of player's counting
    private static int PLAYER_COUNTER = 0;
    
    /*
     *The main function will constantly check the player's instruction 
     *till the command "exit" showed up. All other instructions given 
     *in the assignment for this project has been implemented and being
     *functional
     */
    public static void main(String[] args) throws IOException 
    { 
        /*declarations*/
        Scanner scanner = new Scanner(System.in); 
        ArrayList<String> commandArray = null;
        String command;
        int instruction=0;    

        /*initializations*/
        initializer();
        File myFile = new File("players.dat");
        FileWriter fw = new FileWriter(myFile,true);
        if(!(myFile.exists()))
        {
            myFile.createNewFile();
        }else{
            fileFunction(myFile,scanner);
        }
        
        /*System starts from Here*/
        System.out.println("Welcome to Nim");
        System.out.println();
        CommandLoop:        
        while (true) {
            //This block of code will read the whole line of input from user and
            //store all the information in the ArrayList we created ealier
            try{  
                commandArray =  new ArrayList<>();
                command = "";

                System.out.print(">");

                //This block of code will read the whole line of input from user and
                //store all the information in the ArrayList we created ealier
                while(command.equals("")){
                        command = scanner.nextLine();
                        if(!(command.equals(""))){
                                break;
                        }
                }

                Scanner temp = new Scanner(command);
                temp.useDelimiter("\\s|,");

                while(temp.hasNext()){
                    String a = temp.next();
                    commandArray.add(a);
                }
                
                commandChecker(commandArray);
            }catch(CommandException e1){
                System.out.println(e1);
            }
            
            try{
                if(commandArray.isEmpty()){
                    throw new IndexOutOfBoundsException();
                }else{
                    //Loop will constantly control the input from user
                    //the switch condition is the first instruction string in  
                    //ArrayList
                    switch (commandArray.get(instruction)) {
                        case "exit":
                            exitFunction(commandArray,myFile);
                            scanner.close();
                            break CommandLoop; 
                        case "addplayer":
                            addFunction(commandArray);
                            break;
                        case "removeplayer":
                            removeFunction(commandArray,scanner);
                            break;
                        case "editplayer":
                            editFunction(commandArray);
                            break;
                        case "resetstats":
                            resetFunction(commandArray,scanner);
                            break;
                        case "displayplayer":
                            displayFunction(commandArray);
                            break;
                        case "rankings":
                            rankFunction(commandArray);
                            break;
                        case "startgame":
                            gameFunction(commandArray,scanner);
                            break;
                        case "addaiplayer":
                            addAiFunction(commandArray);
                            break;
                        default:
                            break;
                    }
                    System.out.println();
                }
            }
            catch(IndexOutOfBoundsException e2){
                System.out.println(e2);
            }
        }        
    }
    
    public static void commandChecker(ArrayList<String> instruction)
            throws CommandException{
        String command = instruction.get(0);
        
        if( !(command.equals("addplayer")) &&
            !(command.equals("exit")) && 
            !(command.equals("removeplayer")) &&
            !(command.equals("editplayer")) &&
            !(command.equals("resetstats")) &&
            !(command.equals("displayplayer")) &&
            !(command.equals("rankings")) &&
            !(command.equals("addaiplayer")) &&
            !(command.equals("startgame"))){
            throw new CommandException(command);
        } 
    }
    
    public static void commandSizeChecker(String command,int size)
            throws CommandSizeException{
        if((command.equals("addplayer") || command.equals("addaiplayer"))
                && size != 4){
            throw new CommandSizeException();
        }else if(command.equals("removeplayer")){
            if(size != 1 && size != 2){
                throw new CommandSizeException();
            }  
        }else if(command.equals("exit")){
            if(size != 1 ){
                throw new CommandSizeException();
            }
        }else if(command.equals("editplayer")){
            if(size != 2 ){
                throw new CommandSizeException();
            }
        }else if(command.equals("resetstats")){
            if(size != 1 && size != 2){
                throw new CommandSizeException();
            }
        }else if(command.equals("displayplayer")){
            if(size != 1 && size != 2){
                 throw new CommandSizeException();
            }
        }else if(command.equals("rankings")){
            if(size != 1 ){
                throw new CommandSizeException();
            }
        }else if(command.equals("startgame")){
            if(size != 5 ){
                throw new CommandSizeException();
            }
        }
    }
    
    /*
    *function initializer
    *===========================================================================
    *Initiating all the player in the PLAYER_LIST
    */
    private static void initializer() {
        int i;
        for(i = 0;i<MAX_PLAYER_NUMBER;i++){
            
            //All players given name, family name, user name
            //is empty.
            //given name:""
            //family name:""
            //given name:""
            PLAYER_LIST[i] = new NimPlayer();
        }
    }
    
    /*
    *function playerListChecker
    *===========================================================================
    *Checking whether the player in already in the player list or not.
    *->return false if such a player with this username already existed.
    *->return true vice versa.
    */
    private static boolean playerListChecker(String userName) {
        int i;
        boolean playerAlreadyThere = false;
        for(i = 0;i < MAX_PLAYER_NUMBER;i++)
        {
            //return true if the player exists and break the loop
            if(PLAYER_LIST[i].getUserName().equals(userName) == true)
            {
                playerAlreadyThere = true;
                break;
            }
        }
        return playerAlreadyThere;
    }
    
    /*
    *function getPlayerIndex
    *===========================================================================
    *Return the index of the player user was enquiring about
    */
    private static int getPlayerIndex(String userName) {
        int i,index = -1;
        for(i = 0;i < MAX_PLAYER_NUMBER;i++)
        {
            //return the index of the player user was enquiring about
            //break the loop when player is spotted in PLAYER_LIST
            if(PLAYER_LIST[i].getUserName().equals(userName) == true)
            {
                index = i;
                break;
            }else{
                index = -1;
            }
        }
        return index;
    }

    /*
    *function removeFunction
    *===========================================================================
    *the remove function do two things
    *1.remove all the player from the database if user required so
    *2.remove specific player the user was enquiring about from the data base
    */
    private static void removeFunction(ArrayList<String> command,
            Scanner SCANNER) {
        //The location of the name which needs to get rid of in the ArrayList
        //is 1.
        int namePlaceInNameArray = 1;
        
        //If the size of command is less than 2 -> remove all players
        //If the size of command is 2           -> remove specific player
        try{
            commandSizeChecker(command.get(0),command.size());
            
            if(command.size() >= 2){
                if(playerListChecker(command.get(namePlaceInNameArray))
                        == false){
                    System.out.println("The player does not exist.");
                }else{
                    //If player exsisted, call playerRemover to remove player
                    playerRemover(command.get(namePlaceInNameArray));
                }
            }else{
                //Simply initiating the PLAYER_LIST if remove all Player was 
                //required
                System.out.println("Are you sure you want to"
                        + " remove all players? (y/n)");
                if(SCANNER.nextLine().equals("y") == true){
                    initializer();
                    PLAYER_COUNTER = 0;
                }
            }
        }catch(CommandSizeException e){
            System.out.println(e);
        }
    }
    
    /*
    *function addFunction
    *===========================================================================
    *the remove function do two things
    *Add player to the database with information such as family name,user name,
    *given name that user entered into the ArrayList in the main function
    */
    private static void addFunction(ArrayList<String> command) {
        //Max elements for calling "addplayer" is 4
        int max = 4;
        
        //user name is located in the primary position in the ArrayList
        //family name is located second
        //given name is located seoncd
        int userName = 1;
        int familyName = 2;
        int givenName = 3; 
        
        try{
            commandSizeChecker(command.get(0),command.size());
            
            if(PLAYER_COUNTER >= 100)
            {
                System.out.println("Too many players,Brah!");
            }
            else
            { 
                //If the player does not existd from the result of 
                //playerListChecker, add the player to database
                if(playerListChecker(command.get(userName)) == false){
                    PLAYER_LIST[PLAYER_COUNTER++] = new 
                                        NimPlayer(command.get(userName),
                                                    command.get(familyName),
                                                    command.get(givenName));
                }else{
                   System.out.println("The player already exists."); 
                }
            } 
        }catch(CommandSizeException e){
            System.out.println(e);
        }
    }
    
    /*
    *function addFunction
    *===========================================================================
    *the remove function do two things
    *Add player to the database with information such as family name,user name,
    *given name that user entered into the ArrayList in the main function
    */
    private static void playerRemover(String userName) {
        int i, index;
        //If ths player is the only player in the PLAYER_LIST
        //Initiating the PLAYER_LIST
        if(PLAYER_COUNTER == 1){
            initializer();
            PLAYER_COUNTER = 0;
        }else{
            //Obtaining the index of player in PLAYER_LIST
            index = getPlayerIndex(userName);
            
            //If the player is located in the last position in PLAYER_LIST
            if(index == PLAYER_COUNTER - 1){
                PLAYER_LIST[index] = new NimPlayer();
            }else{
                //else removing the player and then move up the rest of the
                //player by 1 index in the PLAYER_LIST
                for(i = index;i < PLAYER_COUNTER;i++){
                    PLAYER_LIST[i] = PLAYER_LIST[i+1];
                }
                PLAYER_LIST[i] = new NimPlayer();
            }
            
            //One less Player, so PLAYER_COUNTER decrements
            PLAYER_COUNTER -= 1; 
        }
    }

    /*
    *function editFunction
    *===========================================================================
    *Editing player's information such as given name or family name by entering
    *the user name user was wishing to edit
    */
    private static void editFunction(ArrayList<String> command) {
        //the index of each element in the ArrayList
        int user = 1;
        int family = 2;
        int given = 3;
        int index;
        
        try{
            commandSizeChecker(command.get(0),command.size());
            
            if(playerListChecker(command.get(user)) == false){
                System.out.println("The player does not exist."); 
            }else{
                index = getPlayerIndex(command.get(user));
                PLAYER_LIST[index] = new NimPlayer(command.get(user),
                command.get(family),command.get(given));
            } 
        }catch(CommandSizeException e){
            System.out.println(e);
        }
        
    }
    
    /*
    *function resetFunction
    *===========================================================================
    *This function can do 2 things:
    *1. reset the status of all player in the PLAYER_LIST
    *2. reset a specific player's status the user was enquiring about
    */
    private static void resetFunction(ArrayList<String> command,
            Scanner scanner) {
        int max =  2;
        int user = 1;
        int index;
        
        try{
            commandSizeChecker(command.get(0),command.size());
            
            //If the size is less than 2
            //Reseting all player's status
            if(command.size() < max){
                System.out.println("Are you sure you want to "
                        + "reset all player statistics? (y/n)");
                if(scanner.nextLine().equals("y") == true){
                    for(NimPlayer temp:PLAYER_LIST){
                        temp.setGamePlayed(0);
                        temp.setGameWon(0);
                        temp.setWinRate();
                    }
                }
            }else{
                //Reseting a specific player's status
                index = getPlayerIndex(command.get(user));
                if(index == -1){
                   System.out.println("The player does not exist."); 
                }else{
                    PLAYER_LIST[index].setGamePlayed(0);
                    PLAYER_LIST[index].setGameWon(0);
                    PLAYER_LIST[index].setWinRate();
                }
            }
        }catch(CommandSizeException e){
            System.out.println(e);
        }
    }

    /*
    *function displayFunction
    *===========================================================================
    *This function will display all the player whose information is in the 
    *database into the consolo for user's recognisation
    */
    private static void displayFunction(ArrayList<String> command) {
        int name = 1;
        int max = 2;
        
        try{
            commandSizeChecker(command.get(0),command.size());
  
            //Call comparator to assort PLAYER_LIST by name
            Arrays.sort(PLAYER_LIST,new NimPlayer());

            //Again, if size if less than 2
            //Displaying all player's information
            if(command.size() < max){
                for(NimPlayer temp:PLAYER_LIST){
                    if(!(temp.getUserName().equals(""))){
                        System.out.println(temp);  
                    }
                }
                
            }else{
                //Displaying a specific player's status
                for(NimPlayer temp:PLAYER_LIST){
                    if(temp.getUserName().equals(command.get(name))){
                        System.out.println(temp);  
                    }
                }
            }
            
        }catch(CommandSizeException e){
            System.out.println(e);
        } 
    }

    /*
    *function rankFunction
    *===========================================================================
    *This function will displayer all the game information about the players 
    *into the consolo
    */
    private static void rankFunction(ArrayList<String> command) {   
        try{
            commandSizeChecker(command.get(0),command.size());
             
            Arrays.sort(PLAYER_LIST,new NimPlayer());
            //Using comparator to assort array by winning rate
            Arrays.sort(PLAYER_LIST,new Ranking());

            for(NimPlayer temp:PLAYER_LIST){ 
                if(!(temp.getUserName().equals(""))){
                    System.out.printf("%-5s| %02d games | %s %s",
                            temp.getWinRateString(),temp.getGamePlayed(),
                            temp.getGivenName(),temp.getFamilyName());
                    System.out.println();
                }     
            }
        }catch(CommandSizeException e3){
            System.out.println(e3);
        }
        
    }

    /*
    *function gameFunction
    *===========================================================================
    *This function will call NimGame to perform the game procedures as each time
    *a new game is about to excuete
    */
    private static void gameFunction(ArrayList<String> command,
            Scanner SCANNER) {
        int max = 5,stone, stoneBoundary;
        int playerOneName = 3, playerTwoName = 4;
        int index;
        int gamePlayedOne;
        int gamePlayedTwo;
        int gameWon;
        NimPlayer player1,player2,playerWon;

        try{
            commandSizeChecker(command.get(0),command.size());
            
            if(command.size() < max || PLAYER_COUNTER < 2){
                System.out.println("Your command is not recognised");
            }else if(Integer.parseInt(command.get(1)) <= 0 ||
                    Integer.parseInt(command.get(2)) <= 0 ){
                System.out.println("Wrong stone number or stone boundary");
            }else if(command.get(3).equals(command.get(4))){
                System.out.println("Can't have one man party");
            }else{
                if(playerListChecker(command.get(playerOneName)) == false 
                        || playerListChecker(command.get(playerTwoName)) == false){
                    System.out.println("One of the players does not exist.");
                }else{    
                    //Player one 's game played number + 1
                    index = getPlayerIndex(command.get(playerOneName));
                    gamePlayedOne = PLAYER_LIST[index].getGamePlayed();
                    gamePlayedOne += 1;
                    PLAYER_LIST[index].setGamePlayed(gamePlayedOne);    

                    //Player two 's game played number + 1
                    index = getPlayerIndex(command.get(playerTwoName));
                    gamePlayedTwo = PLAYER_LIST[index].getGamePlayed();
                    gamePlayedTwo += 1;
                    PLAYER_LIST[index].setGamePlayed(gamePlayedTwo);

                    //Game starts
                    stone = Integer.parseInt(command.get(1));
                    stoneBoundary = Integer.parseInt(command.get(2));
                    player1 = PLAYER_LIST[
                            getPlayerIndex(command.get(playerOneName))];
                    player2 = 
                            PLAYER_LIST[
                            getPlayerIndex(command.get(playerTwoName))];
                    NimGame newGame = 
                            new NimGame(stone,stoneBoundary,player1,player2);           
                    playerWon = newGame.startGame(SCANNER);

                    //Player who won game won number + 1
                    index = getPlayerIndex(playerWon.getUserName());
                    gameWon = PLAYER_LIST[index].getGameWon();
                    gameWon += 1;
                    PLAYER_LIST[index].setGameWon(gameWon);
                }
            } 
        }catch(CommandSizeException e){
            System.out.println(e);
        } 
    }

    private static void exitFunction(ArrayList<String> commandArray,
            File myFile) throws IOException {
        try{
            commandSizeChecker(commandArray.get(0),commandArray.size());
            System.out.println();
            
            //Call comparator to assort PLAYER_LIST by name
            Arrays.sort(PLAYER_LIST,new NimPlayer());
            
            try (PrintWriter writting = new PrintWriter(myFile)) {
                writting.write("WinRate,"+"GamePlayed,"+"GameWon,"+"GivenName,"
                        +"FamilyName,"+"UserName,"+"AI_player"+"\n");
                
                for(NimPlayer temp:PLAYER_LIST){
                    if(!(temp.getUserName().equals(""))){
                        writting.print(
                                Long.toString(Math.round(temp.getWinRate()))
                                +","+temp.getGamePlayed()
                                +","+temp.getGameWon()
                                +","+temp.getGivenName()
                                +","+temp.getFamilyName()
                                +","+temp.getUserName()
                                +","
                                +(temp.isAiPlayer() == true ? "true":"false")
                                +"\n");
                    }
                }     
            }

        }catch(CommandSizeException e){
            System.out.println(e);
        }
    }

    private static void fileFunction(File myFile,Scanner scanner) 
            throws FileNotFoundException, IOException {
        
        String givenName,familyName,userName,isAiPlayer;
        int winRate,gamePlayed,gameWon,numberOfLines;

        
        try{
            scanner = new Scanner(myFile);
            scanner.useDelimiter("\\s|,");
            
            try (LineNumberReader line = new 
                                    LineNumberReader(new FileReader(myFile))) {
                line.skip(Long.MAX_VALUE);
                numberOfLines = line.getLineNumber();
                //System.out.println(numberOfLines);
                
                if(scanner.hasNextLine()){
                    String header = scanner.nextLine();
                    numberOfLines -= 1;
                }
            }

            while (numberOfLines > 0) {
                //System.out.println("1");
                winRate = Integer.parseInt(scanner.next());
                gamePlayed = Integer.parseInt(scanner.next());
                gameWon = Integer.parseInt(scanner.next());
                givenName = scanner.next();
                familyName = scanner.next();
                userName = scanner.next();
                isAiPlayer = scanner.next();
                
                if(!playerListChecker(userName) && isAiPlayer.equals("false")){
                    PLAYER_LIST[PLAYER_COUNTER++] = 
                        new NimPlayer(userName,familyName,
                                givenName,winRate,gamePlayed,gameWon);
                }else if(!playerListChecker(userName) && isAiPlayer.equals("true")){
                    PLAYER_LIST[PLAYER_COUNTER++] = new 
                        NimAIPlayer(userName,familyName,
                                givenName,winRate,gamePlayed,gameWon);
                }
                
                numberOfLines -= 1;
            }
        }catch(FileNotFoundException e){
            System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    private static void addAiFunction(ArrayList<String> commandArray) {
        //user name is located in the primary position in the ArrayList
        //family name is located second
        //given name is located seoncd
        int userName = 1;
        int familyName = 2;
        int givenName = 3; 
        
        try{
            commandSizeChecker(commandArray.get(0),commandArray.size());
            
            if(PLAYER_COUNTER >= 100)
            {
                System.out.println("Too many players,Brah!");
            }
            else
            { 
                //If the player does not existd from the result of 
                //playerListChecker, add the player to database
                if(playerListChecker(commandArray.get(userName)) == false){
                    PLAYER_LIST[PLAYER_COUNTER++] = new 
                                        NimAIPlayer(commandArray.get(userName),
                                                    commandArray.get(familyName),
                                                    commandArray.get(givenName));
                }else{
                   System.out.println("The player already exists."); 
                }
            } 
        }catch(CommandSizeException e){
            System.out.println(e);
        }
    }
}