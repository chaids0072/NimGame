
/*******************************************************************************
 *This program is designed by Gang Chen(SID: 724553) to be submitted to The    *
 * Department of Engineering as Project c assignment                           *
 * @Gang Chen on 20/05/2015                                                    *
 *******************************************************************************
 */

public class NimAIPlayer extends NimPlayer implements Testable {
	// you may further extend a class or implement an interface
	// to accomplish the task in Section 2.3	
        private final boolean isAi;
        
        public NimAIPlayer() {
            super();
            isAi = true;
	}
        
	public NimAIPlayer(String userName, String familyName, String givenName) {
            super(userName,familyName,givenName);
            isAi = true;
	}
        
        public NimAIPlayer(String userName, String familyName, String givenName, 
                double winRate,int gamePlayed,int gameWon){
            super(userName,familyName,givenName,winRate,gamePlayed,gameWon);
            isAi = true;
        }
        
        
        @Override
        public boolean isAiPlayer(){
            return this.isAi;
        }
  
	public String advancedMove(boolean[] available, String lastMove) {
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = "";
                
                
		return move;
	}
}
