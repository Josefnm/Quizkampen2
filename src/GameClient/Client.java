package GameClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client{

    private final String address;
    private final int port;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    public Client() throws IOException {
        this.address = "127.0.0.1";
        this.port = 12345;

        Socket socket = new Socket(address, port);
        inStream = new ObjectInputStream(socket.getInputStream());
        outStream = new ObjectOutputStream(socket.getOutputStream());
        Object input;
    }

    public ObjectInputStream getInStream() {
        return inStream;
    }

    public void sendObject(Object object) {
        try {
            outStream.writeObject(object);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
