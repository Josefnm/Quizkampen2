package GameClient;

import GameServer.InfoPacket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientTCP {

    private final String address;
    private final int port;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private Socket socket;
    private ClientProtocol protocol;

    public ClientTCP(ClientMain main) {
        protocol = new ClientProtocol(main);
        address = "127.0.0.1";
        port = 12345;
        try {
            socket = new Socket(address, port);
            inStream = new ObjectInputStream(socket.getInputStream());
            outStream = new ObjectOutputStream(socket.getOutputStream());
           // startShutDownHook();
            inputThread();
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void send(Object object) {
        try {
            outStream.writeObject(object);
            outStream.flush();
            outStream.reset();
        } catch (IOException ex) {
            System.out.println("writeobject error");
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void inputThread() { //separerad från konstruktorn för läsbarhet
        new Thread(() -> {
            InfoPacket input;

            while (!socket.isClosed()) {//lyssnar på input från servern
                try {
                    input = (InfoPacket) inStream.readObject();
                    protocol.handleInput(input);
                    System.out.println("recieved input");
                } catch (IOException | ClassNotFoundException ex) {
                    System.out.println("readobject error");
                    Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

//    private void startShutDownHook() { //separerad från konstruktorn för läsbarhet
//        // Stänger socketen om programmet avslutas, förhindrar fel på serversidan
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            @Override
//            public void run() {
//                try {
//                    outStream.close();
//                    inStream.close();
//                    socket.close();
//                    System.exit(0);
//                    System.out.println("Socket closed!");
//                } catch (IOException e) {
//                    System.out.println("closing socket failed");
//                }
//            }
//        });
//    }

    public ObjectInputStream getInStream() {
        return inStream;
    }

    public ObjectOutputStream getOutStream() {
        return outStream;
    }

    public Socket getSocket() {
        return socket;
    }
    
}
