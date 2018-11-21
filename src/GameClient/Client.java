package GameClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private final String address;
    private final int port;
    protected ObjectInputStream inStream;
    protected ObjectOutputStream outStream;
    private Socket socket;

    public Client() {
        this.address = "127.0.0.1";
        this.port = 12345;
        try {
            socket = new Socket(address, port);
            inStream = new ObjectInputStream(socket.getInputStream());
            outStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Stänger socketen om programmet avslutas
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    outStream.close();
                    inStream.close();
                    socket.close();
                    System.out.println("The server is shut down!");
                } catch (IOException e) {
                    /* failed */ }
            }
        });
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
