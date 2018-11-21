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
    ArrayList<Question> lista = new ArrayList<>();
    Protocol protocol;
    Player opponent;
    private boolean isAvailable = false; //kan starta nytt spel

    public Player(Socket socket, Protocol protocol) {
        System.out.println("player connected");
        this.protocol = protocol;
        this.socket = socket;
        protocol.playerHandler.addPlayer(this);
    }

    @Override
    public void run() {
        try {
            outStream = new ObjectOutputStream(socket.getOutputStream());
            inStream = new ObjectInputStream(socket.getInputStream());
            Object input;
            Object output;

            for(int i=0; i<4; i++){
                lista.add(protocol.questionList.getByNum(i));
            }
            outStream.writeObject(lista.get(0));
//            while (true) {
//                input = inStream.readObject();
//                System.out.println(input.toString());
//                if (input.toString().equals("start")) {
//                    protocol.getOpponent(this);
//                }
            }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
        isAvailable = false;
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


}
