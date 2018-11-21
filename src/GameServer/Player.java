package GameServer;

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
    GameRoom gameRoom;
    Player opponent;
    private boolean isAvailable = false; //kan starta nytt spel

    public Player(Socket socket, Protocol protocol) {
        try{
            outStream = new ObjectOutputStream(socket.getOutputStream());
            inStream = new ObjectInputStream(socket.getInputStream());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("player connected");
        this.protocol = protocol;
        this.socket = socket;
        protocol.playerList.addPlayer(this);
    }

    @Override
    public void run() {
        try {
            Object input;
            while (true) {
                input = inStream.readObject();
                try {
                    int score = Integer.parseInt(input.toString());
                    protocol.getResponse(this, score);
                } catch (Exception e) {
                    protocol.getResponse(this, input.toString());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
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
            System.out.println("try startgame");
            outStream
                    .writeObject(new StartPacket(protocol.questionList
                            .getFour(), gameRoom
                                    .isCurrentPlayer(this)));
            System.out.println("out");

        } catch (IOException ex) {
            Logger
                    .getLogger(Player.class
                            .getName()).log(Level.SEVERE, null, ex);
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
}
