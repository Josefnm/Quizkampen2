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
            //outStream.writeObject(protocol.questionList.getFour()); //testkod
            while (true) {
                input = inStream.readObject();
                System.out.println(input.toString());
                if (input.toString().equals("start")) {
                    protocol.getOpponent(this);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
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

//    String mark;
//    Player opponent;
//    Socket socket;
//    GameRoom game;
//    BufferedReader inStream;
//    PrintWriter outStream;
//    
//    public Player(Socket socket, String mark, GameRoom game){
//        this.socket=socket;
//        this.mark=mark;
//        this.game=game;
//        try{
//            inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            outStream = new PrintWriter(socket.getOutputStream(), true);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public void run(){
//        try{
//           outStream.println(game.q1.getFraga()+","+game.q1.getValjningar(0)+","+
//                   game.q1.getValjningar(1)+","+game.q1.getValjningar(2)+","+
//                   game.q1.getValjningar(3));
//           String answer;
//           while((answer = inStream.readLine()) != null){
//               String[] s = answer.split(",");
//               int ss = Integer.parseInt(s[1].trim());
//               if(game.legalMove(this, ss)){
//                   System.out.println(this);
//                    if(answer.startsWith(game.q1.getResult())){
//                        outStream.println("yes"+","+game.q1.getResult());
//                    }
//                    if(!answer.startsWith(game.q1.getResult())){
//                        outStream.println("no"+","+game.q1.getResult()+","+answer);
//                    }
//                    if(answer.startsWith(game.q2.getResult())){
//                        outStream.println("yes"+","+game.q2.getResult());
//                    }
//                    if(!answer.startsWith(game.q2.getResult())){
//                        outStream.println("no"+","+game.q2.getResult()+","+answer);
//                    }
//                    if(answer.startsWith(game.q3.getResult())){
//                        outStream.println("yes"+","+game.q3.getResult());
//                    }
//                    if(!answer.startsWith(game.q3.getResult())){
//                        outStream.println("no"+","+game.q3.getResult()+","+answer);
//                    }
//                    if(answer.startsWith(game.q4.getResult())){
//                        outStream.println("yes"+","+game.q4.getResult());
//                    }
//                    if(!answer.startsWith(game.q4.getResult())){
//                        outStream.println("no"+","+game.q4.getResult()+","+answer);
//                    }
//               }
//                if(answer.startsWith("next")){
//                    if(ss==100){
//                        outStream.println(game.q2.getFraga()+","+game.q2.getValjningar(0)+","+
//                        game.q2.getValjningar(1)+","+game.q2.getValjningar(2)+","+
//                        game.q2.getValjningar(3)); 
//                    }
//                    if(ss==101){
//                        outStream.println(game.q3.getFraga()+","+game.q3.getValjningar(0)+","+
//                        game.q3.getValjningar(1)+","+game.q3.getValjningar(2)+","+
//                        game.q3.getValjningar(3)); 
//                    }
//                    if(ss==102){
//                        outStream.println(game.q4.getFraga()+","+game.q4.getValjningar(0)+","+
//                        game.q4.getValjningar(1)+","+game.q4.getValjningar(2)+","+
//                        game.q4.getValjningar(3)); 
//                    }
//                }
//           }
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        finally{
//            try{socket.close();}catch(Exception e){e.printStackTrace();}
//        }
//    }    
}
