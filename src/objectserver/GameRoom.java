/*
 * Java
 */
package objectserver;

/**
 *
 * @author Julia
 */
public class GameRoom extends Thread{
    Player p1;
    Player p2;
    Dao d = new Dao();
    public GameRoom(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;
    }
    @Override
    public void run(){
        try{
            Object in;
            String p1info="";
            String p2info="";
            p1.output.writeObject(d.getAllQ());
            p2.output.writeObject("wait");
            while(true){
                if((in = p1.input.readObject()) != null){
                    if(((String) in).equals("change")){
                        p2.output.writeObject(d.getAllQ());
                    }
                    if(((String) in).startsWith("end")){
                        p1info = ((String) in).substring(3);
                        p2.output.writeObject("begin second for p2");
                    }
                }
                if((in = p2.input.readObject()) != null){
                    if(((String) in).equals("change")){
                        p1.output.writeObject("p1rond2");
                    }
                    if(((String) in).startsWith("end")){
                        p2info = ((String) in).substring(3);                        
                        p1.output.writeObject("p2end," +p1info+","+ p2info);
                        p2.output.writeObject("p2end," +p2info+","+ p1info);
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
