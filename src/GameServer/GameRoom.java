package GameServer;

/**
 *
 * @author Josef
 */
public class GameRoom {

    private boolean isNotFull; // false när 2 spelare är inne
    Player player1;
    Player player2;
    Player currentPlayer;
    

    public GameRoom(Player player) {
        isNotFull = true;
        player1 = player;
    }

//    public synchronized boolean legalMove(Player player, int num) {
//        if (player == currentPlayer && num == 2) {
//            player = currentPlayer.opponent;
//            return true;
//        }
//        if (player == currentPlayer && num == 1) {
//            return true;
//        }
//        return false;
//    }

    public boolean getIsNotFull() {
        return isNotFull;
    }

    public void addPlayer(Player player) {
        player2 = player;
        isNotFull = false;
        //kod som startar spelet
    }
}
