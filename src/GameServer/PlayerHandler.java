package GameServer;

import java.util.ArrayList;

public class PlayerHandler {
//lista på inloggade spelare, senare kan vi fixa så att de tas bort ifall de loggar ut
    ArrayList<Player> playerList; 

    public PlayerHandler() {
        this.playerList = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }
    public void removePlayer(Player player){
        playerList.remove(player);
    }
}
