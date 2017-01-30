/*******************************************************************************
 *This program is designed by Gang Chen(SID: 724553) to be submitted to The    *
 * Department of Engineering as Project c assignment                           *
 * @Gang Chen on 20/05/2015                                                    *
 *******************************************************************************
 */
class StoneMove extends Exception {
    private String message = "";
    
    public StoneMove() {
        super();
    }
    
    public StoneMove(int remove){
        super();
        this.message = "Invalid move. "+ "You must remove between "
                                + " 1 and "+remove+ " stones.";;   
    }

    @Override
    public String toString(){
        return this.message;
    }
}
