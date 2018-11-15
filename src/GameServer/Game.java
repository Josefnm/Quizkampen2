package GameServer;



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
    Player currentPlayer;
    
    public synchronized boolean legalMove(Player player, int num){
        if(player==currentPlayer && num==2){
            player = currentPlayer.opponent;
            return true;
        }        
        if(player==currentPlayer && num==1){
            return true;
        }
        return false;
    }


}

