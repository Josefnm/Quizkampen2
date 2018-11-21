 package GameServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player extends Thread {

    Socket socket;
    ObjectInputStream inStream;
    ObjectOutputStream outStream;
    Protocol protocol;
    GameRoom gameRoom;
    Player opponent;
    private boolean isAvailable = false; //kan starta nytt spel

    public Player(Socket socket, Protocol protocol) {
        System.out.println("player connected");
        this.protocol = protocol;
        this.socket = socket;
        protocol.playerList.addPlayer(this);
    }

    @Override
    public void run() {
        try {
            outStream = new ObjectOutputStream(socket.getOutputStream());
            inStream = new ObjectInputStream(socket.getInputStream());
            Object input;
            while (true) {
                input = inStream.readObject();
                protocol.getResponse(this, input.toString());
            }

            }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void startGame() {
        try {
            outStream.writeObject(protocol.questionList.getFour());
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean getIsIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean bool) {
        isAvailable = bool;
    }
}
