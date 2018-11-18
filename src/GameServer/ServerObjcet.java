/*
 * Java
 */
package GameServer;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Julia
 */
public class ServerObjcet {
    public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(12345);
        System.out.println("game begin");
        try{
            while(true){
                PlayerObject p1 = new PlayerObject(server.accept());
                p1.start();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
