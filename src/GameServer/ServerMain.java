package GameServer;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMain {
    private ServerSocket listener;
    private Protocol protocl;


   
    public ServerMain() {
        protocl = new Protocol();
        try{
            listener = new ServerSocket(12345);
            System.out.println("Quiz Server iS Running");
            while(true){
                final Socket socket = listener.accept();
                Player player = new Player(socket, protocl);
                player.start();
            }
        }
        catch(IOException e){
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, e);            
        }
        
    }

    public static void main(String[] args) {
        ServerMain main = new ServerMain();
    }
}
