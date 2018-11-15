package QuizClient;

import QuizServer.Question;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josef
 */
public class Client {

    int portNr = 12345;
    String hostIPAddress = "127.0.0.1";
    ObjectInputStream in;
    ObjectOutputStream out;
    Object input;
    QuestionSceneController qsc;
    Question q;

    public Client() {

        try {
            Socket adressSocket = new Socket(hostIPAddress, portNr);
            System.out.println("aftersocket");
            out = new ObjectOutputStream(adressSocket.getOutputStream());
            in = new ObjectInputStream(adressSocket.getInputStream());
            System.out.println("connected");
            
        } catch (IOException ex) {
            System.out.println("klientfel");
            System.exit(1);
        }
        try {
                q=(Question)in.readObject();
            } catch (ClassNotFoundException | IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public boolean send(Object o) {
        System.out.println("send");
        try {
            out.writeObject(o);
            boolean x=(boolean) in.readObject();
            System.out.println(x);
            return x;
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
