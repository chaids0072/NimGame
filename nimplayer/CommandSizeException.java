/*******************************************************************************
 *This program is designed by Gang Chen(SID: 724553) to be submitted to The    *
 * Department of Engineering as Project c assignment                           *
 * @Gang Chen on 20/05/2015                                                    *
 *******************************************************************************
 */
public class CommandSizeException extends Exception{
    private String wrong = "Incorrect number of arguments supplied to command.";
    
    public CommandSizeException(){
        super();
    }
    
    @Override
    public String toString(){
        return this.wrong;
    }
}
