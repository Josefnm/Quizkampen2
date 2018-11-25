package GameServer;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player extends Thread {

    private Socket socket;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private Protocol protocol;
    private GameRoom gameRoom;

    private boolean isAvailable = false; //vill (inte) starta nytt spel

    public Player(Socket socket, Protocol protocol) {
        System.out.println("player connected");
        this.protocol = protocol;
        this.socket = socket;
        protocol.getPlayerList().add(this);
    }

    @Override
    public void run() {
        try {
            outStream = new ObjectOutputStream(socket.getOutputStream());
            inStream = new ObjectInputStream(socket.getInputStream());
            Object input;
            Send(protocol.getRoundsPerGame());
            while (!socket.isClosed()) {
                try {
                    input = inStream.readObject();
                } catch (EOFException eofe) {
                    break;  //ifall klienten stängs
                }
                protocol.getResponse(this, input);
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                protocol.getPlayerList().remove(this);
                outStream.close();
                inStream.close();
                socket.close();
                System.out.println("closed and removed player");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void Send(Object o) {
        try {
            outStream.writeObject(o);
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

    public GameRoom getGameRoom() {
        return gameRoom;
    }

    public void setGameRoom(GameRoom gameRoom) {
        this.gameRoom = gameRoom;
    }
}
