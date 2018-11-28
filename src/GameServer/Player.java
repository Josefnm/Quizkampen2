package GameServer;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

public class Player extends Thread {

    private Socket socket;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private ServerProtocol protocol;
    private GameRoom gameRoom;
    private String playerName;
     private String avatar;
    private boolean isAvailable = false; //vill (inte) starta nytt spel

    public Player(Socket socket, ServerProtocol protocol) {
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
            send(new InfoPacket(protocol.getRoundsPerGame())); //skickar rundor per spel som bestämts av properties
            while (!socket.isClosed()) {
                try {
                    input = inStream.readObject();
                    protocol.handleInput(this, (InfoPacket) input);
                } catch (EOFException eofe) {
                    break;  //ifall klienten stängs
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } finally { //stänger anslutningen och tar bort spelaren ifall den loggat ur
            protocol.getPlayerList().remove(this);
            try {
                outStream.close();
                inStream.close();
                socket.close();
                System.out.println("closed and removed player");
            } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void send(Object o) {
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

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
