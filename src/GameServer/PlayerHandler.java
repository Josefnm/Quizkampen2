package GameServer;

import java.util.ArrayList;
import java.util.List;

public class PlayerHandler {
//lista på inloggade spelare, senare kan vi fixa så att de tas bort ifall de loggar ut
    ArrayList<Player> playerList; 

    public PlayerHandler() {
        this.playerList = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }
public Player getOpponent(Player player1) {
        for (Player player2 : playerList) {
            if (player2.getIsIsAvailable()) {
                player2.setOpponent(player1);
                player1.setOpponent(player2);
                player1.startGame();
                player2.startGame();
                return player2;
            }
        }
        return null;
    }
}
