package GameClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    int portNr = 12345;
    String hostIPAddress = "127.0.0.1";
    ObjectInputStream input;
    ObjectOutputStream output;
    
    public Client() {
        try {
            Socket adressSocket = new Socket(hostIPAddress, portNr);
            output = new ObjectOutputStream(adressSocket.getOutputStream());
            input = new ObjectInputStream(adressSocket.getInputStream());
        } catch (IOException ex) {
            System.out.println("fel");
            System.exit(1);
        }
    }
}
