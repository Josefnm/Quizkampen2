/*
 * Java
 */

package quizkampen;

import java.io.IOException;
import java.net.ServerSocket;


public class QuizKampenServer {

    
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(8901);
        System.out.println("Quiz Server is Running");
        try{
            while(true){
                ServerSideGame game = new ServerSideGame();
                ServerSidePlayer playerX = new ServerSidePlayer(listener.accept(), "X", game);
                ServerSidePlayer playerO = new ServerSidePlayer(listener.accept(), "O", game);
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
