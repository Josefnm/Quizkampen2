package GameServer;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Josef
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(8901);
        System.out.println("Quiz Server is Running");
        try{
            while(true){
                Game game = new Game();
                Player playerX = new Player(listener.accept(), "X", game);
                Player playerO = new Player(listener.accept(), "O", game);
                playerX.setOpponent(playerO);
                playerO.setOpponent(playerX);
                game.currentPlayer = playerX;
                playerX.start();
                playerO.start();
            }
        }
        finally{
            listener.close();
        }
       
           
    }
}
