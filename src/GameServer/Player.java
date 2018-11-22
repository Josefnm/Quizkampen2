package GameServer;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player extends Thread {

    Socket socket;
    ObjectInputStream inStream;
    ObjectOutputStream outStream;
    Protocol protocol;
    private GameRoom gameRoom;
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
            while (!socket.isClosed()) {
                try {
                    input = inStream.readObject();
                } catch (EOFException eofe) {
                    break; //ifall klienten stängs
                }
                try {
                    int score = Integer.parseInt(input.toString());
                    protocol.getResponse(this, score);
                    System.out.println("1");
                } catch (Exception e) {
                    protocol.getResponse(this, input.toString());
                    System.out.println("2");
                }

            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                protocol.playerList.removePlayer(this);
                outStream.close();
                inStream.close();
                socket.close();
                System.out.println("closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void startGame() {
        try {
            outStream
                    .writeObject(new StartPacket(protocol.getQuestionList()
                            .getFour(), gameRoom
                                    .isCurrentPlayer(this)));
            System.out.println("out");

        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean getIsIsAvailable() {
        return isAvailable;

    }

    public void setIsAvailable(Boolean bool
    ) {
        isAvailable
                = bool;

    }

    public GameRoom getGameRoom() {
        return gameRoom;
    }

    public void setGameRoom(GameRoom gameRoom) {
        this.gameRoom = gameRoom;
    }

}
