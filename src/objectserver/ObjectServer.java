/*
 * Java
 */

package objectserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ObjectServer {

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(12345);
        System.out.println("Quiz Server is Running");
        try{
            while(true){
                Player playerX = new Player(listener.accept());
                Player playerO = new Player(listener.accept());
                GameRoom ga = new GameRoom(playerX, playerO);
                ga.start();
            }
        }
        finally{
            listener.close();
        }       
    }

}
