/*******************************************************************************
 *This program is designed by Gang Chen(SID: 724553) to be submitted to The    *
 * Department of Engineering as Project c assignment                           *
 * @Gang Chen on 20/05/2015                                                    *
 *******************************************************************************
 */
public class CommandException extends Exception{
    private static final String WRONG = " is not a valid command.";
    private String message = WRONG;
    
    public CommandException(){
        super("No inputs accepted");
    }
    
    public CommandException(String command){
        super();
        this.message = ("'" + command + "'") + message;
        
    }
    
    @Override
    public String toString(){
        return this.message;
    }
}
