/*
 * Java
 */
package objectserver;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julia
 */
public class Player {
    Socket socket;
    ObjectInputStream input;
    ObjectOutputStream output;
    int portNummer=12345;

    public Player(Socket socket){
        this.socket=socket;
         
        try{
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
