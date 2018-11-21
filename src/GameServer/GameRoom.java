package GameServer;

public class GameRoom {

    Player player1;
    Player player2;
    Player currentPlayer;
    int player1Score = 0;
    int player2Score = 0;

    public GameRoom(Player player1, Player player2) {
        this.player1 = player1;
        this.player1 = player2;
        currentPlayer = player1;
    }

}
