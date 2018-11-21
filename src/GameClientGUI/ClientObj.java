/*
 * Java
 */
package GameClientGUI;

import GameServer.QuestionList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Julia
 */
public class ClientObj {
    ObjectInputStream in;
    ObjectOutputStream out;
    int PORT = 12345;
    Socket socket;
    public ClientObj(String adress) throws IOException{
        socket = new Socket(adress, PORT);
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        Object input;
        System.out.println("xx");
        try{
//            if((input=in.readObject()) instanceof List){
            if((input=in.readObject()) != null){
                System.out.println("oo");
//                System.out.println(((List<Questions>) input).get(0).getFraga());
//                System.out.println(((Questions[]) input)[0].getFraga());
//                System.out.println(((Questions) input).getFraga());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException{
            String serverAddress = (args.length == 0)? "localhost" : args[1];
            ClientObj client = new ClientObj(serverAddress);        
    }    
}
