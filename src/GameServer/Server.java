package GameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public Server() {
        try {
            ServerSocket listener = new ServerSocket(12345);
            System.out.println("Quiz Server is Running");
            try {
                while (true) {
                    //Game game = new Game();
                    final Socket socket = listener.accept();
                    Player player = new Player(socket);
                    //Player playerO = new Player(listener.accept(), "O", game);
                    //playerX.setOpponent(playerO);
                    //playerO.setOpponent(playerX);
                    //game.currentPlayer = playerX;
                    player.start();
                    //playerO.start();
                }
            } finally {
                listener.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
