package GameServer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josef
 */
public class GameRoomHandler {
    List<Player> playerList;
    List<GameRoom> gameRooms;

    public GameRoomHandler() {
        this.playerList = new ArrayList<>();
        this.gameRooms = new ArrayList<>();
    }
    
    
    public void startNewGame(Player player){
        for (GameRoom gameRoom : gameRooms) {
            if(gameRoom.getIsNotFull()){
                gameRoom.addPlayer(player);
                return;
            }
        }
        GameRoom gr=new GameRoom(player);
        gameRooms.add(gr);
    }
}
