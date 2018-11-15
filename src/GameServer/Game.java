package GameServer;

import quizkampen.QandA;
import quizkampen.ServerSidePlayer;

/**
 *
 * @author Josef
 */
public class Game{
    public Dao qa = new Dao();
    Questions q1 = qa.getByNum(1);
    Questions q2 = qa.getByNum(2);
    Questions q3 = qa.getByNum(3);
    Questions q4 = qa.getByNum(4);
    ServerSidePlayer currentPlayer;
    
    public synchronized boolean legalMove(ServerSidePlayer player, int num){
        if(player==currentPlayer && num==2){
            currentPlayer = currentPlayer.opponent;
            return true;
        }        
        if(player==currentPlayer && num==1){
            return true;
        }
        return false;
    }


}

