/*
 * Java
 */
package GameServer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Julia
 */
class PlayerObject extends Thread{
    ObjectOutputStream output;
    ObjectInputStream input;
    Socket socket;
    //Dao d = new Dao();
    public PlayerObject(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run(){
        try{
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
//            output.writeObject(getAllQ());
            //output.writeObject(d.getTwo());
//            output.writeObject(d.getByNum(0));
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
