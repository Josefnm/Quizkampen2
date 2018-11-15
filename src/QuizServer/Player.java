package QuizServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Josef
 */
public class Player extends Thread {

    Socket clientSocket;
    ObjectInputStream in;
    ObjectOutputStream out;
    ArrayList<Player> clientList;
    Question question;
    String playerName;

    public Player(Socket clientSocket, ArrayList<Player> clientList, Question question) {
        this.clientSocket = clientSocket;
        this.clientList = clientList;
        this.question=question;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("connection");
            out.writeObject(question);
            while (true) {
                Object input = in.readObject();
                boolean x;
                    out.writeObject(((String)input).equals(question.getCorrectAnswer()));
                System.out.println("sent");
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("error1");
        } finally {
            clientList.remove(this);
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
