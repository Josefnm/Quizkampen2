/*
 * Java
 */
package objectserver;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
            System.out.println("player out");
            input = new ObjectInputStream(socket.getInputStream());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
