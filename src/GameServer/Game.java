/*
 * Java
 */
package GameServer;


/**
 *
 * @author Julia
 */
public class Game {
    public Dao qa = new Dao();
    Question q1 = qa.getByNum(0);
    Question q2 = qa.getByNum(1);
    Question q3 = qa.getByNum(2);
    Question q4 = qa.getByNum(3);
    Player currentPlayer;
    
    public synchronized boolean legalMove(Player player, int num){
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
