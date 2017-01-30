/*******************************************************************************
 *This program is designed by Gang Chen(SID: 724553) to be submitted to The    *
 * Department of Engineering as Project c assignment                           *
 * @Gang Chen on 20/05/2015                                                    *
 *******************************************************************************
 */

import java.util.Comparator;

public class Ranking implements Comparator<NimPlayer>{
    //Comparing the winning rate of all players in the PLAYER_LIST
    @Override
    public int compare(NimPlayer o1, NimPlayer o2) {
        double p1 = o1.getWinRate();
        double p2 = o2.getWinRate();
        
        if(p1 > p2){
            return -1;
        }else if(p1 < p2){
            return 1;
        }else{
            return 0;
        }
    }
}
