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
            System.out.println("p1 out");
            p1.output.writeObject(d.getAllQ());
            p2.output.writeObject("wait");
            while(true){
                if((in = p1.input.readObject()) != null){
                    if(((String) in).equals("change"))
                        p2.output.writeObject(d.getAllQ());
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
