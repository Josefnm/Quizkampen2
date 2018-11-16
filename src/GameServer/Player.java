package GameServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Josef
 */
public class Player extends Thread{
    String mark;
    Player opponent;
    Socket socket;
    Game game;
    ObjectInputStream input;
    ObjectOutputStream output;
    int portNummer=12345;
    Dao d = new Dao();

    public Player(Socket socket, String mark, Game game){
        System.out.println("player ");
        this.socket=socket;
        this.mark=mark;
        this.game=game;            
    }

    @Override
    public void run(){
        try{
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            output.writeObject(d.getALL());
//            output.writeObject(d.getFirst());
//            output.writeObject(d.getByNum(0));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{socket.close();}catch(Exception e){e.printStackTrace();}
        }
    }        
    
    public void setOpponent(Player opponent){
        this.opponent=opponent;
    }
    
    
//    String mark;
//    Player opponent;
//    Socket socket;
//    GameRoom game;
//    BufferedReader input;
//    PrintWriter output;
//    
//    public Player(Socket socket, String mark, GameRoom game){
//        this.socket=socket;
//        this.mark=mark;
//        this.game=game;
//        try{
//            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            output = new PrintWriter(socket.getOutputStream(), true);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public void run(){
//        try{
//           output.println(game.q1.getFraga()+","+game.q1.getValjningar(0)+","+
//                   game.q1.getValjningar(1)+","+game.q1.getValjningar(2)+","+
//                   game.q1.getValjningar(3));
//           String answer;
//           while((answer = input.readLine()) != null){
//               String[] s = answer.split(",");
//               int ss = Integer.parseInt(s[1].trim());
//               if(game.legalMove(this, ss)){
//                   System.out.println(this);
//                    if(answer.startsWith(game.q1.getResult())){
//                        output.println("yes"+","+game.q1.getResult());
//                    }
//                    if(!answer.startsWith(game.q1.getResult())){
//                        output.println("no"+","+game.q1.getResult()+","+answer);
//                    }
//                    if(answer.startsWith(game.q2.getResult())){
//                        output.println("yes"+","+game.q2.getResult());
//                    }
//                    if(!answer.startsWith(game.q2.getResult())){
//                        output.println("no"+","+game.q2.getResult()+","+answer);
//                    }
//                    if(answer.startsWith(game.q3.getResult())){
//                        output.println("yes"+","+game.q3.getResult());
//                    }
//                    if(!answer.startsWith(game.q3.getResult())){
//                        output.println("no"+","+game.q3.getResult()+","+answer);
//                    }
//                    if(answer.startsWith(game.q4.getResult())){
//                        output.println("yes"+","+game.q4.getResult());
//                    }
//                    if(!answer.startsWith(game.q4.getResult())){
//                        output.println("no"+","+game.q4.getResult()+","+answer);
//                    }
//               }
//                if(answer.startsWith("next")){
//                    if(ss==100){
//                        output.println(game.q2.getFraga()+","+game.q2.getValjningar(0)+","+
//                        game.q2.getValjningar(1)+","+game.q2.getValjningar(2)+","+
//                        game.q2.getValjningar(3)); 
//                    }
//                    if(ss==101){
//                        output.println(game.q3.getFraga()+","+game.q3.getValjningar(0)+","+
//                        game.q3.getValjningar(1)+","+game.q3.getValjningar(2)+","+
//                        game.q3.getValjningar(3)); 
//                    }
//                    if(ss==102){
//                        output.println(game.q4.getFraga()+","+game.q4.getValjningar(0)+","+
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
