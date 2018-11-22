package GameServer;

public class GameRoom {

    Player player1;
    Player player2;
    Player currentPlayer;
    int[] player1Score;
    int[] player2Score;
    int currentRound;

    public GameRoom(Player player1, Player player2) {
        this.player1 = player1;
        this.player1 = player2;
        currentPlayer = player1;
    }
    
    public boolean isCurrentPlayer(Player player){
        return (currentPlayer==player);
    }
}
