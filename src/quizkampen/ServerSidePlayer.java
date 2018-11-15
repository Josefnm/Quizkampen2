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
               int ss = Integer.parseInt(s[1].trim());
               if(game.legalMove(this, ss)){
                    if(answer.startsWith(game.q1.getResult())){
                        output.println("yes"+","+game.q1.getResult());
                    }
                    if(!answer.startsWith(game.q1.getResult())){
                        output.println("no"+","+game.q1.getResult()+","+answer);
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
