package GameServer;

import java.util.ArrayList;
import java.util.List;

public class PlayerHandler {
//lista på inloggade spelare, senare kan vi fixa så att de tas bort ifall de loggar ut
    List<Player> playerList; 

    public PlayerHandler() {
        this.playerList = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }

}
