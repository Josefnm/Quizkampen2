 /*
 * Java
 */
package quizkampen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Julia
 */
public class ServerSidePlayer extends Thread{
    String mark;
    ServerSidePlayer opponent;
    Socket socket;
    ServerSideGame game;
    BufferedReader input;
    PrintWriter output;
    
    public ServerSidePlayer(Socket socket, String mark, ServerSideGame game){
        this.socket=socket;
        this.mark=mark;
        this.game=game;
        try{
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void setOpponent(ServerSidePlayer opponent){
        this.opponent=opponent;
    }
    @Override
    public void run(){
        try{
           output.println(game.q1.getFraga()+","+game.q1.getValjningar(0)+","+
                   game.q1.getValjningar(1)+","+game.q1.getValjningar(2)+","+
                   game.q1.getValjningar(3));
           String answer;
           while((answer = input.readLine()) != null){
               String[] s = answer.split(",");
               int twoTimes = Integer.parseInt(s[1].trim());
               if(game.legalMove(this, twoTimes)){
                    if(answer.startsWith(game.q1.getResult())){
                        output.println("yes"+","+game.q1.getResult());
                    }
                    else if(answer.startsWith(game.q2.getResult())){
                        output.println("yes"+","+game.q2.getResult());
                    }
                    else if(answer.startsWith(game.q3.getResult())){
                        output.println("yes"+","+game.q3.getResult());
                    }
                    else if(answer.startsWith(game.q4.getResult())){
                        output.println("yes"+","+game.q4.getResult());
                    }
                    else{
                        String indata = game.qa.findResult(s[0]);
                        output.println("no"+","+indata+","+answer);
                    }
               }
                if(answer.startsWith("next")){
                    if(twoTimes==100){
                        output.println(game.q2.getFraga()+","+game.q2.getValjningar(0)+","+
                        game.q2.getValjningar(1)+","+game.q2.getValjningar(2)+","+
                        game.q2.getValjningar(3)); 
                    }
                    if(twoTimes==101){
                        output.println(game.q3.getFraga()+","+game.q3.getValjningar(0)+","+
                        game.q3.getValjningar(1)+","+game.q3.getValjningar(2)+","+
                        game.q3.getValjningar(3)); 
                    }
                    if(twoTimes==102){
                        output.println(game.q4.getFraga()+","+game.q4.getValjningar(0)+","+
                        game.q4.getValjningar(1)+","+game.q4.getValjningar(2)+","+
                        game.q4.getValjningar(3)); 
                    }
                }
           }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{socket.close();}catch(Exception e){e.printStackTrace();}
        }
    }    
}
