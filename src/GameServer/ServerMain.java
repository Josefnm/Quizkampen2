package GameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMain {

    private ServerSocket listener;
    private Protocol protocol;

    public ServerMain() {

        protocol = new Protocol();
        try {
            listener = new ServerSocket(12345);
            System.out.println("Quiz Server is Running");
            while (true) {
                final Socket socket = listener.accept();
                Player player = new Player(socket, protocol);
                player.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }

//        QuestionList ql = new QuestionList();
//        System.out.println("ok");
//        ArrayList<Question> tt = ql.getTwoQuestions(ql.matte, 2);
//        ArrayList<ArrayList<Question>> jj = ql.getTwoCategories(2, 2);
//        System.out.println(tt.get(0).getQuestion());
//        System.out.println(jj.get(0).get(0).getQuestion());
    }

    public static void main(String[] args) {
        ServerMain main = new ServerMain();

    }
}
