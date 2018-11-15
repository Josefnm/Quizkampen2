/*
 * Java
 */
package quizkampen;

import java.io.IOException;

/**
 *
 * @author Julia
 */
public class ServerSideGame {
    public Dao qa = new Dao();
    QandA q1 = qa.getByNum(1);
    QandA q2 = qa.getByNum(2);
    QandA q3 = qa.getByNum(3);
    QandA q4 = qa.getByNum(4);
    ServerSidePlayer currentPlayer;
    
    public synchronized boolean legalMove(ServerSidePlayer player, int num){
        if(player==currentPlayer && num==1){
            return true;
        }
        if(player==currentPlayer && num==2){
            currentPlayer = currentPlayer.opponent;
            return true;
        }        
        return false;
    }


}
