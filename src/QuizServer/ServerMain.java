/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {

    int portNr;
    ServerSocket serverSocket;
    ArrayList<Player> clientList;
    ArrayList<Question> questions;

    public ServerMain() {
        //questions
        questions = new ArrayList<>();
        questions.add(new Question("Matte", "Vad är 1+1", new String[]{"2", "5", "10", "20"}, 0));
        questions.add(new Question("Matte", "Vad är 2+2", new String[]{"2", "5", "4", "20"}, 2));
        questions.add(new Question("Sport", "Vilken form har en boll", new String[]{"fyrkantig", "trekantig", "pentagon", "rund"}, 3));
        questions.add(new Question("Sport", "Vilken sport Sysslar Zlatan med", new String[]{"Ishockey", "Fotboll", "Balett", "Boxning"}, 1));
        //server stuff
        System.out.println("hello");
        this.portNr = 12345;
        this.clientList = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(portNr);
        } catch (IOException ex) {
            System.out.println("new serversocket error");
        }
        startServer();
    }

    public  void startServer() {
        System.out.println("startServer");
        while (true) {
            try {
                final Socket clientSocket = serverSocket.accept();
                Player clientHandler = new Player(clientSocket, clientList,questions.get(0));
                clientList.add(clientHandler);
                clientHandler.start();
            } catch (IOException ex) {
                System.out.println("error1");
            }

        }
    }

    public static void main(String[] args) {
        ServerMain serverMain = new ServerMain();
    }
}
/*
Börja med att bygga en enkelt client-server-applikation där en klient kopplar upp sig mot en
server. Klienten laddar sedan ner en fråga med 4 svarsalternativ från servern. Användaren
svarar på frågan i klientapplikationen och får feedback om svaret är rätt eller fel.
 */
